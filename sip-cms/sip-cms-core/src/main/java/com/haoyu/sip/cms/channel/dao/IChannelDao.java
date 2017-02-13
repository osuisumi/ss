/**
 * 
 */
package com.haoyu.sip.cms.channel.dao;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.sip.cms.channel.entity.Channel;


/**
 * @author Administrator
 *
 */

public interface IChannelDao {
    /** 查 */
	Channel selectChannelById(String id);
	/** 增 */
	int insertChannel(Channel channel);
	/** 改 */
	int updateChannel(Channel channel);
	/** 删   逻辑上 */
	int deleteChannelByLogic(Channel channel);
	/** 删  物理上 */
	int deleteChannelByPhysics(String id);
	/** 查  条件*/
	List<Channel> findAll(Map<String, Object> parameter);
	
	Map<String,Channel> selectChannelForMap(Map<String, Object> parameter);
	/** 查  条件  分页*/
	List<Channel> findAll(Map<String, Object> parameter,PageBounds pageBounds);
}
