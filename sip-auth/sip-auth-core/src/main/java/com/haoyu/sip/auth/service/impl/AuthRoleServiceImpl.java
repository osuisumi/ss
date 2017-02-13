/**
 * 
 */
package com.haoyu.sip.auth.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.google.common.collect.Lists;
import com.haoyu.sip.auth.dao.IAuthRoleDao;
import com.haoyu.sip.auth.dao.IAuthRoleMenuDao;
import com.haoyu.sip.auth.dao.IAuthRolePermissionDao;
import com.haoyu.sip.auth.dao.IAuthUserRoleDao;
import com.haoyu.sip.auth.entity.AuthUser;
import com.haoyu.sip.auth.event.BatchDeleteAuthRoleEvent;
import com.haoyu.sip.auth.entity.AuthMenu;
import com.haoyu.sip.auth.entity.AuthPermission;
import com.haoyu.sip.auth.entity.AuthResource;
import com.haoyu.sip.auth.entity.AuthRole;
import com.haoyu.sip.auth.realm.CacheCleaner;
import com.haoyu.sip.auth.service.IAuthResourceService;
import com.haoyu.sip.auth.service.IAuthRoleService;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.utils.Identities;

/**
 * @author lianghuahuang
 *
 */
@Service
public class AuthRoleServiceImpl implements IAuthRoleService {
	@Resource
	private IAuthRoleDao authRoleDao;

	@Resource
	private IAuthRoleMenuDao authRoleMenuDao;

	@Resource
	private IAuthUserRoleDao authUserRoleDao;

	@Resource
	private CacheCleaner authRealm;

	@Resource
	private IAuthRolePermissionDao authRolePermissionDao;

	@Resource
	private ApplicationContext applicationContext;
	
