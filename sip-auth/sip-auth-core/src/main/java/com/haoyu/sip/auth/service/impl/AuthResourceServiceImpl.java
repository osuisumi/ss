/**
 * 
 */
package com.haoyu.sip.auth.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.sip.auth.dao.IAuthResourceDao;
import com.haoyu.sip.auth.entity.AuthResource;
import com.haoyu.sip.auth.event.BatchDeleteAuthResourceEvent;
import com.haoyu.sip.auth.service.IAuthResourceService;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.utils.Identities;

/**
 * @author lianghuahuang
 *
 */
@Service
public class AuthResourceServiceImpl implements IAuthResourceService {
	@Autowired
	private IAuthResourceDao resourceDao;
	@Autowired
	private ApplicationContext applicationContext;
	/* (non-Javadoc)
	 * @see com.haoyu.sip.auth.service.IResourceService#createResource(com.haoyu.sip.auth.entity.Resource)
	 */
	@Override
	public Response createResource(AuthResource resource) {
		if(resource==null){
			return Response.failInstance().responseMsg("createResource fail!resource is null!");
		}
		if(StringUtils.isEmpty(resource.getId())){
			resource.setId(Identities.uuid2());
		}
		int count = resourceDao.insertResource(resource);
		return count>0?Response.successInstance().responseData(resource):Response.failInstance().responseMsg("createResource fail!");
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.auth.service.IResourceService#updateResource(com.haoyu.sip.auth.entity.Resource)
	 */
	@Override
	public Response updateResource(AuthResource resource) {
		if(resource==null||StringUtils.isEmpty(resource.getId())){
			return Response.failInstance().responseMsg("updateResource is fail!resource is null or resource's id is null");
		}
		if(resource.getParent()!=null && StringUtils.isNotEmpty(resource.getParent().getId())){
			if(resource.getId().equals(resource.getParent().getId())){
				return Response.failInstance().responseMsg("updateResource is fail!parent equils itself");
			}
		}
		int count = resourceDao.updateResource(resource);
		return count>0?Response.successInstance().responseData(resource):Response.failInstance().responseMsg("updateResource fail!");
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.auth.service.IResourceService#deleteResource(com.haoyu.sip.auth.entity.Resource)
	 */
	@Override
	public Response deleteResource(AuthResource resource) {
		if(resource==null||(StringUtils.isEmpty(resource.getId())&&StringUtils.isEmpty(resource.getRelationId()))){
			return Response.failInstance().responseMsg("deleteResource is fail!resource is null or resource's id and resource's relationId  is null");
		}
		int count = resourceDao.deleteResourceByLogic(resource);
		return count>0?Response.successInstance():Response.failInstance().responseMsg("deleteResource fail!");
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.auth.service.IResourceService#findResourceById(java.lang.String)
	 */
	@Override
	public AuthResource findResourceById(String id) {
		return resourceDao.selectResourceById(id);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.auth.service.IResourceService#findResourceByNameAndRelation(java.lang.String, java.lang.String, com.github.miemiedev.mybatis.paginator.domain.PageBounds)
	 */
	@Override
	public List<AuthResource> findResourceByNameAndRelation(String name,
			String relationId, PageBounds pageBounds) {
		AuthResource resource = new AuthResource();
		if(StringUtils.isNotEmpty(name))
			resource.setName(name);
		if(StringUtils.isNotEmpty(relationId))
			resource.setRelationId(relationId);
		return resourceDao.selectResourceForList(resource, pageBounds);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.auth.service.IResourceService#findResourceByParent(java.lang.String)
	 */
	@Override
	public List<AuthResource> findResourceByParent(String parentResourceId) {
		if(StringUtils.isEmpty(parentResourceId))
			return null;
		AuthResource resource = new AuthResource();
		resource.setParent(new AuthResource(parentResourceId));
		return resourceDao.selectResourceForList(resource, null);
	}

	@Override
	public List<AuthResource> findResource(AuthResource resource,PageBounds pageBounds, boolean getTree) {
		if(getTree){
			return getResourceTree(this.resourceDao.selectResourceForMap(resource, null));
		}else{
			return resourceDao.selectResourceForList(resource, pageBounds);
		}
		
	}
	
	private List<AuthResource> getResourceTree(Map<String,AuthResource> resourceMap){
		List<AuthResource> roots = new ArrayList<AuthResource>();
		for (Map.Entry<String, AuthResource> entry : resourceMap.entrySet()) {
			 AuthResource resource = entry.getValue();
			 if(resource.getParent()==null){
				 roots.add(resource);
			 }else{
				 setParent(resource,resourceMap);
			 }
		}
		return roots;
	}
	
	private void setParent(AuthResource resource,Map<String,AuthResource> resourceMap){
		 if(resource.getParent()!=null){
			 String parentId = resource.getParent().getId();
			 AuthResource parentResource  = resourceMap.get(parentId);
			 parentResource.addChild(resource);
			 setParent(parentResource,resourceMap);
		 }
	}

	@Override
	public Response batchDeleteByIds(String ids) {
		return this.batchDeleteByIds(Arrays.asList(ids.split(",")));
	}
	
	
	
	private List<AuthResource> getTreeByIds(List<String> ids){
		List<AuthResource> result = new ArrayList<AuthResource>();
		List<AuthResource> resources = this.findResource(new AuthResource(), null, true);
		//从多个树中，根据ids 获取对应的子树
		for(String id:ids){
			for(AuthResource r:resources){
				AuthResource ar = r.getChildTreeById(id);
				if(ar!=null){
					result.add(ar);
				}
			}
		}
		return result;
		
	}

	@Override
	public Response batchDeleteByIds(List<String> ids) {
		if(CollectionUtils.isEmpty(ids)){
			return Response.failInstance().responseMsg("batchDeleteMenu fail! param ids is null");
		}
		//根据ids组装待删除的树的根节点
		List<AuthResource> deleteTreeRoots = this.getTreeByIds(ids);
		
		//获取所有待删除的resource的id
		List<String> prepareDeleteIds = new ArrayList<String>();
		
		for(AuthResource r:deleteTreeRoots){
			List<String> result = new ArrayList<String>();
			result = r.getTreeAllResourceId(result);
			prepareDeleteIds.addAll(result);
		}
		
		int count = this.resourceDao.batchDeleteResourceByIds(prepareDeleteIds);
		if(count>0){
			applicationContext.publishEvent(new BatchDeleteAuthResourceEvent(deleteTreeRoots));
			return Response.successInstance();
		}else{
			return Response.failInstance();
		}
	}
	
	

}
