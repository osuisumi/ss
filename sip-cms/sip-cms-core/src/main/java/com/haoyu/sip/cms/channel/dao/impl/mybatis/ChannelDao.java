/**
 * 
 */
package com.haoyu.sip.cms.channel.dao.impl.mybatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.sip.cms.channel.dao.IChannelDao;
import com.haoyu.sip.cms.channel.entity.Channel;
import com.haoyu.sip.core.jdbc.MybatisDao;

/**
 * @author Administrator
 *
 */
@Repository
public class ChannelDao extends MybatisDao implements IChannelDao {

	/* (non-Javadoc)
	 * @see com.haoyu.sip.cms.channel.dao.IChannelDao#selectChannelById(java.lang.String)
	 */
	@Override
	public Channel selectChannelById(String id) {
		return super.selectByPrimaryKey(id);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.cms.channel.dao.IChannelDao#insertChannel(com.haoyu.sip.cms.channel.entity.Channel)
	 */
	@Override
	public int insertChannel(Channel channel) {
		channel.setDefaultValue();
		return super.insert(channel);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.cms.channel.dao.IChannelDao#updateChannel(com.haoyu.sip.cms.channel.entity.Channel)
	 */
	@Override
	public int updateChannel(Channel channel) {
		channel.setUpdateValue();
		return super.update(channel);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.cms.channel.dao.IChannelDao#deleteChannelByLogic(com.haoyu.sip.cms.channel.entity.Channel)
	 */
	@Override
	public int deleteChannelByLogic(Channel channel) {
		channel.setUpdateValue();
		return super.deleteByLogic(channel);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.cms.channel.dao.IChannelDao#deleteChannelByPhysics(java.lang.String)
	 */
	@Override
	public int deleteChannelByPhysics(String id) {
		return super.deleteByPhysics(id);
	}


	/* (non-Javadoc)
	 * @see com.haoyu.sip.cms.channel.dao.IChannelDao#findAll(java.util.Map, com.github.miemiedev.mybatis.paginator.domain.PageBounds)
	 */
	@Override
	public List<Channel> findAll(Map<String, Object> parameter,
			PageBounds pageBounds) {
		return super.selectList("selectByParameter", parameter, pageBounds);
	}

	@Override
	public List<Channel> findAll(Map<String, Object> parameter) {
		return super.selectList("selectByParameter",parameter);
	}

	@Override
	public Map<String, Channel> selectChannelForMap(
			Map<String, Object> parameter) {
		return super.selectMap("selectByParameter", parameter, "id");
	}

}
