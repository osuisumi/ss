package com.haoyu.dict.entity;

import java.math.BigDecimal;

import com.haoyu.sip.core.entity.BaseEntity;
/**
 * 字典项 和 索引多对一  如 DictType dictTypeCode：SEX  dictTypeName：性别
 * 对应 DictEntry  dictTypeCode：SEX dictValue：1  dictName：女  sortNo: 1
 * 				 dictTypeCode：SEX dictValue：2  dictName：男  sortNo: 2
 */
public class DictEntry extends BaseEntity implements Comparable<DictEntry>{
	
	private static final long serialVersionUID = 5260302474565270270L;
	/** id */
	private String id;
	/** 字典索引 */
	private String dictTypeCode;
	/** 编号 */
	private String dictValue;
	/** 表示名 */
	private String dictName;
	
	/** 父项的编号  这个存在的理由 如  dictValue：2   dictName：硕士 
	 * dictValue：21   dictName：哲学硕士学位  parentValue:2  dictValue：201   dictName：经济学硕士学位  parentValue:2 
	 * dictValue：4   dictName：学士 
	 * dictValue：41   dictName：哲学学士学位  parentValue:2  dictValue：401   dictName：经济学学士学位  parentValue:2 
	 * 如第一个下拉框选择"硕士"和 "学士" ,第二个根据第一个选择出现显示内容，如选了“学士” ，第二个出现 “哲学学士学位 ” 和 “经济学学士学位 ”
	 */
	private String parentValue;
	
	/** 供查询会比较有意义 尽量与dictName保持一 ，暂时还不考虑 */
	private String parentName;
	
    /** 预留字段，暂没用  */
	private BigDecimal rank;
	/** 序号 */
	private BigDecimal sortNo;
	/** 是否隐藏 */
	private String isHidden;
	
	/** 实体对应的数据库表中没有 只是单纯装一下数据  字典索引的名称 */
	private String dictTypeName;
	
	/** 实体对应的数据库表中没有 只是单纯装一下数据  字典索引的父索引code */
	private String parentCode;

    public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIsHidden() {
		return isHidden;
	}

	public void setIsHidden(String isHidden) {
		this.isHidden = isHidden;
	}

	public String getDictTypeCode() {
		return dictTypeCode;
	}

	public void setDictTypeCode(String dictTypeCode) {
		this.dictTypeCode = dictTypeCode == null ? null : dictTypeCode.trim();
	}

	public String getDictValue() {
		return dictValue;
	}

	public void setDictValue(String dictValue) {
		this.dictValue = dictValue == null ? null : dictValue.trim();
	}

	public String getDictName() {
		return dictName;
	}

	public void setDictName(String dictName) {
		this.dictName = dictName == null ? null : dictName.trim();
	}

	public String getParentValue() {
		return parentValue;
	}

	public void setParentValue(String parentValue) {
		this.parentValue = parentValue == null ? null : parentValue.trim();
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName == null ? null : parentName.trim();
	}

	public BigDecimal getRank() {
		return rank;
	}

	public void setRank(BigDecimal rank) {
		this.rank = rank;
	}

	public BigDecimal getSortNo() {
		return sortNo;
	}

	public void setSortNo(BigDecimal sortNo) {
		this.sortNo = sortNo;
	}

	
	public String getDictTypeName() {
		return dictTypeName;
	}

	public void setDictTypeName(String dictTypeName) {
		this.dictTypeName = dictTypeName;
	}

	public String getParentCode() {
		return parentCode;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}

	public int compareTo(DictEntry dictEntry) {
		return Integer.parseInt(this.getDictValue())-(Integer.parseInt(dictEntry.getDictValue()));
	}
	
}