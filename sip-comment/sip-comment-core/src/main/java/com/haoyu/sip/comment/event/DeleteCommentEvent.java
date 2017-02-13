package com.haoyu.sip.comment.event;

import org.springframework.context.ApplicationEvent;

import com.haoyu.sip.comment.entity.Comment;

public class DeleteCommentEvent extends ApplicationEvent {

	private static final long serialVersionUID = 556051530162943402L;

	public DeleteCommentEvent(Comment comment) {
		super(comment);
	}

}
