/**
 * 
 */
package com.haoyu.sip.auth.service;

import java.util.List;

import com.haoyu.sip.auth.entity.AuthUser;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.sip.auth.entity.AuthMenu;
import com.haoyu.sip.auth.entity.AuthPermission;
import com.haoyu.sip.auth.entity.AuthRole;
import com.haoyu.sip.core.service.Response;

/**
 * @author lianghuahuang
 *
 */
public interface IAuthRoleService {
	Response createRole(AuthRole role);
	
	Response updateRole(AuthRole role);
	
	Response deleteRole(AuthRole role);
	
	AuthRole findRoleById(String id);
	
	List<AuthRole> findRoleByNameAndRelation(String name,String relationId);
	
	List<AuthRole> findRoleByNameAndRelation(String name,String relationId,PageBounds pageBounds);
	
	List<AuthRole> findRoleByPermission(AuthPermission permission);
	
	List<AuthRole> findRoleByMenu(AuthMenu menu);
	
	List<AuthRole> findRoleByAuthUser(AuthUser authUser);
	
	List<AuthRole> findRoleByAuthUser(AuthUser authUser,PageBounds pageBounds);
	
	Response addMenusToRole(AuthRole role,List<String> menuIds);
	
	Response addPermissionToRole(AuthRole role,List<String> permissionIds);
	
	Response addUsersToRole(AuthRole role,List<String> userIds,String relationId);
	
	Response removeUsersFromRole(AuthRole role,List<String> userIds,String relationId);
	
	Response updateAuthRoleUsers(AuthRole role,List<String> userIds,String relationId);
	
	Response removeMenusFromRole(AuthRole role,List<String> menuIds);
	
	List<AuthRole> list(AuthRole authRole,PageBounds pageBounds);
	
	Response batchDeleteByIds(String ids);
	
	Response grantMenuToRole(AuthRole role);
	
	Response grantPermissionToRole(AuthRole role);
}
