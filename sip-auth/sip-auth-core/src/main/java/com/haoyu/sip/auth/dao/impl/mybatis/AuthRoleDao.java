/**
 * 
 */
package com.haoyu.sip.auth.dao.impl.mybatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.sip.auth.dao.IAuthRoleDao;
import com.haoyu.sip.auth.entity.AuthRole;
import com.haoyu.sip.core.jdbc.MybatisDao;

/**
 * @author lianghuahuang
 *
 */
@Repository
public class AuthRoleDao extends MybatisDao implements IAuthRoleDao {

	/* (non-Javadoc)
	 * @see com.haoyu.sip.auth.dao.IRoleDao#selectRoleById(java.lang.String)
	 */
	@Override
	public AuthRole selectRoleById(String id) {
		return super.selectByPrimaryKey(id);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.auth.dao.IRoleDao#selectRoleForList(com.haoyu.sip.auth.entity.Role, com.github.miemiedev.mybatis.paginator.domain.PageBounds)
	 */
	@Override
	public List<AuthRole> selectRoleForList(AuthRole role, PageBounds pageBounds) {
		return super.selectList("selectByRole", role, pageBounds);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.auth.dao.IRoleDao#selectRoleForMap(com.haoyu.sip.auth.entity.Role, com.github.miemiedev.mybatis.paginator.domain.PageBounds)
	 */
	@Override
	public Map<String, AuthRole> selectRoleForMap(AuthRole role, PageBounds pageBounds) {
		return super.selectMap("selectByRole", role, "id", pageBounds);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.auth.dao.IRoleDao#insertRole(com.haoyu.sip.auth.entity.Role)
	 */
	@Override
	public int insertRole(AuthRole role) {
		role.setDefaultValue();
		return super.insert(role);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.auth.dao.IRoleDao#updateRole(com.haoyu.sip.auth.entity.Role)
	 */
	@Override
	public int updateRole(AuthRole role) {
		role.setUpdateTime(System.currentTimeMillis());
		return super.update(role);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.auth.dao.IRoleDao#deleteRoleByLogic(com.haoyu.sip.auth.entity.Role)
	 */
	@Override
	public int deleteRoleByLogic(AuthRole role) {
		role.setUpdateTime(System.currentTimeMillis());
		return super.deleteByLogic(role);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.auth.dao.IRoleDao#deleteRoleByPhysics(java.lang.String)
	 */
	@Override
	public int deleteRoleByPhysics(String id) {
		return super.deleteByPhysics(id);
	}

	@Override
	public int batchDeleteByIds(List<String> ids) {
		return super.delete("batchDeleteByIds", ids);
	}

}
