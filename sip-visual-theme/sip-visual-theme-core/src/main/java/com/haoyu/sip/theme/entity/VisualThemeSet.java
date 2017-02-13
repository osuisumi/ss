/**
 * 
 */
package com.haoyu.sip.theme.entity;

import com.haoyu.sip.core.entity.BaseEntity;

/**
 * @author lianghuahuang
 *
 */
public class VisualThemeSet extends BaseEntity{
	
	private String id;
	
	private String name;
	
	private String description;
	
	public VisualThemeSet(){}
	
	public VisualThemeSet(String id){
		this.id = id;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
