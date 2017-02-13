/**
 * 
 */
package com.haoyu.sip.auth.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.haoyu.sip.auth.dao.IAuthMenuDao;
import com.haoyu.sip.auth.dao.IAuthRoleMenuDao;
import com.haoyu.sip.auth.entity.AuthMenu;
import com.haoyu.sip.auth.entity.AuthRole;
import com.haoyu.sip.auth.event.BatchDeleteAuthMenuEvent;
import com.haoyu.sip.auth.service.IAuthMenuService;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.utils.Identities;

/**
 * @author lianghuahuang
 *
 */
@Service
public class AuthMenuServiceImpl implements IAuthMenuService {
	@Autowired
	private IAuthMenuDao authMenuDao;

	@Autowired
	private IAuthRoleMenuDao authRoleMenuDao;

	@Autowired
	private ApplicationContext applicationContext;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.haoyu.sip.auth.service.IMenuService#createMenu(com.haoyu.sip.auth.entity.Menu)
	 */
	@Override
	public Response createMenu(AuthMenu menu) {
		if (menu == null) {
			return Response.failInstance().responseMsg("createMenu fail!menu is null!");
		}
		if (StringUtils.isEmpty(menu.getId())) {
			menu.setId(Identities.uuid2());
		}
		int count = authMenuDao.insertMenu(menu);
		return count > 0 ? Response.successInstance().responseData(menu) : Response.failInstance().responseMsg("createMenu fail!");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.haoyu.sip.auth.service.IMenuService#updateMenu(com.haoyu.sip.auth.entity.Menu)
	 */
	@Override
	public Response updateMenu(AuthMenu menu) {
		if (menu == null || StringUtils.isEmpty(menu.getId())) {
			return Response.failInstance().responseMsg("updateMenu is fail!menu is null or menu's id is null");
		}
		if(menu.getParent()!=null &&StringUtils.isNotEmpty(menu.getParent().getId())){
			if(menu.getId().equals(menu.getParent().getId())){
				return Response.failInstance().responseMsg("updateMenu is fail!parent equils itself");
			}
		}
		int count = authMenuDao.updateMenu(menu);
		return count > 0 ? Response.successInstance().responseData(menu) : Response.failInstance().responseMsg("updateMenu fail!");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.haoyu.sip.auth.service.IMenuService#deleteMenu(com.haoyu.sip.auth.entity.Menu)
	 */
	@Override
	public Response deleteMenu(AuthMenu menu) {
		if (menu == null || (StringUtils.isEmpty(menu.getId()) && StringUtils.isEmpty(menu.getRelationId()))) {
			return Response.failInstance().responseMsg("deleteMenu is fail!menu is null or menu's id and menu's relationId  is null");
		}
		int count = authMenuDao.deleteMenuByLogic(menu);
		return count > 0 ? Response.successInstance() : Response.failInstance().responseMsg("deleteMenu fail!");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.haoyu.sip.auth.service.IMenuService#findMenuById(java.lang.String)
	 */
	@Override
	public AuthMenu findMenuById(String id) {
		return authMenuDao.selectMenuById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.haoyu.sip.auth.service.IMenuService#findMenuByNameAndRelation(java.lang.String, java.lang.String, com.github.miemiedev.mybatis.paginator.domain.PageBounds)
	 */
	@Override
	public List<AuthMenu> findMenuByNameAndRelation(String name, String relationId, PageBounds pageBounds) {
		Map<String,Object> parameter = Maps.newHashMap();
		if (StringUtils.isNotEmpty(name))
			parameter.put("name",name);
		if (StringUtils.isNotEmpty(relationId))
			parameter.put("relationId",relationId);
		return authMenuDao.selectMenuForList(parameter, pageBounds);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.haoyu.sip.auth.service.IMenuService#findMenuByRoles(java.util.List)
	 */
	@Override
	public List<AuthMenu> findMenuByRoles(List<AuthRole> roles, boolean getMenuTree) {
		if (roles == null || roles.isEmpty())
			return null;	
		Map<String,Object> parameter = Maps.newHashMap();
		parameter.put("roles",roles);
		if (getMenuTree) {			
			Map<String, AuthMenu> menuMap = authMenuDao.selectMenuForMap(parameter, null);
			return this.getMenuTree(menuMap);
		} else {
			return authMenuDao.selectMenuForList(parameter, null);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.haoyu.sip.auth.service.IMenuService#findMenuByParent(java.lang.String)
	 */
	@Override
	public List<AuthMenu> findMenuByParent(String parentMenuId, boolean getMenuTree) {
		if (StringUtils.isEmpty(parentMenuId))
			return null;	
		Map<String,Object> parameter = Maps.newHashMap();
		parameter.put("parent",new AuthMenu(parentMenuId));
		if (getMenuTree) {
			
			Map<String, AuthMenu> menuMap = authMenuDao.selectMenuForMap(parameter, null);
			return this.getMenuTree(menuMap);
		} else {
			return authMenuDao.selectMenuForList(parameter, null);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.haoyu.sip.auth.service.IMenuService#addRolesToMenu(com.haoyu.sip.auth.entity.Menu, java.util.List)
	 */
	@Override
	public Response addRolesToMenu(AuthMenu menu, List<String> roleIds) {
		if (menu == null || StringUtils.isEmpty(menu.getId()) || roleIds == null || roleIds.isEmpty())
			return Response.failInstance().responseMsg("addRolesToMenu fail!menu or menu's id or roleIds is null");
		int count = authRoleMenuDao.insertRoleMenu(menu, roleIds);
		return count > 0 ? Response.successInstance().responseData(roleIds) : Response.failInstance().responseMsg("addRolesToMenu fail!");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.haoyu.sip.auth.service.IMenuService#removeRolesFromMenu(com.haoyu.sip.auth.entity.Menu, java.util.List)
	 */
	@Override
	public Response removeRolesFromMenu(AuthMenu menu, List<String> roleIds) {
		if (menu == null || StringUtils.isEmpty(menu.getId()) || roleIds == null || roleIds.isEmpty())
			return Response.failInstance().responseMsg("removeRolesFromMenu fail!menu or menu's id or roleIds is null");
		int count = authRoleMenuDao.deleteRoleMenu(menu, roleIds);
		return count > 0 ? Response.successInstance().responseData(roleIds) : Response.failInstance().responseMsg("removeRolesFromMenu fail!");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.haoyu.sip.auth.service.IMenuService#updateMenuRoles(com.haoyu.sip.auth.entity.Menu, java.util.List)
	 */
	@Override
	public Response updateMenuRoles(AuthMenu menu, List<String> roleIds) {
		if (menu == null || StringUtils.isEmpty(menu.getId()) || roleIds == null || roleIds.isEmpty())
			return Response.failInstance().responseMsg("updateMenuRoles fail!menu or menu's id or roleIds is null");
		// 先删除Menu下对应的role信息，然后再添加相应的role关联信息
		authRoleMenuDao.deleteRoleMenu(menu);
		int count = authRoleMenuDao.insertRoleMenu(menu, roleIds);
		return count > 0 ? Response.successInstance().responseData(roleIds) : Response.failInstance().responseMsg("updateMenuRoles fail!");
	}

	private List<AuthMenu> getMenuTree(Map<String, AuthMenu> menuMap) {
		List<AuthMenu> menus = Lists.newArrayList();
		for (Map.Entry<String, AuthMenu> entry : menuMap.entrySet()) {
			AuthMenu menu = entry.getValue();
			if (menu.getParent() == null) {
				menus.add(menu);
			} else {
				setParent(menu, menuMap);
			}
		}
		sortMenus(menus);
		return menus;
	}

	private void setParent(AuthMenu menu, Map<String, AuthMenu> menuMap) {
		if (menu.getParent() != null) {
			String parentId = menu.getParent().getId();
			AuthMenu parentMenu = menuMap.get(parentId);
			parentMenu.addChild(menu);
			setParent(parentMenu, menuMap);
		}
	}

	private void sortMenus(List<AuthMenu> menus) {
		// 按照排序号进行排序
		Collections.sort(menus, (first, second) -> Integer.compare(first.getOrderNo(), second.getOrderNo()));
		for (AuthMenu menu : menus) {
			menu.sortChildren();
		}
	}

	@Override
	public List<AuthMenu> findMenu(AuthMenu authMenu, boolean getMenuTree) {
		Map<String,Object> parameter = Maps.newHashMap();
		if(StringUtils.isNotEmpty(authMenu.getName())){
			parameter.put("name",authMenu.getName());
		}
		if(StringUtils.isNotEmpty(authMenu.getRelationId())){
			parameter.put("relationId",authMenu.getRelationId());
		}
		if(authMenu.getParent()!=null){
			parameter.put("parent",authMenu.getParent());
		}
		if(authMenu.getRoles()!=null&&!authMenu.getRoles().isEmpty()){
			parameter.put("roles",authMenu.getRoles());
		}
		if (getMenuTree) {			
			Map<String, AuthMenu> menuMap = authMenuDao.selectMenuForMap(parameter, null);
			return this.getMenuTree(menuMap);
		} else {
			return authMenuDao.selectMenuForList(parameter, null);
		}
	}
	

	@Override
	public List<AuthMenu> findMenu(Map<String, Object> parameter,
			boolean getMenuTree) {
		Map<String, AuthMenu> menuMap = authMenuDao.selectMenuForMap(parameter, null);
		return this.getMenuTree(menuMap);
	}


	@Override
	public Response batchDeleteByIds(String ids) {
		if (StringUtils.isEmpty(ids)) {
			return Response.failInstance().responseMsg("batchDeleteMenu fail! param ids is null");
		}
		//根据ids组装待删除的树的根节点
		List<AuthMenu> deleteTreeRoots = this.getTreeByIds(Arrays.asList(ids.split(",")));
		
		//获取所有待删除的resource的id
		List<String> prepareDeleteIds = new ArrayList<String>();


		for (AuthMenu m : deleteTreeRoots) {
			List<String> result = new ArrayList<String>();
			result = m.getTreeAllMenuId(result);
			prepareDeleteIds.addAll(result);
		}

		int count = this.authMenuDao.batchDeleteByIds(prepareDeleteIds);
		if (count > 0) {
			applicationContext.publishEvent(new BatchDeleteAuthMenuEvent(deleteTreeRoots));
			return Response.successInstance();
		} else {
			return Response.failInstance();
		}
	}

	
	@Override
	public Response deleteMenuByPhysics(AuthMenu menu) {
		return this.authMenuDao.deleteMenuByPhysics(menu.getId()) > 0 ? Response.successInstance() : Response.failInstance();
	}
	
	private List<AuthMenu> getTreeByIds(List<String> ids){
		List<AuthMenu> result = new ArrayList<AuthMenu>();
		List<AuthMenu> menus = this.findMenu(new AuthMenu(), true);
		//从多个树中，根据ids 获取对应的子树
		for(String id:ids){
			for(AuthMenu m:menus){
				AuthMenu rm = m.getChildTreeById(id);
				if(rm!=null){
					result.add(rm);
				}
			}
		}
		return result;
		
	}

}
