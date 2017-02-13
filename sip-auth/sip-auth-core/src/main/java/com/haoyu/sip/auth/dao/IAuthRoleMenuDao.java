/**
 * 
 */
package com.haoyu.sip.auth.dao;

import java.util.List;
import java.util.Map;

import com.haoyu.sip.auth.entity.AuthMenu;
import com.haoyu.sip.auth.entity.AuthRole;

/**
 * @author lianghuahuang
 *
 */
public interface IAuthRoleMenuDao {
	
	int insertRoleMenu(AuthMenu menu,List<String> roleIds);
	
	int insertRoleMenu(AuthRole role,List<String> menuIds);
	
	int deleteRoleMenu(AuthMenu menu,List<String> roleIds);
	
	int deleteRoleMenu(AuthRole role,List<String> menuIds);
	
	int deleteRoleMenu(AuthMenu menu);
	
	int deleteRoleMenu(AuthRole role);
	
	int deleteRoleMenu(Map<String,Object> param);
}
