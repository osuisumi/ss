package com.haoyu.sip.evaluate.entity;

import java.math.BigDecimal;

import org.apache.commons.codec.digest.DigestUtils;

import com.haoyu.sip.core.entity.BaseEntity;
import com.haoyu.sip.core.entity.Relation;

public class EvaluateRelation extends BaseEntity{
	
	private static final long serialVersionUID = 681995288720828523L;

	private String id;
	
	private Evaluate evaluate;
	
	private Relation relation;
	
	private String type;
	
	private BigDecimal score;
	
	private int submitNum;
	
	public int getSubmitNum() {
		return submitNum;
	}

	public void setSubmitNum(int submitNum) {
		this.submitNum = submitNum;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Evaluate getEvaluate() {
		return evaluate;
	}

	public void setEvaluate(Evaluate evaluate) {
		this.evaluate = evaluate;
	}

	public Relation getRelation() {
		return relation;
	}

	public void setRelation(Relation relation) {
		this.relation = relation;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public BigDecimal getScore() {
		return score;
	}

	public void setScore(BigDecimal score) {
		this.score = score;
	}
	
	public static String getId(String evaluateId, String relationId){
		return DigestUtils.md5Hex(evaluateId+relationId);
	}

}
