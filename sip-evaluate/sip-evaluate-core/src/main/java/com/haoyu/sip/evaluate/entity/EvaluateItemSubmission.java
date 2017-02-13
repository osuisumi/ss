package com.haoyu.sip.evaluate.entity;

import java.math.BigDecimal;

import org.apache.commons.codec.digest.DigestUtils;

import com.haoyu.sip.core.entity.BaseEntity;

public class EvaluateItemSubmission extends BaseEntity{
	
	private static final long serialVersionUID = 3050113261267356719L;

	private String id;
	
	private EvaluateSubmission evaluateSubmission;
	
	private EvaluateItem evaluateItem;
	
	private BigDecimal score;
	
	private String comment;

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

	public EvaluateSubmission getEvaluateSubmission() {
		return evaluateSubmission;
	}

	public void setEvaluateSubmission(EvaluateSubmission evaluateSubmission) {
		this.evaluateSubmission = evaluateSubmission;
	}

	public EvaluateItem getEvaluateItem() {
		return evaluateItem;
	}

	public void setEvaluateItem(EvaluateItem evaluateItem) {
		this.evaluateItem = evaluateItem;
	}

	public BigDecimal getScore() {
		return score;
	}

	public void setScore(BigDecimal score) {
		this.score = score;
	}
	
	public static String getId(String evaluateSubmission, String itemId){
		return DigestUtils.md5Hex(evaluateSubmission+itemId);
	}

}
