package com.haoyu.sip.file.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.base.utils.BaseServiceUtils;
import com.haoyu.sip.core.jdbc.MybatisDao;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.web.SearchParam;
import com.haoyu.sip.file.dao.IFileResourceDao;
import com.haoyu.sip.file.entity.FileRelation;
import com.haoyu.sip.file.entity.FileResource;
import com.haoyu.sip.file.event.CreateFileResourceEvent;
import com.haoyu.sip.file.event.DeleteFileResourceEvent;
import com.haoyu.sip.file.event.UpdateFileResourceEvent;
import com.haoyu.sip.file.service.IFileRelationService;
import com.haoyu.sip.file.service.IFileResourceService;
import com.haoyu.sip.utils.Collections3;

@Service("fileResourceService")
public class FileResourceServiceImpl implements IFileResourceService{

	@Resource
	private IFileResourceDao fileResourceDao;
	@Resource
	private IFileRelationService fileRelationService;
	@Resource
	private ApplicationContext applicationContext;
	
	public void setFileResourceDao(IFileResourceDao fileResourceDao) {
		this.fileResourceDao = fileResourceDao;
	}

	public void setFileRelationService(IFileRelationService fileRelationService) {
		this.fileRelationService = fileRelationService;
	}

	@Override
	public Response create(FileResource obj) {
		return BaseServiceUtils.create(obj, (MybatisDao)fileResourceDao);
	}

	@Override
	public Response update(FileResource obj) {
		Response response = BaseServiceUtils.update(obj, (MybatisDao)fileResourceDao); 
		if(response.isSuccess()){
			applicationContext.publishEvent(new UpdateFileResourceEvent(obj));
		}
		return response;
	}

	@Override
	public Response delete(String id) {
		FileResource fileResource = this.get(id);
		Response response = BaseServiceUtils.delete(id, (MybatisDao)fileResourceDao);
		if(response.isSuccess()){
			applicationContext.publishEvent(new DeleteFileResourceEvent(fileResource));
		}
		return response;
	}

	@Override
	public FileResource get(String id) {
		return (FileResource) BaseServiceUtils.get(id, (MybatisDao)fileResourceDao);
	}

	@Override
	public List<FileResource> list(SearchParam searchParam, PageBounds pageBounds) {
		return ((MybatisDao)fileResourceDao).selectList("select", searchParam.getParamMap(), pageBounds);
	}

	@Override
	public Response updateFileCount(String id) {
		FileResource fileResource = new FileResource();
		fileResource.setId(id);
		fileResource.setFileCount(1);
		return this.update(fileResource);
	}

	@Override
	public Response createFileResource(FileResource fileResource) {
		if(StringUtils.isNotEmpty(fileResource.getParentId())){
			FileResource parentFile = this.get(fileResource.getParentId());
			fileResource.setParentIds(parentFile.getParentIds()+","+fileResource.getParentId());
		}
		Response response = this.create(fileResource);
		if (response.isSuccess()) {
			if (Collections3.isNotEmpty(fileResource.getFileRelations())) {
				for (FileRelation fileRelation : fileResource.getFileRelations()) {
					if (StringUtils.isEmpty(fileRelation.getFileId())) {
						fileRelation.setFileId(fileResource.getId());
					}
					fileRelation.setFileId(fileResource.getId());
					String id = FileRelation.getId(fileRelation.getFileId(), fileRelation.getRelation().getId());
					fileRelation.setId(id);
					fileRelationService.create(fileRelation);
				}
			}
		}
		if (response.isSuccess()) {
			response.setResponseData(fileResource);
			applicationContext.publishEvent(new CreateFileResourceEvent(fileResource));
		}
		return response;
	}

	@Override
	public Response updateFileResourceParent(FileResource fileResource) {
		Response response = this.update(fileResource);
		if (response.isSuccess()) {
			updateParentLoop(fileResource.getId());
		}
		return response;
	}
	
	private void updateParentLoop(String id){
		int count = fileResourceDao.updateChildFileParent(id);
		if (count > 0) {
			SearchParam searchParam = new SearchParam();
			searchParam.getParamMap().put("parentId", id);
			List<FileResource> fileResources = this.list(searchParam, null);
			for (FileResource fileResource : fileResources) {
				updateParentLoop(fileResource.getId());
			}
		}
	}

	@Override
	public int getCount(Map<String, Object> param) {
		return fileResourceDao.getCount(param);
	}
}
