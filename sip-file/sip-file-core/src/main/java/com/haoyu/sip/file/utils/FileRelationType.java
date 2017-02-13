package com.haoyu.sip.file.utils;

public enum FileRelationType {

	LESSON_PLAN_RELATION("lesson_plan_relation"), RESOURCES("resources"), PLAN_RESULT("plan_result");
	
	private String type;
	
	private FileRelationType(String type){
		this.type = type;
	}
	
	public String toString(){
		return type;
	}
	
}
