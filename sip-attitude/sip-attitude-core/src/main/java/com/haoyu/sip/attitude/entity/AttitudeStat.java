/**
 * 
 */
package com.haoyu.sip.attitude.entity;

import java.io.Serializable;

import com.haoyu.sip.core.entity.Relation;

/**
 * @author lianghuahuang
 *
 */
public class AttitudeStat implements Serializable {
	
	private String attitude;
	
	
	private Relation relation;
	
	/**
	 * 参与数
	 */
	private int participateNum;

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

	public int getParticipateNum() {
		return participateNum;
	}

	public void setParticipateNum(int participateNum) {
		this.participateNum = participateNum;
	}
}
