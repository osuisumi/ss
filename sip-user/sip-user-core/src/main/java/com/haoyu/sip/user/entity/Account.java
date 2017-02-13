package com.haoyu.sip.user.entity;

import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;

import com.google.common.collect.Lists;
import com.haoyu.sip.auth.entity.AuthRole;
import com.haoyu.sip.core.entity.BaseEntity;


public class Account extends BaseEntity {
	
	private static final long serialVersionUID = -8028208242774870628L;

	private String id;
	
	private String userName;
	
	private String password;
	
	private String roleCode;
	
	private String state;
	
	private String type;
	
	private UserInfo user;
	
	private List<AuthRole> roles = Lists.newArrayList();
	
	public Account(){}

	public Account(String id) {
		super();
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public UserInfo getUser() {
		return user;
	}

	public void setUser(UserInfo user) {
		this.user = user;
	}

	public List<AuthRole> getRoles() {
		return roles;
	}

	public void setRoles(List<AuthRole> roles) {
		this.roles = roles;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}