	@Resource
	private IAuthResourceService authResourceService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.haoyu.sip.auth.service.IRoleService#createRole(com.haoyu.sip.auth.entity.Role)
	 */
	@Override
	public Response createRole(AuthRole role) {
		if (role == null) {
			return Response.failInstance().responseMsg("createRole fail!role is null!");
		}
		if (StringUtils.isEmpty(role.getId())) {
			role.setId(Identities.uuid2());
		}
		int count = authRoleDao.insertRole(role);
		return count > 0 ? Response.successInstance().responseData(role) : Response.failInstance().responseMsg("createRole fail!");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.haoyu.sip.auth.service.IRoleService#updateRole(com.haoyu.sip.auth.entity.Role)
	 */
	@Override
	public Response updateRole(AuthRole role) {
		if (role == null || StringUtils.isEmpty(role.getId())) {
			return Response.failInstance().responseMsg("updateRole is fail!role is null or role's id is null");
		}
		int count = authRoleDao.updateRole(role);
		return count > 0 ? Response.successInstance().responseData(role) : Response.failInstance().responseMsg("updateRole fail!");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.haoyu.sip.auth.service.IRoleService#deleteRole(com.haoyu.sip.auth.entity.Role)
	 */
	@Override
	public Response deleteRole(AuthRole role) {
		if (role == null || (StringUtils.isEmpty(role.getId()) && StringUtils.isEmpty(role.getRelationId()))) {
			return Response.failInstance().responseMsg("deleteRole is fail!role is null or role's id and role's relationId  is null");
		}
		int count = authRoleDao.deleteRoleByLogic(role);
		return count > 0 ? Response.successInstance() : Response.failInstance().responseMsg("deleteRole fail!");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.haoyu.sip.auth.service.IRoleService#findRoleById(java.lang.String)
	 */
	@Override
	public AuthRole findRoleById(String id) {
		return authRoleDao.selectRoleById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.haoyu.sip.auth.service.IRoleService#findRoleByNameAndRelation(java.lang.String, java.lang.String)
	 */
	@Override
	public List<AuthRole> findRoleByNameAndRelation(String name, String relationId) {
		return findRoleByNameAndRelation(name,relationId,null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.haoyu.sip.auth.service.IRoleService#findRoleByPermission(com.haoyu.sip.auth.entity.Permission)
	 */
	@Override
	public List<AuthRole> findRoleByPermission(AuthPermission permission) {
		if (permission == null)
			return null;
		AuthRole role = new AuthRole();
		role.setPermissions(Lists.newArrayList(permission));
		return authRoleDao.selectRoleForList(role, null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.haoyu.sip.auth.service.IRoleService#findRoleByMenu(com.haoyu.sip.auth.entity.Menu)
	 */
	@Override
	public List<AuthRole> findRoleByMenu(AuthMenu menu) {
		if (menu == null)
			return null;
		AuthRole role = new AuthRole();
		role.setMenus(Lists.newArrayList(menu));
		return authRoleDao.selectRoleForList(role, null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.haoyu.sip.auth.service.IRoleService#findRoleByAuthUser(com.haoyu.sip.auth.entity.AuthUser)
	 */
	@Override
	public List<AuthRole> findRoleByAuthUser(AuthUser authUser) {
		return findRoleByAuthUser(authUser,null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.haoyu.sip.auth.service.IRoleService#addMenusToRole(com.haoyu.sip.auth.entity.Role, java.util.List)
	 */
	@Override
	public Response addMenusToRole(AuthRole role, List<String> menuIds) {
		if (role == null || StringUtils.isEmpty(role.getId()) || menuIds == null || menuIds.isEmpty())
			return Response.failInstance().responseMsg("addMenusToRole fail!role or role's id or menuIds is null");
		int count = authRoleMenuDao.insertRoleMenu(role, menuIds);
		return count > 0 ? Response.successInstance().responseData(menuIds) : Response.failInstance().responseMsg("addMenusToRole fail!");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.haoyu.sip.auth.service.IRoleService#removeMenusFromRole(com.haoyu.sip.auth.entity.Role, java.util.List)
	 */
	@Override
	public Response removeMenusFromRole(AuthRole role, List<String> menuIds) {
		if (role == null || StringUtils.isEmpty(role.getId()) || menuIds == null || menuIds.isEmpty())
			return Response.failInstance().responseMsg("removeMenusFromRole fail!role or role's id or menuIds is null");
		int count = authRoleMenuDao.deleteRoleMenu(role, menuIds);
		return count > 0 ? Response.successInstance().responseData(menuIds) : Response.failInstance().responseMsg("removeMenusFromRole fail!");
	}

	@Override
	public Response addUsersToRole(AuthRole role, List<String> userIds, String relationId) {
		if (role == null || StringUtils.isEmpty(role.getId()) || userIds == null || userIds.isEmpty())
			return Response.failInstance().responseMsg("addUsersToRole fail!role or role's id or userIds is null");
		int count = authUserRoleDao.insertRoleUser(role, userIds, relationId);
		return count > 0 ? Response.successInstance().responseData(userIds) : Response.failInstance();
	}

	@Override
	public Response removeUsersFromRole(AuthRole role, List<String> userIds, String relationId) {
		if (userIds == null || userIds.isEmpty())
			return Response.failInstance().responseMsg("removeUsersFromRole fail!role or role's id or userIds is null");
		int count = authUserRoleDao.deleteRoleUser(role, userIds, relationId);
		if (count > 0) {
			authRealm.clearUserCacheByIds(userIds);
		}
		return count > 0 ? Response.successInstance() : Response.failInstance();
	}

	@Override
	public Response updateAuthRoleUsers(AuthRole role, List<String> userIds, String relationId) {
		if (role == null || StringUtils.isEmpty(role.getId()) || userIds == null || userIds.isEmpty())
			return Response.failInstance().responseMsg("updateAuthRoleUsers fail!role or role's id or userIds is null");
		List<String> ids = Lists.newArrayList();
		if (authRealm != null && authRealm.hasCache()) {
			List<AuthUser> users = authUserRoleDao.selectAuthUserByRoleAndRelation(role, relationId);
			if (users != null && !users.isEmpty()) {
				for (AuthUser au : users) {
					if (!userIds.contains(au.getId())) {
						ids.add(au.getId());
					}
				}
			}
		}
		authUserRoleDao.deleteRoleUser(role, null, relationId);
		int count = authUserRoleDao.insertRoleUser(role, userIds, relationId);
		if (count > 0) {
			ids.addAll(userIds);
			if (authRealm != null && authRealm.hasCache()) {
				authRealm.clearUserCacheByIds(ids);
			}
		}
		return count > 0 ? Response.successInstance().responseData(userIds) : Response.failInstance();
	}

	@Override
	public List<AuthRole> list(AuthRole authRole, PageBounds pageBounds) {
		return this.authRoleDao.selectRoleForList(authRole, pageBounds);
	}

	@Override
	public Response batchDeleteByIds(String ids) {
		if (StringUtils.isEmpty(ids)) {
			return Response.failInstance().responseMsg("batchDeleteRole fail! param ids is null");
		}
		String[] idArray = ids.split(",");
		int count = this.authRoleDao.batchDeleteByIds(Arrays.asList(idArray));
		if (count > 0) {
			applicationContext.publishEvent(new BatchDeleteAuthRoleEvent(Arrays.asList(idArray)));
			return Response.successInstance();
		} else {
			return Response.failInstance();
		}
	}

	@Override
	public Response addPermissionToRole(AuthRole role, List<String> permissionIds) {
		if (role == null || StringUtils.isEmpty(role.getId()) || permissionIds == null || permissionIds.isEmpty())
			return Response.failInstance().responseMsg("addPermissionsToRole fail!role or role's id or permissionIds is null");
		int count = authRolePermissionDao.insertRolePermission(role, permissionIds);
		return count > 0 ? Response.successInstance().responseData(permissionIds) : Response.failInstance().responseMsg("addPermissionsToRole fail!");
	}

	@Override
	public Response grantMenuToRole(AuthRole role) {
		// 授权前删除旧的权限
		authRoleMenuDao.deleteRoleMenu(new AuthRole(role.getId()));
		
		Response response = Response.successInstance();
		if(role.getMenus()!=null && role.getMenus().size()>0){
			List<String> menuIds = new ArrayList<String>();
			for (AuthMenu m : role.getMenus()) {
				menuIds.add(m.getId());
			}
			if (menuIds.size() > 0) {
				response = this.addMenusToRole(role, menuIds);
			}
		}
		return response;

	}

	@Override
	public Response grantPermissionToRole(AuthRole role) {
		authRolePermissionDao.deleteRolePermission(new AuthRole(role.getId()));
		List<String> permissionIds = Lists.newArrayList();
		if(role.getPermissions()!=null&&!role.getPermissions().isEmpty()){
			for(AuthPermission ap:role.getPermissions()){
				permissionIds.add(ap.getId());
			}
			this.addPermissionToRole(role, permissionIds);
		}
		return Response.successInstance();
		
	}

	@Override
	public List<AuthRole> findRoleByNameAndRelation(String name, String relationId, PageBounds pageBounds) {
		AuthRole role = new AuthRole();
		if (StringUtils.isNotEmpty(name))
			role.setName(name);
		if (StringUtils.isNotEmpty(relationId))
			role.setRelationId(relationId);
		return authRoleDao.selectRoleForList(role, pageBounds);
	}

	@Override
	public List<AuthRole> findRoleByAuthUser(AuthUser authUser, PageBounds pageBounds) {
		if (authUser == null)
			return null;
		AuthRole role = new AuthRole();
		role.setAuthUsers(Lists.newArrayList(authUser));
		return authRoleDao.selectRoleForList(role, pageBounds);
	}

}
