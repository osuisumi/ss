package com.haoyu.sip.evaluate.entity;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.haoyu.sip.core.entity.BaseEntity;
import com.haoyu.sip.utils.Collections3;

public class EvaluateSubmission extends BaseEntity{
	
	private static final long serialVersionUID = -8091567661263639428L;

	private String id;
	
	private EvaluateRelation evaluateRelation;
	
	private BigDecimal score;
	
	private String comment;
	
	private String state;
	
	private List<EvaluateItemSubmission> evaluateItemSubmissions = Lists.newArrayList();

	private Map<String, EvaluateItemSubmission> evaluateItemSubmissionMap = Maps.newHashMap();

	public List<EvaluateItemSubmission> getEvaluateItemSubmissions() {
		return evaluateItemSubmissions;
	}

	public void setEvaluateItemSubmissions(List<EvaluateItemSubmission> evaluateItemSubmissions) {
		this.evaluateItemSubmissions = evaluateItemSubmissions;
	}

	public Map<String, EvaluateItemSubmission> getEvaluateItemSubmissionMap() {
		if (evaluateItemSubmissionMap.isEmpty()) {
			if (Collections3.isNotEmpty(evaluateItemSubmissions)) {
				evaluateItemSubmissionMap = Collections3.extractToMap(evaluateItemSubmissions, "evaluateItem.id", null);
			}
		}
		return evaluateItemSubmissionMap;
	}

	public void setEvaluateItemSubmissionMap(Map<String, EvaluateItemSubmission> evaluateItemSubmissionMap) {
		this.evaluateItemSubmissionMap = evaluateItemSubmissionMap;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public EvaluateRelation getEvaluateRelation() {
		return evaluateRelation;
	}

	public void setEvaluateRelation(EvaluateRelation evaluateRelation) {
		this.evaluateRelation = evaluateRelation;
	}

	public BigDecimal getScore() {
		return score;
	}

	public void setScore(BigDecimal score) {
		this.score = score;
	}
	
	public static String getId(String evaluateRelationId, String userId){
		return DigestUtils.md5Hex(evaluateRelationId+userId);
	}

}
