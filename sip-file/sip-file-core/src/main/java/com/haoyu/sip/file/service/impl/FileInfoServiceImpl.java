package com.haoyu.sip.file.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.base.utils.BaseServiceUtils;
import com.haoyu.sip.core.jdbc.MybatisDao;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.web.SearchParam;
import com.haoyu.sip.file.dao.IFileInfoDao;
import com.haoyu.sip.file.entity.FileInfo;
import com.haoyu.sip.file.entity.FileRelation;
import com.haoyu.sip.file.service.IFileInfoService;
import com.haoyu.sip.file.service.IFileRelationService;
import com.haoyu.sip.utils.Collections3;

@Service("fileInfoService")
public class FileInfoServiceImpl implements IFileInfoService{

	@Resource
	private IFileInfoDao fileInfoDao;
	@Resource
	private IFileRelationService fileRelationService;
	
	public void setFileInfoDao(IFileInfoDao fileInfoDao) {
		this.fileInfoDao = fileInfoDao;
	}

	public void setFileRelationService(IFileRelationService fileRelationService) {
		this.fileRelationService = fileRelationService;
	}

	@Override
	public Response create(FileInfo obj) {
		return BaseServiceUtils.create(obj, (MybatisDao)fileInfoDao);
	}

	@Override
	public Response update(FileInfo obj) {
		return BaseServiceUtils.update(obj, (MybatisDao)fileInfoDao);
	}

	@Override
	public Response delete(String id) {
		return BaseServiceUtils.delete(id, (MybatisDao)fileInfoDao);
	}

	@Override
	public FileInfo get(String id) {
		return (FileInfo) BaseServiceUtils.get(id, (MybatisDao)fileInfoDao);
	}

	@Override
	public List<FileInfo> list(SearchParam searchParam, PageBounds pageBounds) {
		return ((MybatisDao)fileInfoDao).selectList("select", searchParam.getParamMap(), pageBounds);
	}

	@Override
	public Response createFileInfo(FileInfo fileInfo) {
		Response response = this.create(fileInfo);
		if (response.isSuccess()) {
			if (Collections3.isNotEmpty(fileInfo.getFileRelations())) {
				for (FileRelation fileRelation : fileInfo.getFileRelations()) {
					if (StringUtils.isEmpty(fileRelation.getId())) {
						fileRelation.setId(FileRelation.getId(fileInfo.getId(), fileRelation.getRelation().getId()));
					}
					fileRelation.setFileId(fileInfo.getId());
					fileRelationService.create(fileRelation);
				}
			}
		}
		return response;
	}

	@Override
	public int getCount(Map<String, Object> param) {
		return fileInfoDao.getCount(param);
	}

	@Override
	public List<FileInfo> listFileInfo(Map<String, Object> param, PageBounds pageBounds) {
		return fileInfoDao.select(param, pageBounds);
	}
}
