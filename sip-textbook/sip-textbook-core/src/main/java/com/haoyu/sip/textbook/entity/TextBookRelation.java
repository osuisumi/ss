package com.haoyu.sip.textbook.entity;

import com.haoyu.base.entity.BaseEntity;

public class TextBookRelation extends BaseEntity{
	private static final long serialVersionUID = -6631148401702262466L;
	private String textBookTypeCode;
	private String textBookEntryId;
	private String parentCode;
	public TextBookRelation() {
		super();
	}
	public TextBookRelation(String textBookEntryId, String parentCode) {
		super();
		this.textBookEntryId = textBookEntryId;
		this.parentCode = parentCode;
	}
	public String getTextBookTypeCode() {
		return textBookTypeCode;
	}
	public void setTextBookTypeCode(String textBookTypeCode) {
		this.textBookTypeCode = textBookTypeCode;
	}
	public String getTextBookEntryId() {
		return textBookEntryId;
	}
	public void setTextBookEntryId(String textBookEntryId) {
		this.textBookEntryId = textBookEntryId;
	}
	public String getParentCode() {
		return parentCode;
	}
	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((parentCode == null) ? 0 : parentCode.hashCode());
		result = prime * result + ((textBookEntryId == null) ? 0 : textBookEntryId.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TextBookRelation other = (TextBookRelation) obj;
		if (parentCode == null) {
			if (other.parentCode != null)
				return false;
		} else if (!parentCode.equals(other.parentCode))
			return false;
		if (textBookEntryId == null) {
			if (other.textBookEntryId != null)
				return false;
		} else if (!textBookEntryId.equals(other.textBookEntryId))
			return false;
		return true;
	}
	
	
	
	
	

}
