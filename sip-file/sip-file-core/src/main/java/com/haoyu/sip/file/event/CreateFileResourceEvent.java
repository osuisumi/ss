package com.haoyu.sip.file.event;

import org.springframework.context.ApplicationEvent;

import com.haoyu.sip.file.entity.FileResource;

public class CreateFileResourceEvent extends ApplicationEvent{

	private static final long serialVersionUID = 7125885841612825737L;
	
	public CreateFileResourceEvent(FileResource fileResource) {
		super(fileResource);
	}

}
