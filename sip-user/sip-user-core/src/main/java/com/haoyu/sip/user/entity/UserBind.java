package com.haoyu.sip.user.entity;

import com.haoyu.sip.core.entity.BaseEntity;

public class UserBind extends BaseEntity{
	
	private static final long serialVersionUID = 4608478151207716791L;

	private String id;
	
	private UserInfo userInfo;
	
	private String bindId;
	
	private String type;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	public String getBindId() {
		return bindId;
	}

	public void setBindId(String bindId) {
		this.bindId = bindId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
