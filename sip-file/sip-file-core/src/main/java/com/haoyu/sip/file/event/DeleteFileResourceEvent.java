package com.haoyu.sip.file.event;

import org.springframework.context.ApplicationEvent;

import com.haoyu.sip.file.entity.FileResource;

public class DeleteFileResourceEvent extends ApplicationEvent{
	
	private static final long serialVersionUID = 970374469314290377L;

	public DeleteFileResourceEvent(FileResource fileResource) {
		super(fileResource);
	}

}
