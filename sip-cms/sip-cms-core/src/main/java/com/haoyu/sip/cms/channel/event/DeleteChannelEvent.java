/**
 * 
 */
package com.haoyu.sip.cms.channel.event;

import org.springframework.context.ApplicationEvent;

import com.haoyu.sip.cms.channel.entity.Channel;

/**
 * @author lianghuahuang
 *
 */
public class DeleteChannelEvent extends ApplicationEvent {

	public DeleteChannelEvent(Channel channel) {
		super(channel);
	}

}
