/**
 * 
 */
package com.haoyu.sip.auth.realm;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cas.CasRealm;
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
public abstract class AuthenticationCasRealm extends CasRealm implements CacheCleaner {
	@Autowired
	private IAuthRoleService authRoleService;

	@Autowired
	private IAuthPermissionService authPermissionService;

	private List<IAuthRealmHandler> authRealmHandlers;
	
	private String redisAppKey;

	public String getRedisAppKey() {
		return redisAppKey;
	}

	public void setRedisAppKey(String redisAppKey) {
		this.redisAppKey = redisAppKey;
	}

	public List<IAuthRealmHandler> getAuthRealmHandlers() {
		return authRealmHandlers;
	}

	public void setAuthRealmHandlers(List<IAuthRealmHandler> authRealmHandlers) {
		this.authRealmHandlers = authRealmHandlers;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.shiro.cas.CasRealm#doGetAuthenticationInfo(org.apache.shiro.authc.AuthenticationToken)
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		return super.doGetAuthenticationInfo(token);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.shiro.cas.CasRealm#doGetAuthorizationInfo(org.apache.shiro.subject.PrincipalCollection)
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		if (principals == null) {
			throw new AuthorizationException("principals should not be null");
		}
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		//
		List<Object> listPrincipals = principals.asList();
		Map<String, String> attributes = (Map<String, String>) listPrincipals.get(1);
		String userId = attributes.get(getUserIdAttributeKey());
		// 查询用户角色信息
		List<AuthRole> roles = authRoleService.findRoleByAuthUser(new AuthUser(userId));
		// 添加角色信息
		for (AuthRole role : roles) {
			info.addRole(role.getCode());
		}
		// 查询用户权限信息
		List<AuthPermission> permissions = authPermissionService.findPermissionByRoles(roles);
		if (permissions != null && !permissions.isEmpty()) {
			for (AuthPermission permission : permissions) {
				info.addStringPermission(String.join(permission.getResource().getCode(), ":", permission.getAction()));
			}
		}
		addAuthorize(info, principals);
		return info;
	}

	public void clearUserCacheByUsername(String username) {
		if (hasCache()) {
			List<Object> principals = getPrincipalsByUsername(username);
			SimplePrincipalCollection pc = new SimplePrincipalCollection();
			pc.addAll(principals, super.getName());
			super.clearCachedAuthorizationInfo(pc);
		}
	}

	public void clearUserCacheById(String id) {
		if (hasCache()) {
			List<Object> principals = getPrincipalsById(id);
			SimplePrincipalCollection pc = new SimplePrincipalCollection();
			pc.addAll(principals, super.getName());
			super.clearCachedAuthorizationInfo(pc);
		}
	}

	public void clearUserCacheByIds(List<String> ids) {
		if (hasCache()) {
			List<AuthUser> authUsers = this.findAuthUserByIds(ids);
			for (AuthUser au : authUsers) {
				List<Object> principals = getPrincipals(au);
				SimplePrincipalCollection pc = new SimplePrincipalCollection();
				pc.addAll(principals, super.getName());
				super.clearCachedAuthorizationInfo(pc);
			}

		}
	}

	public void clearUserCache() {
		if (hasCache()) {
			Subject subject = SecurityUtils.getSubject();
			if (subject != null) {
				SimplePrincipalCollection pc = new SimplePrincipalCollection();
				pc.addAll(subject.getPrincipals().asList(), super.getName());
				super.clearCachedAuthorizationInfo(pc);
			}
		}

	}

	public boolean hasCache() {
		return super.getAuthorizationCache() != null || (isAuthorizationCachingEnabled() && super.getCacheManager() != null);
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

	protected void addAuthorize(SimpleAuthorizationInfo info, PrincipalCollection principals) {
		if (authRealmHandlers != null && !authRealmHandlers.isEmpty()) {
			for (IAuthRealmHandler irh : authRealmHandlers) {
				if (irh != null) {
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

	protected Object getAuthorizationCacheKey(PrincipalCollection principals) {
		List<Object> listPrincipals = principals.asList();
		Map<String, String> attributes = (Map<String, String>) listPrincipals.get(1);
		String userId = attributes.get(getUserIdAttributeKey());
		if (StringUtils.isEmpty(redisAppKey)) {
			return "shiro_" + userId;
		}else{
			return redisAppKey + ":shiro_" + userId;
		}
		
	}

}
