/**
 * 
 */
package com.haoyu.sip.auth.service;

import java.util.List;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.sip.auth.entity.AuthResource;
import com.haoyu.sip.core.service.Response;

/**
 * @author lianghuahuang
 *
 */
public interface IAuthResourceService {
	
	Response createResource(AuthResource resource);
	
	Response updateResource(AuthResource resource);
	
	Response deleteResource(AuthResource resource);
	
	AuthResource findResourceById(String id);
	
	List<AuthResource> findResourceByNameAndRelation(String name,String relationId,PageBounds pageBounds);	
	
	List<AuthResource> findResourceByParent(String parentResourceId);
	
	List<AuthResource> findResource(AuthResource authResource,PageBounds pageBounds,boolean getTree);
	
	Response batchDeleteByIds(String ids);
	
	Response batchDeleteByIds(List<String> ids);
	
	
}
