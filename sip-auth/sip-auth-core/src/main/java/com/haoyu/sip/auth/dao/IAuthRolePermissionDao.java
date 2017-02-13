/**
 * 
 */
package com.haoyu.sip.auth.dao;

import java.util.List;
import java.util.Map;

import com.haoyu.sip.auth.entity.AuthPermission;
import com.haoyu.sip.auth.entity.AuthRole;

/**
 * @author lianghuahuang
 *
 */
public interface IAuthRolePermissionDao {
	
	int insertRolePermission(AuthPermission permission,List<String> roleIds);
	
	int insertRolePermission(AuthRole role,List<String> permissionIds);
	
	int deleteRolePermission(AuthPermission permission,List<String> roleIds);
	
	int deleteRolePermission(AuthRole role,List<String> permissionIds);
	
	int deleteRolePermission(AuthPermission permission);
	
	int deleteRolePermission(AuthRole role);
	
	int deleteRolePermission(Map<String,Object> param);
}
