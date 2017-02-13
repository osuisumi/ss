package com.haoyu.dict.entity;

import java.math.BigDecimal;

import com.haoyu.sip.core.entity.BaseEntity;

/**
 * 字典索引
 * 
 */
public class DictType extends BaseEntity{

	private static final long serialVersionUID = 1206236508826730676L;
	/** 字典索引 */
	private String dictTypeCode;
	/** 索引名称 */
	private String dictTypeName;
	/** 预留字段，暂没用  */
	private BigDecimal rank;
	/** 父索引code 暂没用  做一些复杂的字典的时候才有用  如  省 市 区 */
	private String parentCode;	

	public String getDictTypeCode() {
		return dictTypeCode;
	}

	public void setDictTypeCode(String dictTypeCode) {
		this.dictTypeCode = dictTypeCode == null ? null : dictTypeCode.trim();
	}

	public String getDictTypeName() {
		return dictTypeName;
	}

	public void setDictTypeName(String dictTypeName) {
		this.dictTypeName = dictTypeName == null ? null : dictTypeName.trim();
	}

	public BigDecimal getRank() {
		return rank;
	}

	public void setRank(BigDecimal rank) {
		this.rank = rank;
	}

	public String getParentCode() {
		return parentCode;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode == null ? null : parentCode.trim();
	}
	
	
}