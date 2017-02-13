package com.haoyu.sip.diary.entity;

import java.util.List;

import com.haoyu.sip.core.entity.BaseEntity;

public class DiaryCategory extends BaseEntity {

	private static final long serialVersionUID = 7025929540027648571L;
	
	private String id;   
	
	private String name; 
	
	private String state;
	
	private List<Diary> diaries;

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

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public List<Diary> getDiaries() {
		return diaries;
	}

	public void setDiaries(List<Diary> diaries) {
		this.diaries = diaries;
	}
}