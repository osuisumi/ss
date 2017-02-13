package com.haoyu.sip.evaluate.service;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.evaluate.entity.EvaluateRelation;

public interface IEvaluateRelationService {
	
	EvaluateRelation getEvaluateRelationByRelationId(String relationId);

	List<EvaluateRelation> listEvaluateRelation(Map<String, Object> param, PageBounds pageBounds);
	
	Response createEvaluateRelation(EvaluateRelation evaluateRelation);

	EvaluateRelation createEvaluateRelationIfNotExists(String evaluateId, String relationId);

	Response updateEvaluateRelation(EvaluateRelation evaluateRelation);
	
}
