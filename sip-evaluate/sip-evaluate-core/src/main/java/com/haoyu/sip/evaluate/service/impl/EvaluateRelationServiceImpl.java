package com.haoyu.sip.evaluate.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.google.common.collect.Maps;
import com.haoyu.sip.core.entity.Relation;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.utils.ThreadContext;
import com.haoyu.sip.evaluate.dao.IEvaluateRelationDao;
import com.haoyu.sip.evaluate.entity.Evaluate;
import com.haoyu.sip.evaluate.entity.EvaluateRelation;
import com.haoyu.sip.evaluate.service.IEvaluateRelationService;
import com.haoyu.sip.utils.Collections3;
import com.haoyu.sip.utils.Identities;

@Service
public class EvaluateRelationServiceImpl implements IEvaluateRelationService{
	
	@Resource
	private IEvaluateRelationDao evaluateRelationDao;

	@Override
	public EvaluateRelation getEvaluateRelationByRelationId(String relationId) {
		Map<String, Object> param = Maps.newHashMap();
		param.put("relationId", relationId);
		List<EvaluateRelation> evaluateRelations = this.listEvaluateRelation(param, null);
		if (Collections3.isNotEmpty(evaluateRelations)) {
			return evaluateRelations.get(0);
		}
		return null;
	}

	@Override
	public List<EvaluateRelation> listEvaluateRelation(Map<String, Object> param, PageBounds pageBounds) {
		return evaluateRelationDao.select(param, pageBounds);
	}

	@Override
	public Response createEvaluateRelation(EvaluateRelation evaluateRelation) {
		if (StringUtils.isEmpty(evaluateRelation.getId())) {
			evaluateRelation.setId(Identities.uuid2());
		}
		evaluateRelation.setDefaultValue();
		int count = evaluateRelationDao.insert(evaluateRelation);
		return count>0?Response.successInstance():Response.failInstance();
	}

	@Override
	public EvaluateRelation createEvaluateRelationIfNotExists(String evaluateId, String relationId) {
		String id = EvaluateRelation.getId(evaluateId, relationId);
		EvaluateRelation evaluateRelation = evaluateRelationDao.selectByPrimaryKey(id);
		if (evaluateRelation == null) {
			evaluateRelation = new EvaluateRelation();
			evaluateRelation.setId(id);
			Evaluate evaluate = new Evaluate();
			evaluate.setId(evaluateId);
			evaluateRelation.setEvaluate(evaluate);
			evaluateRelation.setRelation(new Relation(relationId));
			evaluateRelation.setDefaultValue();
			evaluateRelationDao.insert(evaluateRelation);
		}
		return evaluateRelation;
	}

	@Override
	public Response updateEvaluateRelation(EvaluateRelation evaluateRelation) {
		evaluateRelation.setUpdatedby(ThreadContext.getUser());
		evaluateRelation.setUpdateTime(System.currentTimeMillis());
		int count = evaluateRelationDao.update(evaluateRelation);
		return count>0?Response.successInstance():Response.failInstance();
	}

}
