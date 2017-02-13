/**
 * 
 */
package com.haoyu.sip.core.entity;

import java.io.Serializable;

/**
 * @author lianghuahuang
 *
 */
public class User implements Serializable {
	
	private String id;
	
	private String realName;
	
	private String avatar;
	
	private String deptName;
	
	public User(){}
	
	public User(String id){
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", realName=" + realName + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if(obj!=null && obj instanceof User){
			User user = (User)obj;
			if(user.getId()!=null&&id!=null&&user.getId().equals(id)){
				return true;
			}
		}
		return false;
	}
}
