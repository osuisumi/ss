package com.haoyu.sip.file.event;

import org.springframework.context.ApplicationEvent;

import com.haoyu.sip.file.entity.FileRelation;

public class UploadFileEvent extends ApplicationEvent{

	private static final long serialVersionUID = -909714578108779511L;

	public UploadFileEvent(FileRelation fileRelation) {
		super(fileRelation);
	}

}
