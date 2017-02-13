package com.haoyu.sip.label.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.google.common.collect.Maps;
import com.haoyu.base.utils.BaseServiceUtils;
import com.haoyu.sip.core.jdbc.MybatisDao;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.utils.ThreadContext;
import com.haoyu.sip.core.web.SearchParam;
import com.haoyu.sip.label.dao.ILabelDao;
import com.haoyu.sip.label.entity.Label;
import com.haoyu.sip.label.service.ILabelRelationService;
import com.haoyu.sip.label.service.ILabelService;
import com.haoyu.sip.utils.Collections3;

@Service
public class LabelServiceImpl implements ILabelService{
	
	@Resource
	private ILabelDao labelDao;
	@Resource
	private ILabelRelationService labelRelationService;
	
	@Override
	public Response create(Label obj) {
		return BaseServiceUtils.create(obj, (MybatisDao)labelDao);
	}

	@Override
	public Response update(Label obj) {
		return BaseServiceUtils.update(obj, (MybatisDao)labelDao);
	}

	@Override
	public Response delete(String id) {
		return BaseServiceUtils.delete(id, (MybatisDao)labelDao);
	}

	@Override
	public Label get(String id) {
		return (Label) BaseServiceUtils.get(id, (MybatisDao)labelDao);
	}

	@Override
	public List<Label> list(SearchParam searchParam, PageBounds pageBounds) {
		return ((MybatisDao)labelDao).selectList("select", searchParam.getParamMap(), pageBounds);
	}

	@Override
	public Response updateLabels(List<String> ids, String relationId, String type) {
		if (StringUtils.isEmpty(relationId)) {
			return Response.failInstance();
		}
		//删除该relationId的所有关联记录
		Response response = labelRelationService.deleteByRelationId(relationId);
		if (Collections3.isNotEmpty(ids)) {
			Map<String, Object> param = Maps.newHashMap();
			param.put("ids", ids);
			param.put("type", type);
			param.put("relationId", relationId);
			//插入新的关联记录
			response = labelRelationService.insertByIds(param);
		}
		if (response.isSuccess()) {
			Label label = new Label();
			label.setUpdatedby(ThreadContext.getUser());
			label.setUpdateTime(System.currentTimeMillis());
			//删除没有关联记录的标签
			labelDao.deleteWithoutRelation(label);
		}
		return response;
	}

	@Override
	public List<Label> getLebals(Label label, PageBounds pageBounds) {
		return labelDao.selectByName(label, pageBounds);
	}
	
	@Override
	public List<Label> getLebalsByRelationId(String relationId) {
		List<Label> labels = labelDao.selectByRelationId(relationId);
		return labels;
	}

	@Override
	public Response createLabel(Label label) {
		Label lb = labelDao.selectOneByName(label.getName());
		if (lb == null) {
			Response response = this.create(label);
			response.setResponseData(label);
			return response;
		}
		Response response = Response.successInstance();
		response.setResponseData(lb);
		return response;
	}

}
