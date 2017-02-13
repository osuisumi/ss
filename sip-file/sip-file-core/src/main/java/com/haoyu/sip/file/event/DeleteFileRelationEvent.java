package com.haoyu.sip.file.event;

import org.springframework.context.ApplicationEvent;

import com.haoyu.sip.file.entity.FileRelation;

public class DeleteFileRelationEvent extends ApplicationEvent{

	private static final long serialVersionUID = -6849749729705376932L;

	public DeleteFileRelationEvent(FileRelation fileRelation) {
		super(fileRelation);
	}

}
