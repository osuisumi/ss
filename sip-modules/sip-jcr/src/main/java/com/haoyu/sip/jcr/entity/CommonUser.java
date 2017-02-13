package com.haoyu.sip.jcr.entity;

import org.apache.jackrabbit.ocm.mapper.impl.annotation.Field;
import org.apache.jackrabbit.ocm.mapper.impl.annotation.Node;

import com.haoyu.sip.core.entity.User;

@Node
public class CommonUser extends User {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1620049061005266633L;

	public CommonUser(){
		super();
	}
	public CommonUser(String id ){
		super(id);
	}
	@Field(id=true)
	public String getId() {
		return super.getId();
	}

	@Field
	public String getRealName() {
		return super.getRealName();
	}

	@Field
	public String getAvatar() {
		return super.getAvatar();
	}

}
