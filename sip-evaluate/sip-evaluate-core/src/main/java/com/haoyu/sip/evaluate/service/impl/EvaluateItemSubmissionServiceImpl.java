package com.haoyu.sip.evaluate.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.haoyu.sip.evaluate.dao.IEvaluateItemSubmissionDao;
import com.haoyu.sip.evaluate.entity.EvaluateItemSubmission;
import com.haoyu.sip.evaluate.entity.EvaluateRelation;
import com.haoyu.sip.evaluate.service.IEvaluateItemSubmissionService;

@Service
public class EvaluateItemSubmissionServiceImpl implements IEvaluateItemSubmissionService{
	
	@Resource
	private IEvaluateItemSubmissionDao evaluateItemSubmissionDao;

	@Override
	public Map<String, Float> mapEvaluateItemScore(String evaluateId, String relationId) {
		String evaluateRelationId = EvaluateRelation.getId(evaluateId, relationId);
		Map<String, Object> param = Maps.newHashMap();
		param.put("evaluateRelationId", evaluateRelationId);
		List<EvaluateItemSubmission> evaluateItemSubmissions = evaluateItemSubmissionDao.select(param, null);
		Map<String, List<BigDecimal>> scoreMap = Maps.newHashMap();
		for (EvaluateItemSubmission evaluateItemSubmission : evaluateItemSubmissions) {
			if (!scoreMap.containsKey(evaluateItemSubmission.getEvaluateItem().getId())) {
				scoreMap.put(evaluateItemSubmission.getEvaluateItem().getId(), Lists.newArrayList());
			}
			List<BigDecimal> scores = scoreMap.get(evaluateItemSubmission.getEvaluateItem().getId());
			scores.add(evaluateItemSubmission.getScore());
		}
		Map<String, Float> resultMap = Maps.newHashMap();
		for (String itemId : scoreMap.keySet()) {
			List<BigDecimal> scores = scoreMap.get(itemId);
			BigDecimal total = new BigDecimal(0);
			for (BigDecimal score : scores) {
				total = total.add(score);
			}
			if (total.floatValue() != 0) {
				resultMap.put(itemId, total.divide(BigDecimal.valueOf(scores.size()), BigDecimal.ROUND_HALF_UP, 1).floatValue());
			}else{
				resultMap.put(itemId, Float.valueOf(0));
			}
			
		}
		return resultMap; 
	}

	@Override
	public List<EvaluateItemSubmission> findEvaluateItemSubmissions(Map<String, Object> parameter, PageBounds pageBounds) {
		return this.evaluateItemSubmissionDao.select(parameter, pageBounds);
	}
}
