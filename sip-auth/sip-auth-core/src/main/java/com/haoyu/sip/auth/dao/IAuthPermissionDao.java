/**
 * 
 */
package com.haoyu.sip.auth.dao;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.sip.auth.entity.AuthPermission;

/**
 * @author lianghuahuang
 *
 */
public interface IAuthPermissionDao {
	AuthPermission selectPermissionById(String id);
	
	List<AuthPermission> selectPermissionForList(AuthPermission permission,PageBounds pageBounds);
	
	Map<String,AuthPermission> selectPermissionForMap(AuthPermission permission,PageBounds pageBounds);
	
	int insertPermission(AuthPermission permission);
	
	int updatePermission(AuthPermission permission);
	
	int deletePermissionByLogic(AuthPermission permission);
	
	int deletePermissionByPhysics(String id);

	int batchDeleteByIds(List<String> ids);

}
