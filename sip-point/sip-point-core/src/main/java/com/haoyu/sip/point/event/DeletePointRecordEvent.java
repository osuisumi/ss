package com.haoyu.sip.point.event;

import org.springframework.context.ApplicationEvent;

public class DeletePointRecordEvent extends ApplicationEvent{
	
	private static final long serialVersionUID = 9145862258224981704L;

	public DeletePointRecordEvent(Object source) {
		super(source);
	}

	

}
