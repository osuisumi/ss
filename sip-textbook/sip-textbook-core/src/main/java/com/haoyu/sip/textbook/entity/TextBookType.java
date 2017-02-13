package com.haoyu.sip.textbook.entity;

import com.haoyu.base.entity.BaseEntity;

public class TextBookType extends BaseEntity{
	private static final long serialVersionUID = 7874994306942999178L;
	private String textBookTypeCode;
	private String textBookTypeName;
	private String textBookValue;
	private int rank;
	private String parentCode;
	public TextBookType() {
		super();
	}
	public TextBookType(String textBookTypeName) {
		super();
		this.textBookTypeName = textBookTypeName;
	}
	public String getTextBookTypeCode() {
		return textBookTypeCode;
	}
	public void setTextBookTypeCode(String textBookTypeCode) {
		this.textBookTypeCode = textBookTypeCode;
	}
	public String getTextBookTypeName() {
		return textBookTypeName;
	}
	public void setTextBookTypeName(String textBookTypeName) {
		this.textBookTypeName = textBookTypeName;
	}
	public String getTextBookValue() {
		return textBookValue;
	}
	public void setTextBookValue(String textBookValue) {
		this.textBookValue = textBookValue;
	}
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
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
		result = prime * result + ((textBookTypeCode == null) ? 0 : textBookTypeCode.hashCode());
		result = prime * result + ((textBookTypeName == null) ? 0 : textBookTypeName.hashCode());
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
		TextBookType other = (TextBookType) obj;
		if (textBookTypeCode == null) {
			if (other.textBookTypeCode != null)
				return false;
		} else if (!textBookTypeCode.equals(other.textBookTypeCode))
			return false;
		if (textBookTypeName == null) {
			if (other.textBookTypeName != null)
				return false;
		} else if (!textBookTypeName.equals(other.textBookTypeName))
			return false;
		return true;
	}
	
	
	
}
