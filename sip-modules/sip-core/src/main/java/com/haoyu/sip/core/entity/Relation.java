/**
 * 
 */
package com.haoyu.sip.core.entity;

import java.io.Serializable;
import java.util.Map;

import com.google.common.collect.Maps;

/**
 * @author lianghuahuang
 *
 */
public class Relation implements Serializable{
	
	private String id;
	
	/**
	 * 来源类型：辩论观点、研讨回复等等，由调用方提供
	 */
	private String type;
	
	private Map<String,Object> attribute = Maps.newHashMap();
	
	public Relation(){}
	
	public Relation(String id){
		this.id = id;
	}
	
	public Relation(String id,String type){
		this.id = id;
		this.type = type;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Map<String, Object> getAttribute() {
		return attribute;
	}

	public void setAttribute(Map<String, Object> attribute) {
		this.attribute = attribute;
	}
}
