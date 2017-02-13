/**
 * 
 */
package com.haoyu.sip.cms.resource.service;

import java.util.List;




import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.sip.cms.resource.entity.Resource;
import com.haoyu.sip.core.service.Response;

/**
 * @author lianghuahuang
 *
 */
public interface IResourceService {
	
	Response createResource(Resource resource);
	
	Response updateResource(Resource resource);
	
	Response deleteResource(Resource resource);
	
	Resource findResourceById(String id);
	
	List<Resource> findResources(Resource resource);
	
	List<Resource> findResources(Resource resource,PageBounds pageBounds);
	
	List<Resource> findResources(Map<String,Object> parameter,PageBounds pageBounds);
}
