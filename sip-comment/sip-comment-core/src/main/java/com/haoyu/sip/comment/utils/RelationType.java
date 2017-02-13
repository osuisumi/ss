package com.haoyu.sip.comment.utils;

public enum RelationType {
	
	LESSONPLAN("lessonPlan"), RESOURCE("resource");
	
	private String type;
	
	private RelationType(String type){
		this.type = type;
	}
	
	public String toString(){
		return type;
	}

}
