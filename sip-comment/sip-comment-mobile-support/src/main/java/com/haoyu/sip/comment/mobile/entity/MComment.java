package com.haoyu.sip.comment.mobile.entity;

import java.io.Serializable;

import com.haoyu.sip.user.mobile.entity.MUser;

public class MComment implements Serializable {

	private static final long serialVersionUID = -5572118772311643089L;

	private String id;
		
	private MUser creator ;
	
	private String content;

	private int childNum;
	
	private int supportNum;
	
	private long createTime;
	
	public MComment() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public MUser getCreator() {
		return creator;
	}

	public void setCreator(MUser creator) {
		this.creator = creator;
	}

	public int getChildNum() {
		return childNum;
	}

	public void setChildNum(int childNum) {
		this.childNum = childNum;
	}

	public int getSupportNum() {
		return supportNum;
	}

	public void setSupportNum(int supportNum) {
		this.supportNum = supportNum;
	}

	public long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}
	
}
