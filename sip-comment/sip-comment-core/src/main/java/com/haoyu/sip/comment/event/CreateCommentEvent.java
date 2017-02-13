package com.haoyu.sip.comment.event;

import org.springframework.context.ApplicationEvent;

import com.haoyu.sip.comment.entity.Comment;

public class CreateCommentEvent extends ApplicationEvent {

	private static final long serialVersionUID = -2833933069388455217L;

	public CreateCommentEvent(Comment comment) {
		super(comment);
	}

}
