/**
 * 
 */
package com.haoyu.sip.auth.service;

import java.util.List;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.sip.auth.entity.AuthPermission;
import com.haoyu.sip.auth.entity.AuthResource;
import com.haoyu.sip.auth.entity.AuthRole;
import com.haoyu.sip.core.service.Response;

/**
 * @author lianghuahuang
 *
 */
public interface IAuthPermissionService {
	
	Response createPermission(AuthPermission permission);
	
	Response updatePermission(AuthPermission permission);
	
	Response deletePermission(AuthPermission permission);
	
	AuthPermission findPermissionById(String id);
	
	List<AuthPermission> findPermissionByResource(AuthResource resource,PageBounds pageBounds);
	
	List<AuthPermission> findPermissionByRoles(List<AuthRole> roles);
	
	Response addRolesToPermission(AuthPermission permission,List<String> roleIds);
	
	Response removeRolesFromPermission(AuthPermission permission,List<String> roleIds);
	
	Response updatePermissionRoles(AuthPermission permission,List<String> roleIds);
	
	Response batchDeleteByIds(String ids);
	
	Response batchDeleteByIds(List<String> ids);
	
	List<AuthPermission> list(AuthPermission permission,PageBounds pageBounds);
	
	
}
