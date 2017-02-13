package com.haoyu.sip.auth.event;

import java.util.List;

import org.springframework.context.ApplicationEvent;

import com.haoyu.sip.auth.entity.AuthMenu;

public class BatchDeleteAuthMenuEvent extends ApplicationEvent {
	private static final long serialVersionUID = -2007918693174179416L;

	public BatchDeleteAuthMenuEvent(List<AuthMenu> source) {
		super(source);
	}

}
