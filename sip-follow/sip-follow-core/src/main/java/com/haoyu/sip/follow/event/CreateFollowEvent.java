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
public class CreateFollowEvent extends ApplicationEvent {

	private static final long serialVersionUID = 1461752325230559157L;

	public CreateFollowEvent(Follow follow) {
		super(follow);
	}

}
