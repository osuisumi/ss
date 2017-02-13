/**
 * 
 */
package com.haoyu.sip.auth.dao.impl.mybatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.google.common.collect.Maps;
import com.haoyu.sip.auth.dao.IAuthRolePermissionDao;
import com.haoyu.sip.auth.entity.AuthPermission;
import com.haoyu.sip.auth.entity.AuthRole;
import com.haoyu.sip.core.jdbc.MybatisDao;

/**
 * @author lianghuahuang
 *
 */
@Repository
public class AuthRolePermissionDao extends MybatisDao implements IAuthRolePermissionDao {

	/* (non-Javadoc)
	 * @see com.haoyu.sip.auth.dao.IRolePermissionDao#insertRolePermission(com.haoyu.sip.auth.entity.Permission, java.util.List)
	 */
	@Override
	public int insertRolePermission(AuthPermission permission, List<String> roleIds) {
		Map<String,Object> param = Maps.newHashMap();
		if(permission!=null&&permission.getId()!=null&&roleIds!=null&&!roleIds.isEmpty()){
			param.put("permission", permission);
			param.put("roleIds",roleIds);
			return super.insert("insertByPermissionRoles", param);
		}
		return 0;
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.auth.dao.IRolePermissionDao#insertRolePermission(com.haoyu.sip.auth.entity.Role, java.util.List)
	 */
	@Override
	public int insertRolePermission(AuthRole role, List<String> permissionIds) {
		Map<String,Object> param = Maps.newHashMap();
		if(role!=null&&role.getId()!=null&&permissionIds!=null&&!permissionIds.isEmpty()){
			param.put("role", role);
			param.put("permissionIds",permissionIds);
			return super.insert("insertByRolePermissions", param);
		}
		return 0;
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.auth.dao.IRolePermissionDao#deleteRolePermission(com.haoyu.sip.auth.entity.Permission, java.util.List)
	 */
	@Override
	public int deleteRolePermission(AuthPermission permission, List<String> roleIds) {
		Map<String,Object> param = Maps.newHashMap();
		if(permission!=null&&permission.getId()!=null&&roleIds!=null&&!roleIds.isEmpty()){
			param.put("permission", permission);
			param.put("roleIds",roleIds);
			return super.delete("deleteByPhysics", param);
		}
		return 0;
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.auth.dao.IRolePermissionDao#deleteRolePermission(com.haoyu.sip.auth.entity.Role, java.util.List)
	 */
	@Override
	public int deleteRolePermission(AuthRole role, List<String> permissionIds) {
		Map<String,Object> param = Maps.newHashMap();
		if(role!=null&&role.getId()!=null&&permissionIds!=null&&!permissionIds.isEmpty()){
			param.put("role", role);
			param.put("permissionIds",permissionIds);
			return super.delete("deleteByPhysics", param);
		}
		return 0;
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.auth.dao.IRolePermissionDao#deleteRolePermission(com.haoyu.sip.auth.entity.Permission)
	 */
	@Override
	public int deleteRolePermission(AuthPermission permission) {
		if(permission!=null&&permission.getId()!=null){
			Map<String,Object> param = Maps.newHashMap();
			param.put("permission", permission);
			return super.delete("deleteByPhysics", param);
		}
		return 0;
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.auth.dao.IRolePermissionDao#deleteRolePermission(com.haoyu.sip.auth.entity.Role)
	 */
	@Override
	public int deleteRolePermission(AuthRole role) {
		if(role!=null&&role.getId()!=null){
			Map<String,Object> param = Maps.newHashMap();
			param.put("role", role);
			return super.delete("deleteByPhysics", param);
		}
		return 0;
	}

	@Override
	public int deleteRolePermission(Map<String, Object> param) {
		return super.delete("deleteByPhysics", param);
	}

}
