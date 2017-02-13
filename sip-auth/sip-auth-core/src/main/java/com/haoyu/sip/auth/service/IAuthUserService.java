/**
 * 
 */
package com.haoyu.sip.auth.service;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.sip.auth.entity.AuthMenu;
import com.haoyu.sip.auth.entity.AuthRole;
import com.haoyu.sip.auth.entity.AuthUser;
import com.haoyu.sip.core.service.Response;

/**
 * @author lianghuahuang
 *
 */
public interface IAuthUserService {
	
	List<AuthUser> findAuthUserByRoleAndRelation(AuthRole role,String relationId);
	
	List<AuthUser> findAuthUserByRoleAndUser(AuthRole role,String userId);
		
	List<AuthMenu> findAuthMenuByUser(String userId);
	
	AuthUser findAuthUserById(String id);
	
	List<AuthUser> findAuthUserByParameter(Map<String,Object> parameter);
	
	List<AuthUser>  findAuthUsers(AuthUser authUser,PageBounds pageBounds);
	
	List<AuthUser>  findAuthUsers(Map<String,Object> parameter,PageBounds pageBounds);
	
	Response createAuthUser(AuthUser authUser);
	
	Response updateAuthUser(AuthUser authUser);
	
	Response deleteAuthUser(AuthUser authUser);
	
	Response grantRoleToUser(AuthUser authUser);
	
	Response updatePassword(String userId,String sourcePassword,String newPassword);
	
}
