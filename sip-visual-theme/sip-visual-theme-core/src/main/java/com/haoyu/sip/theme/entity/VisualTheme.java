/**
 * 
 */
package com.haoyu.sip.theme.entity;

import com.haoyu.sip.core.entity.BaseEntity;

/**
 * @author lianghuahuang
 *
 */
public class VisualTheme extends BaseEntity {
	private String id;
	
	private VisualThemeSet visualThemeSet;
	
	private String name;
	
	private String description;
	
	private String resourceDir;
	
	public VisualTheme(){}
	
	public VisualTheme(String id){
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public VisualThemeSet getVisualThemeSet() {
		return visualThemeSet;
	}

	public void setVisualThemeSet(VisualThemeSet visualThemeSet) {
		this.visualThemeSet = visualThemeSet;
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

	public String getResourceDir() {
		return resourceDir;
	}

	public void setResourceDir(String resourceDir) {
		this.resourceDir = resourceDir;
	}
	
	
}
