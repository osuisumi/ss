/**
 * 
 */
package com.haoyu.sip.follow.event;

import org.springframework.context.ApplicationEvent;

import com.haoyu.sip.follow.entity.Follow;

/**
 * @author lianghuahuang
 *
 */
public class DeleteFollowEvent extends ApplicationEvent {

	private static final long serialVersionUID = -3810634636706257539L;

	public DeleteFollowEvent(Follow follow) {
		super(follow);
	}

}
