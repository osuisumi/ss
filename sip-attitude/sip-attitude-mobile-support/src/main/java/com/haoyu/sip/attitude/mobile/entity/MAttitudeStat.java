package com.haoyu.sip.attitude.mobile.entity;

import java.io.Serializable;

import com.haoyu.sip.core.entity.Relation;

public class MAttitudeStat implements Serializable{

	private static final long serialVersionUID = 7690192361090180188L;

	private String attitude;
	
	private Relation relation;
	
	private Integer participateNum;

	public MAttitudeStat() {
	}

	public String getAttitude() {
		return attitude;
	}

	public void setAttitude(String attitude) {
		this.attitude = attitude;
	}

	public Relation getRelation() {
		return relation;
	}

	public void setRelation(Relation relation) {
		this.relation = relation;
	}

	public Integer getParticipateNum() {
		return participateNum;
	}

	public void setParticipateNum(Integer participateNum) {
		this.participateNum = participateNum;
	}
	
}
