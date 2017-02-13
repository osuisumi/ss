package com.haoyu.sip.evaluate.dao;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.sip.evaluate.entity.EvaluateSubmission;

public interface IEvaluateSubmissionDao {

	EvaluateSubmission selectByPrimaryKey(String id);

	int insert(EvaluateSubmission evaluateSubmission);

	int update(EvaluateSubmission evaluateSubmission);
	
	List<EvaluateSubmission> selectByParameter(Map<String,Object> parameter,PageBounds pageBounds);

}
