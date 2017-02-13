/**
 * 
 */
package com.haoyu.sip.auth.dao.impl.mybatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.sip.auth.dao.IAuthPermissionDao;
import com.haoyu.sip.auth.entity.AuthPermission;
import com.haoyu.sip.core.jdbc.MybatisDao;

/**
 * @author lianghuahuang
 *
 */
@Repository
public class AuthPermissionDao extends MybatisDao implements IAuthPermissionDao {
	/* (non-Javadoc)
	 * @see com.haoyu.sip.auth.dao.IPermissionDao#selectPermissionById(java.lang.String)
	 */
	@Override
	public AuthPermission selectPermissionById(String id) {
		return super.selectByPrimaryKey(id);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.auth.dao.IPermissionDao#selectPermissionForList(com.haoyu.sip.auth.entity.Permission, com.github.miemiedev.mybatis.paginator.domain.PageBounds)
	 */
	@Override
	public List<AuthPermission> selectPermissionForList(AuthPermission permission,
			PageBounds pageBounds) {
		return super.selectList("selectByPermission", permission, pageBounds);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.auth.dao.IPermissionDao#selectPermissionForMap(com.haoyu.sip.auth.entity.Permission, com.github.miemiedev.mybatis.paginator.domain.PageBounds)
	 */
	@Override
	public Map<String, AuthPermission> selectPermissionForMap(
			AuthPermission permission, PageBounds pageBounds) {
		return super.selectMap("selectByPermission", permission, "id", pageBounds);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.auth.dao.IPermissionDao#insertPermission(com.haoyu.sip.auth.entity.Permission)
	 */
	@Override
	public int insertPermission(AuthPermission permission) {
		permission.setDefaultValue();
		return super.insert(permission);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.auth.dao.IPermissionDao#updatePermission(com.haoyu.sip.auth.entity.Permission)
	 */
	@Override
	public int updatePermission(AuthPermission permission) {
		permission.setUpdateTime(System.currentTimeMillis());
		return super.update(permission);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.auth.dao.IPermissionDao#deletePermissionByLogic(com.haoyu.sip.auth.entity.Permission)
	 */
	@Override
	public int deletePermissionByLogic(AuthPermission permission) {
		permission.setUpdateTime(System.currentTimeMillis());
		return super.deleteByLogic(permission);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.auth.dao.IPermissionDao#deletePermissionByPhysics(java.lang.String)
	 */
	@Override
	public int deletePermissionByPhysics(String id) {
		return super.deleteByPhysics(id);
	}

	@Override
	public int batchDeleteByIds(List<String> ids) {
		return super.delete("batchDeleteByIds",ids);
	}

}
