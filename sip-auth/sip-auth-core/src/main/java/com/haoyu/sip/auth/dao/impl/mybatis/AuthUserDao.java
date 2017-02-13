/**
 * 
 */
package com.haoyu.sip.auth.dao.impl.mybatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.github.miemiedev.mybatis.paginator.domain.Order;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.haoyu.sip.auth.dao.IAuthUserDao;
import com.haoyu.sip.auth.entity.AuthMenu;
import com.haoyu.sip.auth.entity.AuthUser;
import com.haoyu.sip.core.jdbc.MybatisDao;

/**
 * @author lianghuahuang
 *
 */
@Repository
public class AuthUserDao extends MybatisDao implements IAuthUserDao {

	/* (non-Javadoc)
	 * @see com.haoyu.sip.auth.dao.IAuthUserDao#selectAuthUserById(java.lang.String)
	 */
	@Override
	public AuthUser selectAuthUserById(String id) {
		Map<String,Object> parameter = Maps.newHashMap();
		parameter.put("userId", id);
		return super.selectOne("selectByParameter", parameter);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.auth.dao.IAuthUserDao#selectAuthUserByIds(java.util.List)
	 */
	@Override
	public List<AuthUser> selectAuthUserByIds(List<String> ids) {
		if(ids==null||ids.isEmpty())
			return Lists.newArrayList();
		Map<String,Object> parameter = Maps.newHashMap();
		parameter.put("userIds", ids);
		return super.selectList("selectByParameter", parameter);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.auth.dao.IAuthUserDao#selectAuthUserForList(com.haoyu.sip.auth.entity.AuthUser, com.github.miemiedev.mybatis.paginator.domain.PageBounds)
	 */
	@Override
	public List<AuthUser> selectAuthUserForList(AuthUser authUser,
			PageBounds pageBounds) {
		Map<String,Object> parameter = Maps.newHashMap();
		parameter.put("userId", authUser.getId());
		parameter.put("username", authUser.getUsername());
		parameter.put("password", authUser.getPassword());
		return super.selectList("selectByParameter", parameter);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.auth.dao.IAuthUserDao#selectAuthUserByUsername(java.lang.String)
	 */
	@Override
	public AuthUser selectAuthUserByUsername(String username) {
		Map<String,Object> parameter = Maps.newHashMap();
		parameter.put("username", username);
		return super.selectOne("selectByParameter", parameter);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.auth.dao.IAuthUserDao#selectAuthUserByUsernamePassword(java.lang.String, java.lang.String)
	 */
	@Override
	public AuthUser selectAuthUserByUsernamePassword(String username,
			String password) {
		Map<String,Object> parameter = Maps.newHashMap();
		parameter.put("username", username);
		parameter.put("password", password);
		return super.selectOne("selectByParameter", parameter);
	}

	@Override
	public List<AuthMenu> selectAuthUserMenus(String userId) {
		Map<String,Object> parameter = Maps.newHashMap();
		parameter.put("userId", userId);
		PageBounds pageBounds = new PageBounds();
		pageBounds.setLimit(Integer.MAX_VALUE);
		pageBounds.setOrders(Order.formString("ORDER_NO,CREATE_TIME"));
		return super.selectList("selectAuthUserMenus", parameter, pageBounds);
	}

	@Override
	public int insertAuthUser(AuthUser authUser) {
		authUser.setDefaultValue();
		return super.insert(authUser);
	}

	@Override
	public int updateAuthUser(AuthUser authUser) {
		authUser.setUpdateValue();
		return super.update(authUser);
	}

	@Override
	public int deleteAuthUserByLogic(AuthUser authUser) {
		authUser.setUpdateValue();
		return super.deleteByLogic(authUser);
	}

	@Override
	public List<AuthUser> selectAuthUserForList(Map<String, Object> parameter,
			PageBounds pageBounds) {
		return super.selectList("selectByParameter", parameter,pageBounds);
	}

}
