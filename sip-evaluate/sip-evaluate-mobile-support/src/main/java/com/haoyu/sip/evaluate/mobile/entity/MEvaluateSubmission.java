package com.haoyu.sip.evaluate.mobile.entity;

import java.io.Serializable;
import java.util.List;

import com.google.common.collect.Lists;

public class MEvaluateSubmission implements Serializable{
	
	private static final long serialVersionUID = 6173397319028858214L;

	private String id;
	
	private String evaluateRelationId;
	
	private List<MEvaluateItemSubmission> mEvaluateItemSubmissions = Lists.newArrayList();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEvaluateRelationId() {
		return evaluateRelationId;
	}

	public void setEvaluateRelationId(String evaluateRelationId) {
		this.evaluateRelationId = evaluateRelationId;
	}

	public List<MEvaluateItemSubmission> getmEvaluateItemSubmissions() {
		return mEvaluateItemSubmissions;
	}

	public void setmEvaluateItemSubmissions(List<MEvaluateItemSubmission> mEvaluateItemSubmissions) {
		this.mEvaluateItemSubmissions = mEvaluateItemSubmissions;
	}

}
