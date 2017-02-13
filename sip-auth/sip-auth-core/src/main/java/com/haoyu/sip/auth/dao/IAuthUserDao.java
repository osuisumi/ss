/**
 * 
 */
package com.haoyu.sip.auth.dao;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.sip.auth.entity.AuthMenu;
import com.haoyu.sip.auth.entity.AuthUser;

/**
 * @author lianghuahuang
 *
 */
public interface IAuthUserDao {
	AuthUser selectAuthUserById(String id);
	
	List<AuthUser> selectAuthUserByIds(List<String> ids);
	
	List<AuthUser> selectAuthUserForList(AuthUser authUser,PageBounds pageBounds);
	
	List<AuthUser> selectAuthUserForList(Map<String,Object> parameter,PageBounds pageBounds);
	
	AuthUser selectAuthUserByUsername(String username);
	
	AuthUser selectAuthUserByUsernamePassword(String username,String password);
	
	List<AuthMenu> selectAuthUserMenus(String userId);
	
	int insertAuthUser(AuthUser authUser);
	
	int updateAuthUser(AuthUser authUser);
	
	int deleteAuthUserByLogic(AuthUser authUser);
}
