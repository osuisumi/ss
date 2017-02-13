package com.haoyu.sip.dynamic.entity;

import org.apache.commons.lang3.StringUtils;

import com.haoyu.base.entity.BaseEntity;
import com.haoyu.sip.core.entity.Relation;

public class Dynamic extends BaseEntity {
	private static final long serialVersionUID = 2105421604506314477L;
	private String dynamicSourceId;

	private String dynamicSourceType;

	private Relation dynamicSourceRelation;

	private String content;

	private int replyNum;

	private int browseNum;

	private int fowordNum;

	private int supportNum;
	
	private Object dynamicSourceEntity;

	public String getDynamicSourceId() {
		return dynamicSourceId;
	}

	public void setDynamicSourceId(String dynamicSourceId) {
		this.dynamicSourceId = dynamicSourceId;
	}

	public Relation getDynamicSourceRelation() {
		return dynamicSourceRelation;
	}

	public void setDynamicSourceRelation(Relation dynamicSourceRelation) {
		this.dynamicSourceRelation = dynamicSourceRelation;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getDynamicSourceType() {
		return dynamicSourceType;
	}

	public void setDynamicSourceType(String dynamicSourceType) {
		this.dynamicSourceType = dynamicSourceType;
	}

	public int getReplyNum() {
		return replyNum;
	}

	public void setReplyNum(int replyNum) {
		this.replyNum = replyNum;
	}

	public int getBrowseNum() {
		return browseNum;
	}

	public void setBrowseNum(int browseNum) {
		this.browseNum = browseNum;
	}

	public int getFowordNum() {
		return fowordNum;
	}

	public void setFowordNum(int fowordNum) {
		this.fowordNum = fowordNum;
	}

	public int getSupportNum() {
		return supportNum;
	}

	public void setSupportNum(int supportNum) {
		this.supportNum = supportNum;
	}

	public Object getDynamicSourceEntity() {
		return dynamicSourceEntity;
	}

	public void setDynamicSourceEntity(Object dynamicSourceEntity) {
		this.dynamicSourceEntity = dynamicSourceEntity;
	}
	
	@Override
	public boolean equals(Object obj){
		if(obj == null){
			return false;
		}
		if(!(obj instanceof Dynamic)){
			return false;
		}
		Dynamic other = (Dynamic) obj;
		if(StringUtils.isAnyEmpty(this.getId(),other.getId())){
			return false;
		}
		return this.getId().equals(other.getId());
	}
	
	

}
