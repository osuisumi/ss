package com.haoyu.sip.file.event;

import org.springframework.context.ApplicationEvent;

import com.haoyu.sip.file.entity.FileResource;

public class UpdateFileResourceEvent extends ApplicationEvent {

	private static final long serialVersionUID = -5922773277998031293L;

	public UpdateFileResourceEvent(FileResource fileResource) {
		super(fileResource);
	}

}
