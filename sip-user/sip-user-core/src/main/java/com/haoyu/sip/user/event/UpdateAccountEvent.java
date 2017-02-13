package com.haoyu.sip.user.event;

import org.springframework.context.ApplicationEvent;

import com.haoyu.sip.user.entity.Account;

public class UpdateAccountEvent extends ApplicationEvent{
	
	private static final long serialVersionUID = 458615480945134956L;

	public UpdateAccountEvent(Account source) {
		super(source);
	}


}
