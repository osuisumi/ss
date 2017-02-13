package com.haoyu.sip.core.service;


import java.io.Serializable;

import com.haoyu.sip.core.mapper.JsonMapper;


/**
 *
 *
 *@author 梁华璜
 */
public class Response implements Serializable {
	
	private static final long serialVersionUID = 6601119505899497676L;
	//响应码：00代表成功
	private String responseCode;
	//响应消息：可自定义，默认响应码00为成功
	private String responseMsg;
	//响应成功返回的数据
	private Object responseData;
	
	public Response(){
		
	}

	public Response(String responseCode) {
		this.responseCode = responseCode;
	}

	public Response(String responseCode, String responseMsg, Object responseData) {
		this.responseCode = responseCode;
		this.responseMsg = responseMsg;
		this.responseData = responseData;
	}

	public Response(String responseCode, String responseMsg) {
		super();
		this.responseCode = responseCode;
		this.responseMsg = responseMsg;
	}

	public String getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	public String getResponseMsg() {
		return responseMsg;
	}

	public void setResponseMsg(String responseMsg) {
		this.responseMsg = responseMsg;
	}

	public Object getResponseData() {
		return responseData;
	}

	public void setResponseData(Object responseData) {
		this.responseData = responseData;
	}
	
	public static Response newInstance(String json){
			return JsonMapper.nonEmptyMapper().fromJson(json, Response.class);
	}
	
	public static Response successInstance(){
		return new Response("00");
	}
	
	public static Response failInstance(){
		return new Response("01");
	}
	
	public Response responseMsg(String responseMsg){
		this.responseMsg = responseMsg;
		return this;
	}
	
	public Response responseData(Object responseData){
		this.responseData = responseData;
		return this;
	}
	
	public String toString(){
			return JsonMapper.nonEmptyMapper().toJson(this);
	}
	
	public boolean isSuccess(){
		if(this.responseCode.equals("00")){
			return true;
		}
		return false;
	}
}
