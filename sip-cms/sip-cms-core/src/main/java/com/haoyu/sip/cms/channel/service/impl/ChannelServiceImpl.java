/**
 * 
 */
package com.haoyu.sip.cms.channel.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.haoyu.sip.cms.channel.dao.IChannelDao;
import com.haoyu.sip.cms.channel.entity.Channel;
import com.haoyu.sip.cms.channel.event.CreateChannelEvent;
import com.haoyu.sip.cms.channel.event.DeleteChannelEvent;
import com.haoyu.sip.cms.channel.event.UpdateChannelEvent;
import com.haoyu.sip.cms.channel.service.IChannelService;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.utils.ThreadContext;
import com.haoyu.sip.utils.Identities;

/**
 * @author Administrator
 *
 */
@Service
public class ChannelServiceImpl implements IChannelService{
	@Resource
	private IChannelDao channelDao;
	
	@Resource
	private ApplicationContext applicationContext;  
	
	/* (non-Javadoc)
	 * @see com.haoyu.sip.cms.channel.service.IChannelService#createChannel(com.haoyu.sip.cms.channel.entity.Channel)
	 */
	@CacheEvict(value="channels",allEntries = true) 
	public Response createChannel(Channel channel) {
		if(channel==null){
			return Response.failInstance().responseMsg("createChannel fail!channel is null!");
		}
		if(StringUtils.isEmpty(channel.getAlias())){
			return Response.failInstance().responseMsg("createChannel fail! alias is null!");
		}
		//判断别名是否存在冲突
		Map<String,Object> map = Maps.newHashMap();
		map.put("alias", channel.getAlias());
		List<Channel> channelList = channelDao.findAll(map);
		if(channelList.size() >0){
			return Response.failInstance().responseMsg("createChannel is fail!alias is duplicate!");
		}
		
		if(StringUtils.isEmpty(channel.getId())){
			channel.setId(Identities.uuid2());
		}

		channel.setUrl("/channel/"+ channel.getAlias());
		int count = channelDao.insertChannel(channel);
		if(count>0){
			applicationContext.publishEvent(new CreateChannelEvent(channel));
		}
		return count>0?Response.successInstance().responseData(channel):Response.failInstance().responseMsg("createChannel fail!");
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.cms.channel.service.IChannelService#updateChannel(com.haoyu.sip.cms.channel.entity.Channel)
	 */
	@CacheEvict(value="channels",allEntries = true) 
	public Response updateChannel(Channel channel) {
		if(channel==null||StringUtils.isEmpty(channel.getId())){
			return Response.failInstance().responseMsg("updateChannel is fail!channel is null or channel's id is null");
		}
		int count = channelDao.updateChannel(channel);
		if(count>0){
			applicationContext.publishEvent(new UpdateChannelEvent(channel));
		}
		return count>0?Response.successInstance().responseData(channel):Response.failInstance().responseMsg("updateChannel fail!");
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.cms.channel.service.IChannelService#deleteChannel(com.haoyu.sip.cms.channel.entity.Channel)
	 */
	@CacheEvict(value="channels",allEntries = true) 
	public Response deleteChannel(Channel channel) {
		if(channel==null||StringUtils.isEmpty(channel.getId())){
			return Response.failInstance().responseMsg("deleteChannel is fail!channel is null or channel's id is null");
		}
		int count = channelDao.deleteChannelByLogic(channel);
		if(count>0){
			applicationContext.publishEvent(new DeleteChannelEvent(channel));
		}
		return count>0?Response.successInstance():Response.failInstance().responseMsg("deleteChannel fail!");
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.cms.channel.service.IChannelService#findChannelById(java.lang.String)
	 */
	public Channel findChannelById(String id) {
		if(StringUtils.isEmpty(id)){
			return null;
		}
		return channelDao.selectChannelById(id);
	}


	public Channel findById(String id) {
		if(StringUtils.isEmpty(id)){
			return null;
		}
		return channelDao.selectChannelById(id);
	}


	@Override
	@Cacheable(key="'chas_'+(#channel.alias==null?'':#channel.alias)+(#channel.relationId==null?'':#channel.relationId)+(#pageBounds==null?'':(#pageBounds.page+'_'+#pageBounds.limit))",value="channels")
	public List<Channel> findChannels(Channel channel,PageBounds pageBounds) {
		Map<String, Object> parameter = Maps.newHashMap();
		if(channel!=null){
			if(StringUtils.isNotEmpty(channel.getAlias())){
				parameter.put("alias", channel.getAlias());
			}
		/*	if(StringUtils.isNotEmpty(channel.getName())){
				parameter.put("name", channel.getName());
			}*/
			if(StringUtils.isNotEmpty(channel.getRelationId())){
				parameter.put("relationId",channel.getRelationId());
			}
		}
		return channelDao.findAll(parameter, pageBounds);
	}

	@Override
	@Cacheable(value="channels",key="(#domain==null?'':#domain)+#alias")
	public Channel findChannelByAlias(String alias,String domain) {
		if(StringUtils.isNotEmpty(alias)){
			Map<String, Object> parameter = Maps.newHashMap();
			parameter.put("alias", alias);
			if(StringUtils.isEmpty(domain)){
				parameter.put("domain", ThreadContext.getDomain());
			}else{
				parameter.put("domain", domain);
			}
			List<Channel> channels = channelDao.findAll(parameter);
			return channels!=null&&channels.size()>0?channels.get(0):null;
		}
		return null;
	}

	@Override
	@Cacheable(key="'chas_'+(#channel.alias==null?'':#channel.alias)+(#channel.relationId==null?'':#channel.relationId)",value="channels")
	public List<Channel> findChannels(Channel channel) {
		return findChannels(channel,null);
	}

	@Override
	@Cacheable(key="'chas_'+(#parameter['userId']?:'')+(#parameter['parentId']?:'')+(#parameter['relationId']?:'')",value="channels")
	public List<Channel> findChannels(Map<String, Object> parameter) {
		return channelDao.findAll(parameter);
	}

	@Override
	@Cacheable(key="'chas_'+(#parameter['parentId']==null?'':#parameter['parentId'])+(#parameter['parentAlias']==null?'':#parameter['parentAlias'])+(#parameter['relationId']==null?'':#parameter['relationId'])+(#pageBounds==null?'':(#pageBounds.page+'_'+#pageBounds.limit))",value="channels")
	public List<Channel> findChannels(Map<String, Object> parameter,
			PageBounds pageBounds) {
		return channelDao.findAll(parameter, pageBounds);
	}

	@Override
	@Cacheable(key="'chaMap_'+(#parameter['relationId']==null?'':#parameter['relationId'])",value="channels")
	public Map<String, Channel> findChannelsForMap(Map<String, Object> parameter) {
		return channelDao.selectChannelForMap(parameter);
	}

	@Override
	public List<Channel> getAllParents(Channel channel) {
		if(channel!=null&&channel.getParent()!=null){
			List<Channel> channels = Lists.newArrayList();
			Channel parentChannel = channel.getParent();
			Map<String,Channel> channelMap = channelDao.selectChannelForMap(Maps.newHashMap());
			if(channelMap.containsKey(parentChannel.getId())){
				parentChannel = channelMap.get(parentChannel.getId());
				channels.add(parentChannel);
			}
			while(parentChannel.getParent()!=null){
				parentChannel = channelMap.get(parentChannel.getId());
				channels.add(parentChannel);
			}
			return channels;
		}
		return null;
	}

	@Override
	public List<Channel> getAllParents(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	/*@Override
	public List<Channel> getChannelsTree(Channel channel) {
		Map<String, Object> parameter = Maps.newHashMap();
		if(channel!=null){
			if(StringUtils.isNotEmpty(channel.getAlias())){
			
				parameter.put("alias", channel.getAlias());
			}
			if(StringUtils.isNotEmpty(channel.getName())){
				parameter.put("name", channel.getName());
			}
			if(StringUtils.isNotEmpty(channel.getRelationId())){
				parameter.put("relationId",channel.getRelationId());
			}
		}
		List<Channel> channels =  channelDao.findAll(parameter, null);
		return buildChannelTree(Collections3.extractToMap(channels, "id", null));
		
	}
	
	private List<Channel> buildChannelTree(Map<String,Channel> channelMap){
		List<Channel> roots = new ArrayList<Channel>();
		for(Map.Entry<String, Channel> entry:channelMap.entrySet()){
			Channel channel = entry.getValue();
			if(channel.getParent() == null || StringUtils.isEmpty(channel.getParent().getId())){
				roots.add(channel);
			}else{
				setParent(channel,channelMap);
			}
			
		}
		return roots;
	}
	
	private void setParent(Channel channel,Map<String,Channel> channelMap){
		if (channel.getParent() != null) {
			String parentId = channel.getParent().getId();
			Channel parent = channelMap.get(parentId);
			parent.addChild(channel);
			setParent(parent, channelMap);
		}
	}*/


}
