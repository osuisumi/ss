/**
 * 
 */
package com.haoyu.sip.auth.dao.impl.mybatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.google.common.collect.Maps;
import com.haoyu.sip.auth.dao.IAuthRoleMenuDao;
import com.haoyu.sip.auth.entity.AuthMenu;
import com.haoyu.sip.auth.entity.AuthRole;
import com.haoyu.sip.core.jdbc.MybatisDao;

/**
 * @author lianghuahuang
 *
 */
@Repository
public class AuthRoleMenuDao extends MybatisDao implements IAuthRoleMenuDao {

	/* (non-Javadoc)
	 * @see com.haoyu.sip.auth.dao.IMenuRoleDao#insertRoleMenu(com.haoyu.sip.auth.entity.Menu, java.util.List)
	 */
	@Override
	public int insertRoleMenu(AuthMenu menu, List<String> roleIds) {
		Map<String,Object> param = Maps.newHashMap();
		if(menu!=null&&menu.getId()!=null&&roleIds!=null&&!roleIds.isEmpty()){
			param.put("menu", menu);
			param.put("roleIds",roleIds);
			return super.insert("insertByMenuRoles", param);
		}
		return 0;
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.auth.dao.IMenuRoleDao#insertRoleMenu(com.haoyu.sip.auth.entity.Role,java.util.List)
	 */
	@Override
	public int insertRoleMenu( AuthRole role,List<String> menuIds) {
		Map<String,Object> param = Maps.newHashMap();
		if(role!=null&&role.getId()!=null&&menuIds!=null&&!menuIds.isEmpty()){
			param.put("role", role);
			param.put("menuIds",menuIds);
			return super.insert("insertByRoleMenus", param);
		}
		return 0;
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.auth.dao.IMenuRoleDao#deleteMenuRole(com.haoyu.sip.auth.entity.Menu, java.util.List)
	 */
	@Override
	public int deleteRoleMenu(AuthMenu menu, List<String> roleIds) {
		Map<String,Object> param = Maps.newHashMap();
		if(menu!=null&&menu.getId()!=null&&roleIds!=null&&!roleIds.isEmpty()){
			param.put("menu", menu);
			param.put("roleIds",roleIds);
			return super.delete("deleteByPhysics", param);
		}
		return 0;
	}
	@Override
	public int deleteRoleMenu( AuthRole role,List<String> menuIds) {
		Map<String,Object> param = Maps.newHashMap();
		if(role!=null&&role.getId()!=null&&menuIds!=null&&!menuIds.isEmpty()){
			param.put("role", role);
			param.put("menuIds",menuIds);
			return super.delete("deleteByPhysics", param);
		}
		return 0;
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.auth.dao.IMenuRoleDao#deleteMenuRole(com.haoyu.sip.auth.entity.Menu)
	 */
	@Override
	public int deleteRoleMenu(AuthMenu menu) {
		if(menu!=null&&menu.getId()!=null){
			Map<String,Object> param = Maps.newHashMap();
			param.put("menu", menu);
			return super.delete("deleteByPhysics", param);
		}
		return 0;
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.auth.dao.IMenuRoleDao#deleteMenuRole(com.haoyu.sip.auth.entity.Role)
	 */
	@Override
	public int deleteRoleMenu(AuthRole role) {
		if(role!=null&&role.getId()!=null){
			Map<String,Object> param = Maps.newHashMap();
			param.put("role", role);
			return super.delete("deleteByPhysics", param);
		}
		return 0;
	}

	@Override
	public int deleteRoleMenu(Map<String, Object> param) {
		return super.delete("deleteByPhysics",param);
	}

}
