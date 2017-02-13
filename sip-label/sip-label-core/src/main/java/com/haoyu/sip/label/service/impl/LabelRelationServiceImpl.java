package com.haoyu.sip.label.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.base.utils.BaseServiceUtils;
import com.haoyu.sip.core.jdbc.MybatisDao;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.web.SearchParam;
import com.haoyu.sip.label.dao.ILabelRelationDao;
import com.haoyu.sip.label.entity.LabelRelation;
import com.haoyu.sip.label.service.ILabelRelationService;

@Service
public class LabelRelationServiceImpl implements ILabelRelationService{

	@Resource
	private ILabelRelationDao labelRelationDao;
	
	@Override
	public Response create(LabelRelation obj) {
		return BaseServiceUtils.create(obj, (MybatisDao)labelRelationDao);
	}

	@Override
	public Response update(LabelRelation obj) {
		return BaseServiceUtils.update(obj, (MybatisDao)labelRelationDao);
	}

	@Override
	public Response delete(String id) {
		return BaseServiceUtils.delete(id, (MybatisDao)labelRelationDao);
	}

	@Override
	public LabelRelation get(String id) {
		return (LabelRelation) BaseServiceUtils.get(id, (MybatisDao)labelRelationDao);
	}

	@Override
	public List<LabelRelation> list(SearchParam searchParam, PageBounds pageBounds) {
		return ((MybatisDao)labelRelationDao).selectList("select", searchParam.getParamMap(), pageBounds);
	}
	
	@Override
	public Response deleteByRelationId(String relationId) {
		int count = labelRelationDao.deleteByRelationId(relationId);
		return count>0?Response.successInstance():Response.failInstance();
	}

	@Override
	public Response insertByIds(Map<String, Object> param) {
		int count = labelRelationDao.insertByIds(param);
		return count>0?Response.successInstance():Response.failInstance();
	}

}
