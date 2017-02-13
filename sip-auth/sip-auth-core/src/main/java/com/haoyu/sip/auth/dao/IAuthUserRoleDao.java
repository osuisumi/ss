/**
 * 
 */
package com.haoyu.sip.auth.dao;

import java.util.List;
import java.util.Map;

import com.haoyu.sip.auth.entity.AuthRole;
import com.haoyu.sip.auth.entity.AuthUser;

/**
 * @author lianghuahuang
 *
 */
public interface IAuthUserRoleDao {
	
	int insertRoleUser(AuthRole authRole,List<String> userIds,String relationId);
	
	int insertRoleUser(AuthUser authUser,List<String> roleIds);
	
	int deleteRoleUser(AuthRole authRole, List<String> userIds,String relationId);
	
	int deleteRoleUser(AuthUser authUser,List<String> roleIds);
	
	int deleteRoleUser(String userId);
	
	List<AuthUser> selectAuthUserByRoleAndRelation(AuthRole authRole,String relationId);

	List<AuthUser> findAuthUserByRoleAndUser(AuthRole role, String userId);
	
	List<AuthUser> findAuthUserByParameter(Map<String,Object> parameter);
	
	int deleteRoleUser(Map<String,Object> param);
}
