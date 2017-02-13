package com.haoyu.sip.note.mobile.entity;

import java.io.Serializable;

public class MNote implements Serializable{

	private static final long serialVersionUID = -8565467564415295614L;

	private String id;
	
	private String content;
		
	private long createTime;
	
	private String relationName;
	
	public MNote() {
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRelationName() {
		return relationName;
	}

	public void setRelationName(String relationName) {
		this.relationName = relationName;
	}

}