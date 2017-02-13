package com.haoyu.sip.auth.event;

import java.util.List;

import org.springframework.context.ApplicationEvent;

public class BatchDeleteAuthPermissionEvent extends ApplicationEvent {
	private static final long serialVersionUID = -3220764825021525977L;

	public BatchDeleteAuthPermissionEvent(List<String> ids) {
		super(ids);
	}
}
