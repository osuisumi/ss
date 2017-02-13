package com.haoyu.sip.category.entity;

import com.haoyu.sip.core.entity.BaseEntity;

public class Category extends BaseEntity{

	private static final long serialVersionUID = -7600743746879722164L;

	private String id;

	private String name;
	
	private String type;
	
	private Category parentCategory ;

	private CategoryRelation categoryRelation ;
	
	public Category() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Category getParentCategory() {
		return parentCategory;
	}

	public void setParentCategory(Category parentCategory) {
		this.parentCategory = parentCategory;
	}

	public CategoryRelation getCategoryRelation() {
		return categoryRelation;
	}

	public void setCategoryRelation(CategoryRelation categoryRelation) {
		this.categoryRelation = categoryRelation;
	}
	
}
