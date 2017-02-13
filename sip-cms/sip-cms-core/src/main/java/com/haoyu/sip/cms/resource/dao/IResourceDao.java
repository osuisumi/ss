/**
 * 
 */
package com.haoyu.sip.cms.resource.dao;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.sip.cms.resource.entity.Resource;

/**
 * @author lianghuahuang
 *
 */
public interface IResourceDao {
	Resource selectResourceById(String id);
	
	int insertResource(Resource resource);
	
	int updateResource(Resource resource);
	
	int deleteResourceByLogic(Resource resource);
	
	int deleteResourceByPhysics(String id);

	List<Resource> findAll(Map<String, Object> parameter);
	
	List<Resource> findAll(Map<String, Object> parameter, PageBounds pageBounds);
}
