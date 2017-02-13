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
public class UpdateChannelEvent extends ApplicationEvent {

	public UpdateChannelEvent(Channel channel) {
		super(channel);
	}

}
