package com.haoyu.sip.textbook.entity;

import java.util.ArrayList;
import java.util.List;

import com.haoyu.base.entity.BaseEntity;

public class TextBookEntry extends BaseEntity{
	private static final long serialVersionUID = -1115157870311090995L;
	private String textBookTypeCode;
	private String textBookValue;
	private String textBookName;
	private String parentValue;
	private String parentName;
	private Integer rank;
	private Integer sortNo;
	private String parentCode;
	private List<TextBookEntry> childTextBookEntrys = new ArrayList<TextBookEntry>();
	
	public TextBookEntry() {
		super();
	}
	
	public TextBookEntry(String textBookTypeCode) {
		super();
		this.textBookTypeCode = textBookTypeCode;
	}
	public TextBookEntry(String textBookTypeCode, String textBookName) {
		super();
		this.textBookTypeCode = textBookTypeCode;
		this.textBookName = textBookName;
	}

	public String getTextBookTypeCode() {
		return textBookTypeCode;
	}
	public void setTextBookTypeCode(String textBookTypeCode) {
		this.textBookTypeCode = textBookTypeCode;
	}
	public String getTextBookValue() {
		return textBookValue;
	}
	public void setTextBookValue(String textBookValue) {
		this.textBookValue = textBookValue;
	}
	public String getTextBookName() {
		return textBookName;
	}
	public void setTextBookName(String textBookName) {
		this.textBookName = textBookName;
	}
	public String getParentValue() {
		return parentValue;
	}
	public void setParentValue(String parentValue) {
		this.parentValue = parentValue;
	}
	public String getParentName() {
		return parentName;
	}
	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public Integer getRank() {
		return rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

	public Integer getSortNo() {
		return sortNo;
	}

	public void setSortNo(Integer sortNo) {
		this.sortNo = sortNo;
	}

	public List<TextBookEntry> getChildTextBookEntrys() {
		return childTextBookEntrys;
	}

	public void setChildTextBookEntrys(List<TextBookEntry> childTextBookEntrys) {
		this.childTextBookEntrys = childTextBookEntrys;
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
		result = prime * result + ((textBookName == null) ? 0 : textBookName.hashCode());
		result = prime * result + ((textBookTypeCode == null) ? 0 : textBookTypeCode.hashCode());
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
		TextBookEntry other = (TextBookEntry) obj;
		if (textBookName == null) {
			if (other.textBookName != null)
				return false;
		} else if (!textBookName.equals(other.textBookName))
			return false;
		if (textBookTypeCode == null) {
			if (other.textBookTypeCode != null)
				return false;
		}
		else if (!textBookTypeCode.equals(other.textBookTypeCode))
			return false;
		if(parentName == null){
			if (other.parentName != null)
				return false;
		}else if(!parentName.equals(other.parentName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TextBookEntry [textBookTypeCode=" + textBookTypeCode + ", textBookValue=" + textBookValue + ", textBookName=" + textBookName + "]";
	}
}
