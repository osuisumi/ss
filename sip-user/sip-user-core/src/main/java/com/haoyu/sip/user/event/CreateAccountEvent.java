package com.haoyu.sip.user.event;

import org.springframework.context.ApplicationEvent;

import com.haoyu.sip.user.entity.Account;

public class CreateAccountEvent extends ApplicationEvent{
	
	private static final long serialVersionUID = 8520099642450429508L;

	public CreateAccountEvent(Account source) {
		super(source);
	}


}
