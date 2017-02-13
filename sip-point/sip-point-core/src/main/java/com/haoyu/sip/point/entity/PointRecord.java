package com.haoyu.sip.point.entity;


import com.haoyu.sip.core.entity.BaseEntity;

/**
 * @author Administrator
 *
 */
public class PointRecord extends BaseEntity {
	private static final long serialVersionUID = 1L;

	private String id;

	private PointStrategy pointStrategy;

	private String userId;

	private String relationId;

	private String entityId;
	
	private String detail;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId == null ? null : userId.trim();
	}

	public String getRelationId() {
		return relationId;
	}

	public void setRelationId(String relationId) {
		this.relationId = relationId == null ? null : relationId.trim();
	}

	public PointStrategy getPointStrategy() {
		return pointStrategy;
	}

	public void setPointStrategy(PointStrategy pointStrategy) {
		this.pointStrategy = pointStrategy;
	}

	public String getEntityId() {
		return entityId;
	}

	public void setEntityId(String entityId) {
		this.entityId = entityId;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}
	
}