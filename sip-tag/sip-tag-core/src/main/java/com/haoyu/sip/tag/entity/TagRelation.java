package com.haoyu.sip.tag.entity;

import java.io.Serializable;
import com.haoyu.sip.core.entity.Relation;



public class TagRelation implements Serializable{
	
	private static final long serialVersionUID = -3558468743056941987L;

	private Tag tag;

    private Relation relation;


	public Tag getTag() {
		return tag;
	}

	public void setTag(Tag tag) {
		this.tag = tag;
	}

	public Relation getRelation() {
		return relation;
	}

	public void setRelation(Relation relation) {
		this.relation = relation;
	}

	

    
}