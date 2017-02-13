package com.haoyu.sip.attitude.mobile.entity;

import java.io.Serializable;

public class MAttitudeUser implements Serializable{

	private static final long serialVersionUID = 2447484857257972708L;

	private String id;

	private String attitude;
		
	private int participateNum;
	
	public MAttitudeUser() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAttitude() {
		return attitude;
	}

	public void setAttitude(String attitude) {
		this.attitude = attitude;
	}

	public int getParticipateNum() {
		return participateNum;
	}

	public void setParticipateNum(int participateNum) {
		this.participateNum = participateNum;
	}

}