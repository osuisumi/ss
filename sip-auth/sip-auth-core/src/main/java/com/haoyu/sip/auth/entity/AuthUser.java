/**
 * 
 */
package com.haoyu.sip.auth.entity;

import java.util.List;

import com.haoyu.sip.core.entity.User;
import com.haoyu.sip.core.utils.ThreadContext;

/**
 * @author lianghuahuang
 *
 */
public class AuthUser extends User {
	
	private List<AuthRole> roles;
	
	/**
	 * 用户所关联的对象ID，可以是机构ID等等
	 */
	private String relationId;
	
	private String password;
	
	private String username;
	
	private User creator = new User();
	
	private User updatedby;
	
	private long createTime;
	
	private long updateTime;
	
	private String isDeleted;
	
	private int version;

	public AuthUser(String id) {
		super(id);
	}
	
	public AuthUser(){
		
	}

	/**
	 * @return the relationId
	 */
	public String getRelationId() {
		return relationId;
	}

	/**
	 * @param relationId the relationId to set
	 */
	public void setRelationId(String relationId) {
		this.relationId = relationId;
	}

	public List<AuthRole> getRoles() {
		return roles;
	}

	public void setRoles(List<AuthRole> roles) {
		this.roles = roles;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the creator
	 */
	public User getCreator() {
		return creator;
	}

	/**
	 * @param creator the creator to set
	 */
	public void setCreator(User creator) {
		this.creator = creator;
	}

	/**
	 * @return the updatedby
	 */
	public User getUpdatedby() {
		return updatedby;
	}

	/**
	 * @param updatedby the updatedby to set
	 */
	public void setUpdatedby(User updatedby) {
		this.updatedby = updatedby;
	}

	/**
	 * @return the createTime
	 */
	public long getCreateTime() {
		return createTime;
	}

	/**
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}

	/**
	 * @return the updateTime
	 */
	public long getUpdateTime() {
		return updateTime;
	}

	/**
	 * @param updateTime the updateTime to set
	 */
	public void setUpdateTime(long updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * @return the isDeleted
	 */
	public String getIsDeleted() {
		return isDeleted;
	}

	/**
	 * @param isDeleted the isDeleted to set
	 */
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}

	/**
	 * @return the version
	 */
	public int getVersion() {
		return version;
	}

	/**
	 * @param version the version to set
	 */
	public void setVersion(int version) {
		this.version = version;
	}
	
	public void setDefaultValue(){
		this.createTime = System.currentTimeMillis();
		this.isDeleted = "N";
		this.updateTime = System.currentTimeMillis();
		this.version = 1;
		User user = ThreadContext.getUser();
		if(user!=null&&user.getId()!=null){
			this.setCreator(ThreadContext.getUser());
		}
	}
	
	public void setUpdateValue(){
		this.updateTime = System.currentTimeMillis();
		User user = ThreadContext.getUser();
		if(user!=null&&user.getId()!=null){
			this.setUpdatedby(ThreadContext.getUser());
		}
	}
}
