package com.haoyu.sip.point.entity;

import org.apache.commons.codec.digest.DigestUtils;

import com.haoyu.sip.core.entity.BaseEntity;

public class PointStrategy extends BaseEntity {
	private static final long serialVersionUID = -3656510460532440555L;

	private String id;

	private Float point;

	private String summary;

	private String type;

	private String relationId;
	
	public PointStrategy(){}
	
	public PointStrategy(String id){
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}

	public Float getPoint() {
		return point;
	}

	public void setPoint(Float point) {
		this.point = point;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary == null ? null : summary.trim();
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getRelationId() {
		return relationId;
	}

	public void setRelationId(String relationId) {
		this.relationId = relationId;
	}
	
	public static String getId(String type,String relationId){
		return DigestUtils.md5Hex(type + relationId);
	}
	
	public static PointStrategy getMd5IdInstance(String type,String relationId){
		return new PointStrategy(PointStrategy.getId(type, relationId));
	}

}