package com.haoyu.sip.attitude.event;

import org.springframework.context.ApplicationEvent;

public class DeleteAttitudeUserEvent extends ApplicationEvent{
	private static final long serialVersionUID = -8155410041054800632L;
	public DeleteAttitudeUserEvent(Object source) {
		super(source);
	}
}
