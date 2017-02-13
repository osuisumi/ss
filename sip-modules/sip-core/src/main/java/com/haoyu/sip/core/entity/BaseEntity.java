/**
 * 
 */
package com.haoyu.sip.core.entity;

import java.io.Serializable;
import java.util.Date;

import com.haoyu.sip.core.utils.ThreadContext;

/**
 * @author lianghuahuang
 *
 */
public class BaseEntity implements Serializable {
	
	protected User creator = new User();
	
	protected User updatedby;
	
	protected long createTime;
	
	protected long updateTime;
	
	protected String isDeleted;
	
	protected int version;
	
	
	public User getCreator() {
		return creator;
	}


	public void setCreator(User creator) {
		this.creator = creator;
	}


	public User getUpdatedby() {
		return updatedby;
	}


	public void setUpdatedby(User updatedby) {
		this.updatedby = updatedby;
	}


	public long getCreateTime() {
		return createTime;
	}


	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}


	public long getUpdateTime() {
		return updateTime;
	}


	public void setUpdateTime(long updateTime) {
		this.updateTime = updateTime;
	}


	public String getIsDeleted() {
		return isDeleted;
	}


	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}


	public int getVersion() {
		return version;
	}


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
