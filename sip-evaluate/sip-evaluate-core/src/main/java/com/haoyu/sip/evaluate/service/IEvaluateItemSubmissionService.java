package com.haoyu.sip.evaluate.service;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.sip.evaluate.entity.EvaluateItemSubmission;

public interface IEvaluateItemSubmissionService {
	
	Map<String, Float> mapEvaluateItemScore(String evaluateId, String relationId);
	
	List<EvaluateItemSubmission> findEvaluateItemSubmissions(Map<String,Object> parameter,PageBounds pageBounds);

}
