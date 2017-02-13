/**
 * 
 */
package com.haoyu.sip.jcr.entity;

import java.io.Serializable;




import org.apache.jackrabbit.ocm.mapper.impl.annotation.Bean;
import org.apache.jackrabbit.ocm.mapper.impl.annotation.Field;
import org.apache.jackrabbit.ocm.mapper.impl.annotation.Node;

import com.haoyu.sip.core.entity.User;

/**
 * @author Administrator
 *
 */

public class JcrEntity implements Serializable {
	
	private static final long serialVersionUID = 1738534485437447378L;
	
	
	private String id;
	
	@Bean
	protected CommonUser creator;
	
	@Bean
	protected CommonUser updatedby;
	
	@Field
	protected long createTime;
	
	@Field
	protected long updateTime;
	
	@Field(uuid=true)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public User getCreator() {
		return creator;
	}



	public User getUpdatedby() {
		return updatedby;
	}



	public void setCreator(CommonUser creator) {
		this.creator = creator;
	}

	public void setUpdatedby(CommonUser updatedby) {
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
	
}
