/**
 * 
 */
package com.haoyu.sip.cms.channel.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;
import com.haoyu.sip.cms.channel.dao.IChannelContentDao;
import com.haoyu.sip.cms.channel.entity.ChannelContent;
import com.haoyu.sip.cms.channel.service.IChannelContentService;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.utils.ThreadContext;

/**
 * @author lianghuahuang
 *
 */
@Service
public class ChannelContentServiceImpl implements IChannelContentService {
	@Resource
	private IChannelContentDao channelContentDao;
	/* (non-Javadoc)
	 * @see com.haoyu.sip.cms.channel.service.IChannelContentService#findChannelContentById(java.lang.String)
	 */
	@Override
	public ChannelContent findChannelContentById(String channelId) {
		return channelContentDao.selectByPrimaryKey(channelId);
	}
	
	@Override
	public ChannelContent findChannelContentByAliasAndDomain(String alias, String domain) {
		Map<String,Object> parameter = Maps.newHashMap();
		parameter.put("alias",alias);
		if(StringUtils.isEmpty(domain)){
			parameter.put("domain",ThreadContext.getDomain());
		}else{
			parameter.put("domain", domain);
		}
		List<ChannelContent> channelContents = channelContentDao.selectByParameter(parameter);
		return channelContents!=null&&!channelContents.isEmpty()?channelContents.get(0):null;
	}


	/* (non-Javadoc)
	 * @see com.haoyu.sip.cms.channel.service.IChannelContentService#createChannelContent(com.haoyu.sip.cms.channel.entity.ChannelContent)
	 */
	@Override
	public Response createChannelContent(ChannelContent channelContent) {
		if(channelContent==null||StringUtils.isEmpty(channelContent.getChannelId())){
			return Response.failInstance().responseMsg("createChannelContent fail!channelContent is null or channelId is null!");
		}
		int count = channelContentDao.insertChannelContent(channelContent);
		return count>0?Response.successInstance().responseData(channelContent):Response.failInstance().responseMsg("createChannelContent fail!");
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.cms.channel.service.IChannelContentService#updateChannelContent(com.haoyu.sip.cms.channel.entity.ChannelContent, boolean)
	 */
	@Override
	public Response updateChannelContent(ChannelContent channelContent,
			boolean createIfNotExists) {
		if(channelContent==null||StringUtils.isEmpty(channelContent.getChannelId())){
			return Response.failInstance().responseMsg("updateChannelContet fail!channelContent is null or channelId is null!");
		}
		int count = channelContentDao.updateChannelContent(channelContent);
		if(count<=0&&createIfNotExists){
			count = channelContentDao.insertChannelContent(channelContent);
		}
		return count>0?Response.successInstance().responseData(channelContent):Response.failInstance().responseMsg("updateChannelContent fail!");
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.cms.channel.service.IChannelContentService#deleteChannelContent(java.lang.String)
	 */
	@Override
	public Response deleteChannelContent(String channelId) {
		int count = channelContentDao.deleteChannelContentByPhysics(channelId);
		return count>0?Response.successInstance():Response.failInstance().responseMsg("deleteChannelContent fail!");
	}


}
