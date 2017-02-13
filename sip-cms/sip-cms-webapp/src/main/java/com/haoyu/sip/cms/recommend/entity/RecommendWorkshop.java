/**
 * 
 */
package com.haoyu.sip.cms.recommend.entity;

import java.io.Serializable;

/**
 * @author lianghuahuang
 *
 */
public class RecommendWorkshop implements Serializable {
	
	private  Recommend recommend;
	
	private String id;
	
	private String workshopName;
	
	private String master;
	
	private long   createTime;
	
	private int memberNum;
	
	public RecommendWorkshop(){}
	
	public RecommendWorkshop(Recommend recommend){
		this.recommend =recommend;
	}
	public Recommend getRecommend() {
		return recommend;
	}

	public void setRecommend(Recommend recommend) {
		this.recommend = recommend;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getWorkshopName() {
		return workshopName;
	}

	public void setWorkshopName(String workshopName) {
		this.workshopName = workshopName;
	}

	public String getMaster() {
		return master;
	}

	public void setMaster(String master) {
		this.master = master;
	}

	public long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}

	public int getMemberNum() {
		return memberNum;
	}

	public void setMemberNum(int memberNum) {
		this.memberNum = memberNum;
	}
	
	
}
