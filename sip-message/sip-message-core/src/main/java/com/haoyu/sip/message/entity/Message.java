package com.haoyu.sip.message.entity;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import com.haoyu.sip.core.entity.BaseEntity;
import com.haoyu.sip.core.entity.User;
import com.haoyu.sip.message.utils.MessageState;
import com.haoyu.sip.message.utils.MessageType;

public class Message extends BaseEntity {

	private static final long serialVersionUID = -2401134848532405515L;

	private String id;

	private User sender;

	private User receiver;

	private String title;

	private String content;

	private String senderState = MessageState.OUT_BOX;

	private Date senderStateChangeDate;

	private String receiverState = MessageState.IN_BOX;

	private Date receiverStateChangeDate;

	private String type = MessageType.USER_MESSAGE;

	private Boolean readed = Boolean.FALSE;

	private Boolean replied = Boolean.FALSE;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public User getSender() {
		return sender;
	}

	public void setSender(User sender) {
		this.sender = sender;
	}

	public User getReceiver() {
		return receiver;
	}

	public void setReceiver(User receiver) {
		this.receiver = receiver;
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

	public Date getSenderStateChangeDate() {
		return senderStateChangeDate;
	}

	public void setSenderStateChangeDate(Date senderStateChangeDate) {
		this.senderStateChangeDate = senderStateChangeDate;
	}

	public Date getReceiverStateChangeDate() {
		return receiverStateChangeDate;
	}

	public void setReceiverStateChangeDate(Date receiverStateChangeDate) {
		this.receiverStateChangeDate = receiverStateChangeDate;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Boolean getReaded() {
		return readed;
	}

	public void setReaded(Boolean readed) {
		this.readed = readed;
	}

	public Boolean getReplied() {
		return replied;
	}

	public void setReplied(Boolean replied) {
		this.replied = replied;
	}

	public String getSenderState() {
		return senderState;
	}

	public void setSenderState(String senderState) {
		this.senderState = senderState;
	}

	public String getReceiverState() {
		return receiverState;
	}

	public void setReceiverState(String receiverState) {
		this.receiverState = receiverState;
	}
	
	public boolean isEffective(){
		if(this.sender == null || this.receiver == null){
			return false;
		}
		if(StringUtils.isAnyEmpty(this.sender.getId(),this.receiver.getId(),this.content)){
			return false;
		}
		return true;
	}

}
