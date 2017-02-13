package com.haoyu.sip.sign.event;

import org.springframework.context.ApplicationEvent;

public class CreateSignUserEvent extends ApplicationEvent {

	private static final long serialVersionUID = 4041844873457017768L;

	public CreateSignUserEvent(Object source) {
		super(source);
	}

}
