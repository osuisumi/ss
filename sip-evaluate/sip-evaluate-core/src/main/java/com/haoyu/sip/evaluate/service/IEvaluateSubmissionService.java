package com.haoyu.sip.evaluate.service;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.evaluate.entity.EvaluateSubmission;

public interface IEvaluateSubmissionService {

	Response updateEvaluateSubmission(EvaluateSubmission evaluateSubmission);

	EvaluateSubmission createEvaluateSubmissionIfNotExists(String evaluateId, String relationId);
	
	List<EvaluateSubmission> findEvaluateSubmissions(Map<String,Object> parameter,PageBounds pageBounds);
	
}
