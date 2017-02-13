/**
 * 
 */
package com.haoyu.sip.auth.dao.impl.mybatis;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.google.common.collect.Maps;
import com.haoyu.sip.auth.dao.IAuthUserRoleDao;
import com.haoyu.sip.auth.entity.AuthRole;
import com.haoyu.sip.auth.entity.AuthUser;
import com.haoyu.sip.core.jdbc.MybatisDao;

/**
 * @author lianghuahuang
 *
 */
@Repository
public class AuthUserRoleDao extends MybatisDao implements IAuthUserRoleDao {

	/* (non-Javadoc)
	 * @see com.haoyu.sip.auth.dao.IAuthRoleUserDao#insertRoleUser(com.haoyu.sip.auth.entity.AuthRole, java.util.List)
	 */
	@Override
	public int insertRoleUser(AuthRole authRole, List<String> userIds,String relationId) {
		Map<String,Object> param = Maps.newHashMap();
		if(authRole!=null&&authRole.getId()!=null&&userIds!=null&&!userIds.isEmpty()){
			param.put("role", authRole);
			param.put("userIds",userIds);
			param.put("relationId",relationId);
			return super.insert("insertByRoleUsers", param);
		}
		return 0;
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.auth.dao.IAuthRoleUserDao#insertRoleUser(com.haoyu.sip.auth.entity.AuthUser, java.util.List)
	 */
	@Override
	public int insertRoleUser(AuthUser authUser, List<String> roleIds) {
		Map<String,Object> param = Maps.newHashMap();
		if(authUser!=null&&authUser.getId()!=null&&roleIds!=null&&!roleIds.isEmpty()){
			param.put("roleIds", roleIds);
			param.put("authUser",authUser);
			return super.insert("insertByUserRoles", param);
		}
		return 0;
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.auth.dao.IAuthRoleUserDao#deleteRoleUser(com.haoyu.sip.auth.entity.AuthRole)
	 */
	@Override
	public int deleteRoleUser(AuthRole authRole, List<String> userIds,String relationId) {
		Map<String,Object> param = Maps.newHashMap();
		if(((userIds!=null&&!userIds.isEmpty())||StringUtils.isNotEmpty(relationId))){
			param.put("role", authRole);
			param.put("userIds",userIds);
			param.put("relationId",relationId);
			return super.delete("deleteByPhysics", param);
		}
		return 0;
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.auth.dao.IAuthRoleUserDao#deleteRoleUser(com.haoyu.sip.auth.entity.AuthUser)
	 */
	@Override
	public int deleteRoleUser(AuthUser authUser,List<String> roleIds) {
		Map<String,Object> param = Maps.newHashMap();
		if(authUser!=null&&authUser.getId()!=null&&roleIds!=null&&!roleIds.isEmpty()){
			param.put("roleIds", roleIds);
			param.put("authUser",authUser);
			return super.delete("deleteByPhysics", param);
		}
		return 0;
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.auth.dao.IAuthRoleUserDao#selectAuthUserByRole(com.haoyu.sip.auth.entity.AuthRole)
	 */
	@Override
	public List<AuthUser> selectAuthUserByRoleAndRelation(AuthRole authRole,String relationId) {
		Map<String,Object> param = Maps.newHashMap();
		if(authRole!=null||relationId!=null){
			param.put("role", authRole);
			param.put("relationId",relationId);
			return super.selectList("selectAuthUserByRoleAndRelation", param);
		}
		return null;
	}

	@Override
	public List<AuthUser> findAuthUserByRoleAndUser(AuthRole authRole, String userId) {
		Map<String,Object> param = Maps.newHashMap();
		if(authRole!=null||userId!=null){
			param.put("role", authRole);
			param.put("userId",userId);
			return super.selectList("selectAuthUserByRoleAndUser", param);
		}
		return null;
	}

	@Override
	public int deleteRoleUser(Map<String, Object> param) {
		return super.delete("deleteByPhysics", param);
	}

	@Override
	public int deleteRoleUser(String userId) {
		Map<String,Object> param = Maps.newHashMap();
		param.put("userId", userId);
		return super.delete("deleteByPhysics", param);
	}
	
	@Override
	public List<AuthUser> findAuthUserByParameter(Map<String, Object> parameter) {
		return super.selectList("selectAuthUserByParameter", parameter);
	}

}
