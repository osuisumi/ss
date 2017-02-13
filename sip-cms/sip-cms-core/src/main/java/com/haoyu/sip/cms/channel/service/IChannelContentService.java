package com.haoyu.sip.cms.channel.service;

import com.haoyu.sip.cms.channel.entity.ChannelContent;
import com.haoyu.sip.core.service.Response;

public interface IChannelContentService {
	
	ChannelContent findChannelContentById(String channelId);
	
	ChannelContent findChannelContentByAliasAndDomain(String alias,String domain);
	
	Response createChannelContent(ChannelContent channelContent);
	
	Response updateChannelContent(ChannelContent channelContent,boolean createIfNotExists);
	
	Response deleteChannelContent(String channelId);
}
