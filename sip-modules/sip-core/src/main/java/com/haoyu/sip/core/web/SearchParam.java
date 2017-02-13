package com.haoyu.sip.core.web;

import java.util.HashMap;
import java.util.Map;



/**
 * 客户端请求参数对象
 */
public class SearchParam{
	
	private static final long serialVersionUID = 8081267402507908402L;
	
	private Map<String,Object> paramMap;
	
	public SearchParam(){
		this.paramMap = new HashMap<String,Object>();
	}
	
	public Map<String, Object> getParamMap() {
		return paramMap;
	}

	public void setParamMap(Map<String, Object> param) {
		this.paramMap = param;
	}
	
		
}
