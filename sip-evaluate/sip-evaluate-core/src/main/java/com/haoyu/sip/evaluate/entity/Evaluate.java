package com.haoyu.sip.evaluate.entity;

import java.util.List;

import com.google.common.collect.Lists;
import com.haoyu.sip.core.entity.BaseEntity;

public class Evaluate extends BaseEntity{
	
	private static final long serialVersionUID = -6164620664870639951L;

	private String id;
	
	private String title;
	
	private String description;
	
	private String state;
	
	private List<EvaluateItem> evaluateItems = Lists.newArrayList();

	public List<EvaluateItem> getEvaluateItems() {
		return evaluateItems;
	}

	public void setEvaluateItems(List<EvaluateItem> evaluateItems) {
		this.evaluateItems = evaluateItems;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

}
