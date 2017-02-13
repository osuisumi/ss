/**
 * 
 */
package com.haoyu.sip.auth.dao;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.sip.auth.entity.AuthRole;

/**
 * @author lianghuahuang
 *
 */
public interface IAuthRoleDao {
	
	AuthRole selectRoleById(String id);
	
	List<AuthRole> selectRoleForList(AuthRole role,PageBounds pageBounds);
	
	Map<String,AuthRole> selectRoleForMap(AuthRole role,PageBounds pageBounds);
	
	int insertRole(AuthRole role);
	
	int updateRole(AuthRole role);
	
	int deleteRoleByLogic(AuthRole role);
	
	int deleteRoleByPhysics(String id);
	//根据id集合批量物理删除
	int batchDeleteByIds(List<String> ids);
	
	
}
