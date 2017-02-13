package com.haoyu.sip.evaluate.dao.impl.mybatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.sip.core.jdbc.MybatisDao;
import com.haoyu.sip.evaluate.dao.IEvaluateSubmissionDao;
import com.haoyu.sip.evaluate.entity.EvaluateSubmission;

@Repository
public class EvaluateSubmissionDao extends MybatisDao implements IEvaluateSubmissionDao{

	@Override
	public EvaluateSubmission selectByPrimaryKey(String id) {
		return super.selectByPrimaryKey(id);
	}

	@Override
	public int insert(EvaluateSubmission evaluateSubmission) {
		return super.insert(evaluateSubmission);
	}

	@Override
	public int update(EvaluateSubmission evaluateSubmission) {
		return super.update(evaluateSubmission);
	}

	@Override
	public List<EvaluateSubmission> selectByParameter(Map<String, Object> parameter, PageBounds pageBounds) {
		return super.selectList("select", parameter, pageBounds);
	}

}
