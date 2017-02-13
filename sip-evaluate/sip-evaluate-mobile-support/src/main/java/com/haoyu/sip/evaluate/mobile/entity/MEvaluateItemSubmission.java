package com.haoyu.sip.evaluate.mobile.entity;

import java.io.Serializable;

public class MEvaluateItemSubmission implements Serializable{
	
	private static final long serialVersionUID = 4262283478532771797L;

	private String id;
	
	private String content;
	
	private double score;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
