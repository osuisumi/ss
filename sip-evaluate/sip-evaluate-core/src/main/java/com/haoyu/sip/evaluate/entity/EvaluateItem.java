package com.haoyu.sip.evaluate.entity;

import java.math.BigDecimal;

import com.haoyu.sip.core.entity.BaseEntity;

public class EvaluateItem extends BaseEntity{
	
	private static final long serialVersionUID = 4473699340433203818L;

	private String id;
	
	private Evaluate evaluate;
	
	private String content;
	
	private BigDecimal sortNo;

	public BigDecimal getSortNo() {
		return sortNo;
	}

	public void setSortNo(BigDecimal sortNo) {
		this.sortNo = sortNo;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Evaluate getEvaluate() {
		return evaluate;
	}

	public void setEvaluate(Evaluate evaluate) {
		this.evaluate = evaluate;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	@Override
	public void setDefaultValue() {
		super.setDefaultValue();
		this.setSortNo(BigDecimal.valueOf(9999));
	}

}
