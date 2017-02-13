package com.haoyu.sip.note.entity;

import com.haoyu.sip.core.entity.BaseEntity;
import com.haoyu.sip.core.entity.Relation;

public class Note extends BaseEntity {
	
	private static final long serialVersionUID = -5941794353906663169L;

	private String id;

	private String title;
	
	private String content;

	private String state;

	private Relation relation ;
	
	public Note() {
		super();
	}
	
	public Note(String id) {
		super();
		this.id = id;
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Relation getRelation() {
		return relation;
	}

	public void setRelation(Relation relation) {
		this.relation = relation;
	}

}
