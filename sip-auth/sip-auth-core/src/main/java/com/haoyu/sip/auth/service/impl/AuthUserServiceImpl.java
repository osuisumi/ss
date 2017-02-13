/**
 * 
 */
package com.haoyu.sip.auth.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.google.common.collect.Lists;
import com.haoyu.sip.auth.dao.IAuthUserDao;
import com.haoyu.sip.auth.dao.IAuthUserRoleDao;
import com.haoyu.sip.auth.entity.AuthMenu;
import com.haoyu.sip.auth.entity.AuthRole;
import com.haoyu.sip.auth.entity.AuthUser;
import com.haoyu.sip.auth.service.IAuthUserService;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.utils.Identities;

/**
 * @author lianghuahuang
 *
 */
@Service
public class AuthUserServiceImpl implements IAuthUserService {
	@Resource
	private IAuthUserRoleDao authUserRoleDao;
	
	@Resource
	private IAuthUserDao authUserDao;
	/* (non-Javadoc)
	 * @see com.haoyu.sip.auth.service.IAuthUserService#findAuthUserByRoleAndRelation(com.haoyu.sip.auth.entity.AuthRole, java.lang.String)
	 */
	@Override
	public List<AuthUser> findAuthUserByRoleAndRelation(AuthRole role,
			String relationId) {
		return authUserRoleDao.selectAuthUserByRoleAndRelation(role, relationId);
	}
	@Override
	public List<AuthUser> findAuthUserByRoleAndUser(AuthRole role, String userId) {
		return authUserRoleDao.findAuthUserByRoleAndUser(role, userId);
	}
	@Override
	public List<AuthMenu> findAuthMenuByUser(String userId) {
		if(StringUtils.isEmpty(userId))
			return Lists.newArrayList();
		return authUserDao.selectAuthUserMenus(userId);
	}
	
	@Override
	public List<AuthUser> findAuthUserByParameter(Map<String, Object> parameter) {
		return authUserRoleDao.findAuthUserByParameter(parameter);
	}
	@Override
	public Response createAuthUser(AuthUser authUser) {
		if(authUser==null||StringUtils.isEmpty(authUser.getUsername())){
			return Response.failInstance().responseMsg("create AuthUser fail!authUser is null or userName is null!");
		}
		
		AuthUser au = authUserDao.selectAuthUserByUsername(authUser.getUsername());
		if(au!=null){
			return Response.failInstance().responseMsg("create AuthUser fail!authUser userName exists!");
		}
		if(StringUtils.isEmpty(authUser.getId())){
			authUser.setId(Identities.uuid2());
		}
		if(StringUtils.isEmpty(authUser.getPassword())){
			authUser.setPassword(DigestUtils.md5Hex("888888"));
		}else{
			authUser.setPassword(DigestUtils.md5Hex(authUser.getPassword()));
		}
		int count = authUserDao.insertAuthUser(authUser);
		return count>0?Response.successInstance().responseData(authUser):Response.failInstance();
	}
	@Override
	public Response updateAuthUser(AuthUser authUser) {
		if(authUser==null||StringUtils.isEmpty(authUser.getId())){
			return Response.failInstance().responseMsg("update AuthUser fail!authUser is null or id is null!");
		}
		if(StringUtils.isNotEmpty(authUser.getUsername())){
			AuthUser au = authUserDao.selectAuthUserByUsername(authUser.getUsername());
			if(au!=null&&!authUser.getId().equals(au.getId())){
				return Response.failInstance().responseMsg("update AuthUser fail!authUser userName exists!");
			}
		}
		if(StringUtils.isNotEmpty(authUser.getPassword())){
			authUser.setPassword(DigestUtils.md5Hex(authUser.getPassword()));
		}
		int count = authUserDao.updateAuthUser(authUser);
		return count>0?Response.successInstance().responseData(authUser):Response.failInstance();
	}
	@Override
	public Response deleteAuthUser(AuthUser authUser) {
		if(authUser==null||StringUtils.isEmpty(authUser.getId())){
			return Response.failInstance().responseMsg("delete AuthUser fail!authUser is null or id is null!");
		}
		int count = authUserDao.deleteAuthUserByLogic(authUser);
		return count>0?Response.successInstance().responseData(authUser):Response.failInstance();
	}
	@Override
	public AuthUser findAuthUserById(String id) {
		return authUserDao.selectAuthUserById(id);
	}
	@Override
	public List<AuthUser> findAuthUsers(AuthUser authUser, PageBounds pageBounds) {
		return authUserDao.selectAuthUserForList(authUser, pageBounds);
	}
	@Override
	public List<AuthUser> findAuthUsers(Map<String, Object> parameter,
			PageBounds pageBounds) {
		return authUserDao.selectAuthUserForList(parameter, pageBounds);
	}
	@Override
	public Response grantRoleToUser(AuthUser authUser) {
		if(authUser==null||StringUtils.isEmpty(authUser.getId())){
			return Response.failInstance().responseMsg("grantRoleToUser  fail!authUser is null or id is null!");
		}
		int count = authUserRoleDao.deleteRoleUser(authUser.getId());
		if(authUser.getRoles()!=null&&!authUser.getRoles().isEmpty()){
			List<String> roleIds = Lists.newArrayList();
			for(AuthRole au:authUser.getRoles()){
				roleIds.add(au.getId());
			}
			count = authUserRoleDao.insertRoleUser(authUser, roleIds);			
		}
		return count>0?Response.successInstance().responseData(authUser):Response.failInstance();
	}
	@Override
	public Response updatePassword(String userId, String sourcePassword, String newPassword) {
		if(StringUtils.isEmpty(userId)){
			return Response.failInstance().responseMsg("updatePassword fail!userId is null");
		}
		if(StringUtils.isEmpty(sourcePassword)||StringUtils.isEmpty(newPassword)){
			return Response.failInstance().responseMsg("update Password fail!source password is null or new password is null!");
		}
		AuthUser au = authUserDao.selectAuthUserById(userId);
		if(au!=null){
			String oldPassword = au.getPassword();
			if(!oldPassword.equals(DigestUtils.md5Hex(sourcePassword))){
				return Response.failInstance().responseMsg("update Password is fail!source password is incorret!");
			}
			au.setPassword(DigestUtils.md5Hex(newPassword));
			int count = authUserDao.updateAuthUser(au);
			return count>0?Response.successInstance():Response.failInstance();
		}
		return Response.failInstance().responseMsg("update Password is fail! User is Not Found!");
	}

}
