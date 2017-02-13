package com.haoyu.sip.user.mobile.entity;

import java.io.Serializable;

import com.haoyu.sip.file.utils.FileUtils;
import com.haoyu.sip.textbook.mobile.entity.MTextBookEntry;

public class MUser implements Serializable{
	
	private static final long serialVersionUID = -4787068892235805754L;

	private String id;
	
	private String realName;
	
	private String avatar;
	
	private String deptName;

	private String phone;
	
	private String email;
	
	private MTextBookEntry mStage;
	
	private MTextBookEntry mSubject;
		
	public MUser() {
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

	public String getAvatar() {
		String prefix = FileUtils.getHttpHost();
		if (avatar != null && !avatar.contains(prefix)) {
			avatar = prefix + avatar;
		}
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
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

	public MTextBookEntry getmStage() {
		return mStage;
	}

	public void setmStage(MTextBookEntry mStage) {
		this.mStage = mStage;
	}

	public MTextBookEntry getmSubject() {
		return mSubject;
	}

	public void setmSubject(MTextBookEntry mSubject) {
		this.mSubject = mSubject;
	}
	
}
