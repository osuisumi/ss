package com.haoyu.sip.category.entity;

import com.haoyu.sip.core.entity.BaseEntity;
import com.haoyu.sip.core.entity.Relation;

public class CategoryRelation extends BaseEntity{

	private static final long serialVersionUID = 6387703452895051887L;
	
	private String id;
	
	private Category category;
	
	private Relation relation;

	public CategoryRelation() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Relation getRelation() {
		return relation;
	}

	public void setRelation(Relation relation) {
		this.relation = relation;
	}
	
	
}
