/**
 * 
 */
package com.haoyu.sip.auth.dao;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.sip.auth.entity.AuthResource;

/**
 * @author lianghuahuang
 *
 */
public interface IAuthResourceDao {
	
	AuthResource selectResourceById(String id);
	
	List<AuthResource> selectResourceForList(AuthResource resource,PageBounds pageBounds);
	
	Map<String,AuthResource> selectResourceForMap(AuthResource resource,PageBounds pageBounds);
	
	int insertResource(AuthResource resource);
	
	int updateResource(AuthResource resource);
	
	int deleteResourceByLogic(AuthResource resource);
	
	int deleteResourceByPhysics(String id);
	
	int batchDeleteResourceByIds(List<String> ids);
}
