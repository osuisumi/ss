package com.haoyu.sip.auth.event;

import java.util.List;

import org.springframework.context.ApplicationEvent;

public class BatchDeleteAuthRoleEvent extends ApplicationEvent{
	private static final long serialVersionUID = -7329468762068082299L;
	
	public BatchDeleteAuthRoleEvent(List<String> ids) {
		super(ids);
	}
}
