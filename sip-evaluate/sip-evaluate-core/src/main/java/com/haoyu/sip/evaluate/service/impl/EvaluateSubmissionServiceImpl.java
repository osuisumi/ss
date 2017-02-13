package com.haoyu.sip.evaluate.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.google.common.collect.Maps;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.utils.ThreadContext;
import com.haoyu.sip.evaluate.dao.IEvaluateItemSubmissionDao;
import com.haoyu.sip.evaluate.dao.IEvaluateSubmissionDao;
import com.haoyu.sip.evaluate.entity.EvaluateItem;
import com.haoyu.sip.evaluate.entity.EvaluateItemSubmission;
import com.haoyu.sip.evaluate.entity.EvaluateRelation;
import com.haoyu.sip.evaluate.entity.EvaluateSubmission;
import com.haoyu.sip.evaluate.event.SubmitEvaluateSubmissionEvent;
import com.haoyu.sip.evaluate.service.IEvaluateRelationService;
import com.haoyu.sip.evaluate.service.IEvaluateSubmissionService;
import com.haoyu.sip.evaluate.utils.EvaluateSubmissionState;

@Service
public class EvaluateSubmissionServiceImpl implements IEvaluateSubmissionService{
	
	@Resource
	private IEvaluateSubmissionDao evaluateSubmissionDao;
	@Resource
	private IEvaluateItemSubmissionDao evaluateItemSubmissionDao;
	@Resource
	private IEvaluateRelationService evaluateRelationService;
	@Resource
	private ApplicationContext applicationContext;

	@Override
	public EvaluateSubmission createEvaluateSubmissionIfNotExists(String evaluateId, String relationId) {
		String evaluateRelationId = EvaluateRelation.getId(evaluateId, relationId);
		String evaluateSubmissionId = EvaluateSubmission.getId(evaluateRelationId, ThreadContext.getUser().getId());
		EvaluateSubmission evaluateSubmission = evaluateSubmissionDao.selectByPrimaryKey(evaluateSubmissionId);
		if (evaluateSubmission == null) {
			EvaluateRelation evaluateRelation = evaluateRelationService.createEvaluateRelationIfNotExists(evaluateId, relationId);
			evaluateSubmission = new EvaluateSubmission();
			evaluateSubmission.setEvaluateRelation(evaluateRelation);
			evaluateSubmission.setId(evaluateSubmissionId);
			evaluateSubmission.setState(EvaluateSubmissionState.NOT_SUBMIT);
			evaluateSubmission.setDefaultValue();
			int count = evaluateSubmissionDao.insert(evaluateSubmission);
			return count>0?evaluateSubmission:null;
		}
		return evaluateSubmission;
	}

	@Override
	public Response updateEvaluateSubmission(EvaluateSubmission evaluateSubmission) {
		if (StringUtils.isEmpty(evaluateSubmission.getId())) {
			return Response.failInstance().responseMsg("evaluateSubmission.id is null");
		}
		if (evaluateSubmission.getEvaluateRelation() == null || StringUtils.isEmpty(evaluateSubmission.getEvaluateRelation().getId())) {
			return Response.failInstance().responseMsg("evaluateSubmission.evaluateRelation.id is null");
		}
		if (evaluateSubmission.getEvaluateItemSubmissionMap().isEmpty()) {
			return Response.failInstance().responseMsg("evaluateSubmission.evaluateItemSubmissionMap is null");
		}
		BigDecimal totalScore = new BigDecimal(0);
		BigDecimal score = new BigDecimal(0);
		for (String itemId : evaluateSubmission.getEvaluateItemSubmissionMap().keySet()) {
			EvaluateItemSubmission evaluateItemSubmission = evaluateSubmission.getEvaluateItemSubmissionMap().get(itemId);
			String evaluateItemSubmissionId = EvaluateItemSubmission.getId(evaluateSubmission.getId(), itemId);
			evaluateItemSubmission.setId(evaluateItemSubmissionId);
			EvaluateItem evaluateItem = new EvaluateItem();
			evaluateItem.setId(itemId);
			evaluateItemSubmission.setEvaluateItem(evaluateItem);
			evaluateItemSubmission.setEvaluateSubmission(evaluateSubmission);
			evaluateItemSubmission.setUpdatedby(ThreadContext.getUser());
			evaluateItemSubmission.setUpdateTime(System.currentTimeMillis());
			int count = evaluateItemSubmissionDao.update(evaluateItemSubmission); 
			if (count <= 0) {
				evaluateItemSubmission.setDefaultValue();
				evaluateItemSubmissionDao.insert(evaluateItemSubmission);
			}
			totalScore = totalScore.add(evaluateItemSubmission.getScore());
		}
		evaluateSubmission.setState(EvaluateSubmissionState.SUBMITED);
		evaluateSubmission.setScore(BigDecimal.valueOf(1));
		int count = evaluateSubmissionDao.update(evaluateSubmission);
		if (count > 0) {
			EvaluateRelation evaluateRelation = evaluateSubmission.getEvaluateRelation();
			evaluateRelation.setSubmitNum(1);
			evaluateRelation.setScore(BigDecimal.valueOf(1));
			Response response = evaluateRelationService.updateEvaluateRelation(evaluateRelation);
			if (response.isSuccess()) {
				Map<String, Object> source = Maps.newHashMap();
				source.put("relationId", evaluateRelation.getRelation().getId());
				score = totalScore.divide(BigDecimal.valueOf(evaluateSubmission.getEvaluateItemSubmissionMap().size()), 1, BigDecimal.ROUND_HALF_UP);
				source.put("score", score);
				applicationContext.publishEvent(new SubmitEvaluateSubmissionEvent(source));
			}
		}
		return count>0?Response.successInstance():Response.failInstance();
	}

	@Override
	public List<EvaluateSubmission> findEvaluateSubmissions(Map<String, Object> parameter, PageBounds pageBounds) {
		return this.evaluateSubmissionDao.selectByParameter(parameter, pageBounds);
	}

}
