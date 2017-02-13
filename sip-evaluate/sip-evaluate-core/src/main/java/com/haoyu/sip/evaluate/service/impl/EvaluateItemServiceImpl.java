package com.haoyu.sip.evaluate.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.google.common.collect.Maps;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.utils.ThreadContext;
import com.haoyu.sip.evaluate.dao.IEvaluateItemDao;
import com.haoyu.sip.evaluate.entity.EvaluateItem;
import com.haoyu.sip.evaluate.service.IEvaluateItemService;
import com.haoyu.sip.utils.Identities;

@Service
public class EvaluateItemServiceImpl implements IEvaluateItemService{
	
	@Resource
	private IEvaluateItemDao evaluateItemDao;

	@Override
	public Response createEvaluateItem(EvaluateItem evaluateItem) {
		if (StringUtils.isEmpty(evaluateItem.getId())) {
			evaluateItem.setId(Identities.uuid2());
		}
		evaluateItem.setDefaultValue();
		int count = evaluateItemDao.insert(evaluateItem);
		return count>0?Response.successInstance().responseData(evaluateItem):Response.failInstance();
	}

	@Override
	public Response updateEvaluateItem(EvaluateItem evaluateItem) {
		evaluateItem.setUpdatedby(ThreadContext.getUser());
		evaluateItem.setUpdateTime(System.currentTimeMillis());
		int count = evaluateItemDao.update(evaluateItem);
		return count>0?Response.successInstance():Response.failInstance();
	}

	@Override
	public Response deleteEvaluateItemByLogic(EvaluateItem evaluateItem) {
		String[] array = evaluateItem.getId().split(",");
		List<String> ids = Arrays.asList(array);
		evaluateItem.setUpdatedby(ThreadContext.getUser());
		evaluateItem.setUpdateTime(System.currentTimeMillis());
		Map<String, Object> param = Maps.newHashMap();
		param.put("ids", ids);
		param.put("entity", evaluateItem);
		int count = evaluateItemDao.deleteByLogic(param);
		return count>0?Response.successInstance():Response.failInstance();
	}

	@Override
	public List<EvaluateItem> listEvaluateItems(Map<String, Object> param, PageBounds pageBounds) {
		return evaluateItemDao.select(param, pageBounds);
	}

}
