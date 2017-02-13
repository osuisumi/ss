/**
 * 
 */
package com.haoyu.sip.auth.service;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.sip.auth.entity.AuthMenu;
import com.haoyu.sip.auth.entity.AuthRole;
import com.haoyu.sip.core.service.Response;

/**
 * @author lianghuahuang
 *
 */
public interface IAuthMenuService {
	
	Response createMenu(AuthMenu menu);
	
	Response updateMenu(AuthMenu menu);
	
	Response deleteMenu(AuthMenu menu);
	
	AuthMenu findMenuById(String id);
	
	List<AuthMenu> findMenuByNameAndRelation(String name,String relationId,PageBounds pageBounds);
	
	List<AuthMenu> findMenuByRoles(List<AuthRole> roles,boolean getMenuTree);
	
	List<AuthMenu> findMenuByParent(String parentMenuId,boolean getMenuTree);
	
	Response addRolesToMenu(AuthMenu menu,List<String> roleIds);
	
	Response removeRolesFromMenu(AuthMenu menu,List<String> roleIds);
	
	Response updateMenuRoles(AuthMenu menu,List<String> roleIds);
	
	List<AuthMenu> findMenu(AuthMenu authMenu,boolean getMenuTree);
	
	List<AuthMenu> findMenu(Map<String,Object> parameter,boolean getMenuTree);
	
	Response batchDeleteByIds(String ids);
	
	Response deleteMenuByPhysics(AuthMenu menu);
	
}
