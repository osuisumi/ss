package com.haoyu.sip.label.entity;

import com.haoyu.base.entity.BaseEntity;

public class LabelRelation extends BaseEntity{
	
	private static final long serialVersionUID = -3558468743056941987L;

	private String labelId;

    private String relationId;
    
    private String type;

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
        this.relationId = relationId == null ? null : relationId.trim();
    }

	public String getLabelId() {
		return labelId;
	}

	public void setLabelId(String labelId) {
		this.labelId = labelId;
	}
}