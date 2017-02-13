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
public class CreateChannelEvent extends ApplicationEvent {

	public CreateChannelEvent(Channel channel) {
		super(channel);
	}

}
