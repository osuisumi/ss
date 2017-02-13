/**
 * 
 */
package com.haoyu.sip.cms.channel.dao;

import java.util.List;
import java.util.Map;

import com.haoyu.sip.cms.channel.entity.ChannelContent;

/**
 * @author lianghuahuang
 *
 */
public interface IChannelContentDao {
	
	ChannelContent selectByPrimaryKey(String id);
	
	List<ChannelContent> selectByParameter(Map<String,Object> parameter);
	
	int updateChannelContent(ChannelContent channelContent);
	
	int insertChannelContent(ChannelContent channelContent);
	
	int deleteChannelContentByPhysics(String id);
}
