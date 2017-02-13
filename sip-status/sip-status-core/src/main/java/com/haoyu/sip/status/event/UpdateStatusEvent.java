package com.haoyu.sip.status.event;

import org.springframework.context.ApplicationEvent;

public class UpdateStatusEvent extends ApplicationEvent {

	private static final long serialVersionUID = -571339684388944975L;

	public UpdateStatusEvent(Object source) {
		super(source);
	}

}
