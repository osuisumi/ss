package com.haoyu.sip.user.entity;

import com.haoyu.sip.core.entity.BaseEntity;
import com.haoyu.sip.core.entity.User;

public class UserInfo extends BaseEntity{
	
	private static final long serialVersionUID = -6677704147758572482L;

	private String id;
	
	private String realName;
	
	private String paperworkNo;
	
	private String bornDate;
	
	private String avatar;
	
	private String summary;
	
	private String sex;
	
	private String post;
	
	private String phone;
	
	private String email;
	
	private Department department;
	
	private String userName;
	
	private String stage;
	
	private String subject;
	
	public UserInfo(){}

	public UserInfo(String id) {
		super();
		this.id = id;
	}
	
	public String getStage() {
		return stage;
	}

	public void setStage(String stage) {
		this.stage = stage;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getPaperworkNo() {
		return paperworkNo;
	}

	public void setPaperworkNo(String paperworkNo) {
		this.paperworkNo = paperworkNo;
	}

	public String getBornDate() {
		return bornDate;
	}

	public void setBornDate(String bornDate) {
		this.bornDate = bornDate;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getPost() {
		return post;
	}

	public void setPost(String post) {
		this.post = post;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public User getUser(){
		User user = new User();
		user.setId(this.getId());
		user.setAvatar(this.getAvatar());
		user.setRealName(this.getRealName());
		return user;
	}
}
