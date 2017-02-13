package com.haoyu.sip.evaluate.dao;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.sip.evaluate.entity.EvaluateItemSubmission;

public interface IEvaluateItemSubmissionDao {

	int update(EvaluateItemSubmission evaluateItemSubmission);

	int insert(EvaluateItemSubmission evaluateItemSubmission);

	List<EvaluateItemSubmission> select(Map<String, Object> param, PageBounds pageBounds);

}
