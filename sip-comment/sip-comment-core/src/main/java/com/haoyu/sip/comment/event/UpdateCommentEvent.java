/**
 * 
 */
package com.haoyu.sip.comment.event;

import org.springframework.context.ApplicationEvent;

import com.haoyu.sip.comment.entity.Comment;

public class UpdateCommentEvent extends ApplicationEvent {

	private static final long serialVersionUID = -4702296217146139144L;

	public UpdateCommentEvent(Comment comment) {
		super(comment);
	}

}
