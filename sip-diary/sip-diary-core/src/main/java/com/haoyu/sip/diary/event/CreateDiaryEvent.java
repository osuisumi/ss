package com.haoyu.sip.diary.event;

import org.springframework.context.ApplicationEvent;

public class CreateDiaryEvent extends ApplicationEvent{
	private static final long serialVersionUID = -1124026354783713942L;
	public CreateDiaryEvent(Object source) {
		super(source);
	}
}
