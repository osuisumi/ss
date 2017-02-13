package com.haoyu.sip.auth.event;

import java.util.List;

import org.springframework.context.ApplicationEvent;

import com.haoyu.sip.auth.entity.AuthResource;

public class BatchDeleteAuthResourceEvent extends ApplicationEvent{
	private static final long serialVersionUID = -4009792748841140221L;
	public BatchDeleteAuthResourceEvent(List<AuthResource> roots) {
		super(roots);
	}



}
