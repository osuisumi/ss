/**
 * 
 */
package com.haoyu.sip.auth.realm;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.haoyu.sip.auth.entity.AuthPermission;
import com.haoyu.sip.auth.entity.AuthRole;
import com.haoyu.sip.auth.entity.AuthUser;
import com.haoyu.sip.auth.service.IAuthPermissionService;
import com.haoyu.sip.auth.service.IAuthRoleService;

/**
 * @author lianghuahuang
 *
 */
public abstract class AuthenticationRealm extends AuthorizingRealm   implements CacheCleaner {
	@Autowired
	private IAuthRoleService authRoleService;

	@Autowired
	private IAuthPermissionService authPermissionService;
	
	
	private List<IAuthRealmHandler> authRealmHandlers;

	public List<IAuthRealmHandler> getAuthRealmHandlers() {
		return authRealmHandlers;
	}

	public void setAuthRealmHandlers(List<IAuthRealmHandler> authRealmHandlers) {
		this.authRealmHandlers = authRealmHandlers;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.apache.shiro.realm.AuthorizingRealm#doGetAuthorizationInfo(org.apache
	 * .shiro.subject.PrincipalCollection)
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		if (principals == null) {
			throw new AuthorizationException("principals should not be null");
		}
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		//
		Map<String, Object> attributes = getPrincipalAttributes(principals);
		String userId = getUserId(attributes);
		// 查询用户角色信息
		List<AuthRole> roles = authRoleService.findRoleByAuthUser(new AuthUser(
				userId));
		// 添加角色信息
		for (AuthRole role : roles) {
			info.addRole(role.getCode());
		}
		// 查询用户权限信息
		List<AuthPermission> permissions = authPermissionService
				.findPermissionByRoles(roles);
		if (permissions != null && !permissions.isEmpty()) {
			for (AuthPermission permission : permissions) {
				info.addStringPermission(String.join(":",permission.getActionURI(), permission.getAction()));
			}
		}
		addAuthorize(info, principals);
		return info;
	}

	protected String getUserId(Map<String, Object> attributes) {
		return Objects.toString(attributes.get(getUserIdAttributeKey()));
	}

	protected Map<String, Object> getPrincipalAttributes(
			PrincipalCollection principals) {
		if (principals != null) {
			List<Object> listPrincipals = principals.asList();
			if (listPrincipals != null & listPrincipals.size() > 1) {
				Map<String, Object> attributes = (Map<String, Object>) listPrincipals.get(1);
				return attributes;
			}
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.apache.shiro.realm.AuthenticatingRealm#doGetAuthenticationInfo(org
	 * .apache.shiro.authc.AuthenticationToken)
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken authenticationToken)
			throws AuthenticationException {
		UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
		if (StringUtils.isEmpty(token.getUsername())) {
			throw new AccountException("IncorrectCredentials");
		}
		AuthUser au = findAuthUserByUsername(token.getUsername());
		if (au.getPassword().equals(DigestUtils.md5Hex(new String(token.getPassword())))) {
			Map<String, String> attributes = Maps.newHashMap();
			attributes.put(getUserIdAttributeKey(), au.getId());
			attributes.put("realName", au.getRealName());
			List<Object> principals = Lists.newArrayList();
			principals.add(au.getUsername());
			principals.add(attributes);
			return new SimpleAuthenticationInfo(principals, token.getPassword(),
					this.getName());
		}
		throw new IncorrectCredentialsException();
	}

	public void clearUserCacheByUsername(String username) {
		if(hasCache()){
			List<Object> principals = getPrincipalsByUsername(username);
			SimplePrincipalCollection pc = new SimplePrincipalCollection();
			pc.addAll(principals, super.getName());
			super.clearCachedAuthorizationInfo(pc);
		}
	}
	
	public void clearUserCacheById(String id) {
		if(hasCache()){
			List<Object> principals = getPrincipalsById(id);
			SimplePrincipalCollection pc = new SimplePrincipalCollection();
			pc.addAll(principals, super.getName());
			super.clearCachedAuthorizationInfo(pc);
		}
	}
	
	public void clearUserCacheByIds(List<String> ids) {
		if(hasCache()){
			List<AuthUser> authUsers = this.findAuthUserByIds(ids);
			for(AuthUser au:authUsers){
				List<Object> principals = getPrincipals(au);
				SimplePrincipalCollection pc = new SimplePrincipalCollection();
				pc.addAll(principals, super.getName());
				super.clearCachedAuthorizationInfo(pc);
			}

		}
	}
	
	public void clearUserCache(){
		if(hasCache()){
			Subject subject = SecurityUtils.getSubject();
			if(subject!=null){
				SimplePrincipalCollection pc = new SimplePrincipalCollection();
				pc.addAll(subject.getPrincipals().asList(), super.getName());
				super.clearCachedAuthorizationInfo(pc);
			}
		}
		
	}
	
	public boolean hasCache(){
		 return super.getAuthorizationCache()!=null||(isAuthorizationCachingEnabled() && super.getCacheManager()!=null);
	}

	private List<Object> getPrincipalsByUsername(String username) {
		AuthUser au = findAuthUserByUsername(username);
		return getPrincipals(au);
	}
	
	private List<Object> getPrincipalsById(String id) {
		AuthUser au = findAuthUserById(id);
		return getPrincipals(au);
	}

	private List<Object> getPrincipals(AuthUser au) {
		Map<String, String> attributes = Maps.newHashMap();
		attributes.put(getUserIdAttributeKey(), au.getId());
		attributes.put("realName", au.getRealName());
		List<Object> principals = Lists.newArrayList();
		principals.add(au.getUsername());
		principals.add(attributes);
		return principals;
	}

	protected void addAuthorize(SimpleAuthorizationInfo info,
			PrincipalCollection principals){
		if(authRealmHandlers!=null&&!authRealmHandlers.isEmpty()){
			for(IAuthRealmHandler irh:authRealmHandlers){
				if(irh!=null){
					irh.addAuthorize(info, principals);
				}
			}
		}
	}

	protected abstract AuthUser findAuthUserByUsername(String username);
	
	protected abstract AuthUser findAuthUserById(String id);

	protected String getUserIdAttributeKey() {
		return "id";
	}

}
