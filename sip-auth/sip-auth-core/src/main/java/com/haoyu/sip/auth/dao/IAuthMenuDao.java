/**
 * 
 */
package com.haoyu.sip.auth.dao;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.sip.auth.entity.AuthMenu;

/**
 * @author lianghuahuang
 *
 */
public interface IAuthMenuDao {
	
	AuthMenu selectMenuById(String id);
	
	List<AuthMenu> selectMenuForList(Map<String,Object> parameter,PageBounds pageBounds);
	
	Map<String,AuthMenu> selectMenuForMap(Map<String,Object> parameter,PageBounds pageBounds);
	
	//Map<String,AuthMenu> selectMenuForMap(AuthMenu menu,PageBounds pageBounds);
	
	int insertMenu(AuthMenu menu);
	
	int updateMenu(AuthMenu menu);
	
	int deleteMenuByLogic(AuthMenu menu);
	
	int deleteMenuByPhysics(String id);
	
	int batchDeleteByIds(List<String> ids);
}
