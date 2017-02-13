package com.haoyu.sip.evaluate.dao.impl.mybatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.sip.core.jdbc.MybatisDao;
import com.haoyu.sip.evaluate.dao.IEvaluateItemSubmissionDao;
import com.haoyu.sip.evaluate.entity.EvaluateItemSubmission;

@Repository
public class EvaluateItemSubmissionDao extends MybatisDao implements IEvaluateItemSubmissionDao{

	@Override
	public int update(EvaluateItemSubmission evaluateItemSubmission) {
		return super.update(evaluateItemSubmission);
	}

	@Override
	public int insert(EvaluateItemSubmission evaluateItemSubmission) {
		return super.insert(evaluateItemSubmission);
	}

	@Override
	public List<EvaluateItemSubmission> select(Map<String, Object> param, PageBounds pageBounds) {
		return selectList("select", param, pageBounds);
	}

}
