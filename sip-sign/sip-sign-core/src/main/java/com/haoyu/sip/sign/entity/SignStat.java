package com.haoyu.sip.sign.entity;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.codec.digest.DigestUtils;

import com.haoyu.sip.core.entity.BaseEntity;
import com.haoyu.sip.core.entity.Relation;

public class SignStat extends BaseEntity{
	
	private static final long serialVersionUID = -4645672976935506661L;

	private String id;
	
	private Relation relation;
	
	private Date lastSignTime;		//最后一次签到日期
	
	private BigDecimal signLastNum;		//连续签到次数
	
	private BigDecimal signNum;		//签到总次数
	
	//以下是非数据库字段
	private int rank;	//签到总次数排行

	public SignStat() {
		
	}
	
	public SignStat(String id) {
		this.id = id;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

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

	public Date getLastSignTime() {
		return lastSignTime;
	}

	public void setLastSignTime(Date lastSignTime) {
		this.lastSignTime = lastSignTime;
	}

	public BigDecimal getSignLastNum() {
		return signLastNum;
	}

	public void setSignLastNum(BigDecimal signLastNum) {
		this.signLastNum = signLastNum;
	}

	public BigDecimal getSignNum() {
		return signNum;
	}

	public void setSignNum(BigDecimal signNum) {
		this.signNum = signNum;
	}

	public static String getId(String relationId, String userId) {
		return DigestUtils.md5Hex(relationId+userId);
	}

}
