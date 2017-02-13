package com.haoyu.sip.status.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.codec.digest.DigestUtils;

import com.haoyu.sip.core.entity.Relation;

public class Status implements Serializable{
	
	private static final long serialVersionUID = -3219308896335540861L;

	private String id;
	
	private Relation relation;
	
	private String state;
	
	private BigDecimal days;
	
	private Date lastSetTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Relation getRelation() {
		return relation;
	}

	public void setRelation(Relation relation) {
		this.relation = relation;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public BigDecimal getDays() {
		return days;
	}

	public void setDays(BigDecimal days) {
		this.days = days;
	}

	public Date getLastSetTime() {
		return lastSetTime;
	}

	public void setLastSetTime(Date lastSetTime) {
		this.lastSetTime = lastSetTime;
	}
	
	public static String getId(String relationId, String state){
		return DigestUtils.md5Hex(relationId+state);
	}

}
