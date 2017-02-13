package com.haoyu.sip.message.mobile.entity;

import java.io.Serializable;

import com.haoyu.sip.user.mobile.entity.MUser;

public class MMessage implements Serializable{

	private static final long serialVersionUID = 4463451643055715483L;
	
	private String id;
	
	private MUser sender;

	private MUser receiver;

	private String title;

	private String content;

	private String type;
	
	private long createTime;
	
	public MMessage() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public MUser getSender() {
		return sender;
	}

	public void setSender(MUser sender) {
		this.sender = sender;
	}

	public MUser getReceiver() {
		return receiver;
	}

	public void setReceiver(MUser receiver) {
		this.receiver = receiver;
	}

	public long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}
	
}
