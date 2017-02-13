package com.haoyu.sip.point.event;

import org.springframework.context.ApplicationEvent;

import com.haoyu.sip.point.entity.PointRecord;

public class ChangePointRecordEvent extends ApplicationEvent{
	private static final long serialVersionUID = 1203577842354746266L;

	public ChangePointRecordEvent(PointRecord source) {
		super(source);
	}



}
