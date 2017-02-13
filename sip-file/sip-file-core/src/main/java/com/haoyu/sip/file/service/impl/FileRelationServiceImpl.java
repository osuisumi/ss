package com.haoyu.sip.file.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.base.utils.BaseServiceUtils;
import com.haoyu.sip.core.entity.Relation;
import com.haoyu.sip.core.entity.User;
import com.haoyu.sip.core.jdbc.MybatisDao;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.utils.ThreadContext;
import com.haoyu.sip.core.web.SearchParam;
import com.haoyu.sip.file.dao.IFileRelationDao;
import com.haoyu.sip.file.entity.FileRelation;
import com.haoyu.sip.file.service.IFileRelationService;

@Service("fileRelationService")
public class FileRelationServiceImpl implements IFileRelationService{

	@Resource
	private IFileRelationDao fileRelationDao;
	
	public void setFileRelationDao(IFileRelationDao fileRelationDao) {
		this.fileRelationDao = fileRelationDao;
	}

	@Override
	public Response create(FileRelation obj) {
		return BaseServiceUtils.create(obj, (MybatisDao)fileRelationDao);
	}

	@Override
	public Response update(FileRelation obj) {
		return BaseServiceUtils.update(obj, (MybatisDao)fileRelationDao);
	}

	@Override
	public Response delete(String id) {
		return BaseServiceUtils.delete(id, (MybatisDao)fileRelationDao);
	}

	@Override
	public FileRelation get(String id) {
		return (FileRelation) BaseServiceUtils.get(id, (MybatisDao)fileRelationDao);
	}

	@Override
	public List<FileRelation> list(SearchParam searchParam, PageBounds pageBounds) {
		return ((MybatisDao)fileRelationDao).selectList("select", searchParam.getParamMap(), pageBounds);
	}

	@Override
	public Response deleteFileRelation(String fileId, String relationId) {
		FileRelation fileRelation = new FileRelation();
		fileRelation.setUpdatedby(ThreadContext.getUser());
		fileRelation.setUpdateTime(System.currentTimeMillis());
		fileRelation.setFileId(fileId);
		fileRelation.setRelation(new Relation(relationId));
		int count = fileRelationDao.deleteFileRelation(fileRelation);
		return count>0?Response.successInstance():Response.failInstance();
	}
	
}
