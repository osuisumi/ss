 /**
 * 
 */
package com.haoyu.sip.cms.channel.service;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.sip.cms.channel.entity.Channel;
import com.haoyu.sip.core.service.Response;

/**
 * @author Administrator
 *
 */
public interface IChannelService{
	
	Response createChannel(Channel channel);
	
	Response updateChannel(Channel channel);
	
	Response deleteChannel(Channel channel);
	
	Channel findChannelById(String id);
	
	Channel findChannelByAlias(String alias,String domain);
	
	List<Channel> findChannels(Channel channel);
	
	List<Channel> findChannels(Channel channel,PageBounds pageBounds);
	
	List<Channel> findChannels(Map<String,Object> parameter);
	
	List<Channel> findChannels(Map<String,Object> parameter,PageBounds pageBounds);
	
	Map<String,Channel> findChannelsForMap(Map<String,Object> parameter);
	
	List<Channel> getAllParents(Channel channel); 
	
	List<Channel> getAllParents(String id);
}
