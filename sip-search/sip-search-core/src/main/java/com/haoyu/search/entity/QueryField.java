package com.haoyu.search.entity;

import java.io.Serializable;

public class QueryField implements Serializable{
	
	private static final long serialVersionUID = 6490823157675462738L;

	private String[] fields = new String[]{};  //检索的字段名, 多个取并集, 即or
		
	private String value;	//检索的关键字或具体值

	public String[] getFields() {
		return fields;
	}

	public void setFields(String[] fields) {
		this.fields = fields;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
}
