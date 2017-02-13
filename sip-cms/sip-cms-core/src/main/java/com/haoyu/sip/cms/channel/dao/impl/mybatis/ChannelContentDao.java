/**
 * 
 */
package com.haoyu.sip.cms.channel.dao.impl.mybatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.haoyu.sip.cms.channel.dao.IChannelContentDao;
import com.haoyu.sip.cms.channel.entity.ChannelContent;
import com.haoyu.sip.core.jdbc.MybatisDao;

/**
 * @author lianghuahuang
 *
 */
@Repository
public class ChannelContentDao extends MybatisDao implements IChannelContentDao {

	/* (non-Javadoc)
	 * @see com.haoyu.sip.cms.channel.dao.IChannelContentDao#selectByPrimaryKey(java.lang.String)
	 */
	@Override
	public ChannelContent selectByPrimaryKey(String id) {
		return super.selectByPrimaryKey(id);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.cms.channel.dao.IChannelContentDao#updateChannelContent(com.haoyu.sip.cms.channel.entity.ChannelContent)
	 */
	@Override
	public int updateChannelContent(ChannelContent channelContent) {
		channelContent.setUpdateValue();
		return super.update(channelContent);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.cms.channel.dao.IChannelContentDao#insertChannelContent(com.haoyu.sip.cms.channel.entity.ChannelContent)
	 */
	@Override
	public int insertChannelContent(ChannelContent channelContent) {
		channelContent.setDefaultValue();
		return super.insert(channelContent);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.cms.channel.dao.IChannelContentDao#deleteChannelContentByPhysics(java.lang.String)
	 */
	@Override
	public int deleteChannelContentByPhysics(String id) {
		return super.deleteByPhysics(id);
	}

	@Override
	public List<ChannelContent> selectByParameter(Map<String, Object> parameter) {
		return super.selectList("selectByParameter", parameter);
	}

}
