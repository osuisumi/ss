/**
 * 
 */
package com.haoyu.sip.auth.service.impl;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.sip.auth.dao.IAuthPermissionDao;
import com.haoyu.sip.auth.dao.IAuthRolePermissionDao;
import com.haoyu.sip.auth.entity.AuthPermission;
import com.haoyu.sip.auth.entity.AuthResource;
import com.haoyu.sip.auth.entity.AuthRole;
import com.haoyu.sip.auth.event.BatchDeleteAuthPermissionEvent;
import com.haoyu.sip.auth.service.IAuthPermissionService;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.utils.Identities;

/**
 * @author lianghuahuang
 *
 */
@Service
public class AuthPermissionServiceImpl implements IAuthPermissionService {
	@Autowired
	private IAuthPermissionDao authPermissionDao;
	
	@Autowired
	private IAuthRolePermissionDao authRolePermissionDao;
	@Autowired
	private ApplicationContext applicationContext;
	
	/* (non-Javadoc)
	 * @see com.haoyu.sip.auth.service.IPermissionService#createPermission(com.haoyu.sip.auth.entity.Permission)
	 */
	@Override
	public Response createPermission(AuthPermission permission) {
		if(permission==null)
			return Response.failInstance().responseMsg("createPermission fail! permission is null");
		if(StringUtils.isEmpty(permission.getId())){
			permission.setId(Identities.uuid2());
		}
		int count = authPermissionDao.insertPermission(permission);
		return count>0?Response.successInstance().responseData(permission):Response.failInstance().responseMsg("createPermission fail!");
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.auth.service.IPermissionService#updatePermission(com.haoyu.sip.auth.entity.Permission)
	 */
	@Override
	public Response updatePermission(AuthPermission permission) {
		if(permission==null||StringUtils.isEmpty(permission.getId())){
			return Response.failInstance().responseMsg("updatePermission is fail!permission is null or permission's id is null");
		}
		int count = authPermissionDao.updatePermission(permission);
		return count>0?Response.successInstance().responseData(permission):Response.failInstance().responseMsg("updatePermission fail!");
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.auth.service.IPermissionService#deletePermission(com.haoyu.sip.auth.entity.Permission)
	 */
	@Override
	public Response deletePermission(AuthPermission permission) {
		if(permission==null||(StringUtils.isEmpty(permission.getId()))){
			return Response.failInstance().responseMsg("deletePermission is fail!permission is null or permission's id  is null");
		}
		int count = authPermissionDao.deletePermissionByLogic(permission);
		return count>0?Response.successInstance():Response.failInstance().responseMsg("deletePermission fail!");
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.auth.service.IPermissionService#findPermissionById(java.lang.String)
	 */
	@Override
	public AuthPermission findPermissionById(String id) {
		return authPermissionDao.selectPermissionById(id);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.auth.service.IPermissionService#findPermissionByResource(com.haoyu.sip.auth.entity.Resource, com.github.miemiedev.mybatis.paginator.domain.PageBounds)
	 */
	@Override
	public List<AuthPermission> findPermissionByResource(AuthResource resource,
			PageBounds pageBounds) {
		if(resource==null)
			return null;
		AuthPermission permission  = new AuthPermission();
		permission.setResource(resource);
		return authPermissionDao.selectPermissionForList(permission, pageBounds);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.auth.service.IPermissionService#findPermissionByRoles(java.util.List)
	 */
	@Override
	public List<AuthPermission> findPermissionByRoles(List<AuthRole> roles) {
		if(roles==null||roles.isEmpty())
			return null;
		AuthPermission permission  = new AuthPermission();
		permission.setRoles(roles);
		return authPermissionDao.selectPermissionForList(permission, null);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.auth.service.IPermissionService#addRolesToPermission(com.haoyu.sip.auth.entity.Permission, java.util.List)
	 */
	@Override
	public Response addRolesToPermission(AuthPermission permission,
			List<String> roleIds) {
		if(permission==null||StringUtils.isEmpty(permission.getId())||roleIds==null||roleIds.isEmpty())
			return Response.failInstance().responseMsg("addRolesToPermission fail!permission or permission's id or roleIds is null");
		int count = authRolePermissionDao.insertRolePermission(permission, roleIds);
		return count>0?Response.successInstance().responseData(roleIds):Response.failInstance().responseMsg("addRolesToPermission fail!");
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.auth.service.IPermissionService#removeRolesFromPermission(com.haoyu.sip.auth.entity.Permission, java.util.List)
	 */
	@Override
	public Response removeRolesFromPermission(AuthPermission permission,
			List<String> roleIds) {
		if(permission==null||StringUtils.isEmpty(permission.getId())||roleIds==null||roleIds.isEmpty())
			return Response.failInstance().responseMsg("removeRolesFromPermission fail!permission or permission's id or roleIds is null");
		int count = authRolePermissionDao.deleteRolePermission(permission, roleIds);
		return count>0?Response.successInstance().responseData(roleIds):Response.failInstance().responseMsg("deleteRolesToPermission fail!");
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.auth.service.IPermissionService#updatePermissionRoles(com.haoyu.sip.auth.entity.Permission, java.util.List)
	 */
	@Override
	public Response updatePermissionRoles(AuthPermission permission,
			List<String> roleIds) {
		if(permission==null||StringUtils.isEmpty(permission.getId())||roleIds==null||roleIds.isEmpty())
			return Response.failInstance().responseMsg("updatePermissionRoles fail!permission or permission's id or roleIds is null");
		//先删除Permission下对应的role信息，然后再添加相应的role关联信息
		authRolePermissionDao.deleteRolePermission(permission);
		int count = authRolePermissionDao.insertRolePermission(permission, roleIds);
		return count>0?Response.successInstance().responseData(roleIds):Response.failInstance().responseMsg("updatePermissionRoles fail!");
	}

	@Override
	public Response batchDeleteByIds(String ids) {
		return this.batchDeleteByIds(Arrays.asList(ids.split(",")));
	}

	@Override
	public List<AuthPermission> list(AuthPermission permission, PageBounds pageBounds) {
		return authPermissionDao.selectPermissionForList(permission, pageBounds);
	}

	@Override
	public Response batchDeleteByIds(List<String> ids) {
		if(CollectionUtils.isEmpty(ids)){
			return Response.failInstance().responseMsg("batchDeletePermission fail! param ids is null");
		}
		int count = this.authPermissionDao.batchDeleteByIds(ids);
		if(count>0){
			applicationContext.publishEvent(new BatchDeleteAuthPermissionEvent(ids) );
			return Response.successInstance();
		}else{
			return Response.failInstance();
		}
	}

}
