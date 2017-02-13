package com.haoyu.sip.cms.channel.template;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.haoyu.sip.cms.channel.entity.Channel;
import com.haoyu.sip.cms.channel.service.IChannelService;
import com.haoyu.sip.core.utils.ThreadContext;

import freemarker.core.Environment;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.SimpleScalar;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

/**
 * freemarker .ftl文件中所使用的标签处理程序
 * 用于从后台获取相对应的数据
 * 通过栏目的别名 获取栏目的信息
 * 参数：alias 别名
 * @author lianghuahuang
 */
@Component
public class ChannelAllParentDataDirective implements TemplateDirectiveModel {
	
	@Autowired
	private IChannelService channelService;

	public void execute(Environment env, @SuppressWarnings("rawtypes") Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		String channelId = null;
		if(params.containsKey("channelId")){
			TemplateModel paramValue = (TemplateModel)params.get("channelId");
			channelId = ((SimpleScalar)paramValue).getAsString();
		}	
		if(channelId!=null){
			Channel channel = channelService.findChannelById(channelId);
			if(channel!=null){
				List<Channel>  allParents = getAllParents(channel); 
				env.setVariable("currentChannel",  new DefaultObjectWrapper().wrap(channel));
				env.setVariable("allParentChannels",  new DefaultObjectWrapper().wrap(allParents));
				body.render(env.getOut());  
			}
		}
		
	}
	
	private List<Channel> getAllParents(Channel channel){
		if(channel!=null&&channel.getParent()!=null){
			List<Channel> channels = Lists.newArrayList();
			Channel parentChannel = channel.getParent();
			Map<String,Channel> channelMap = channelService.findChannelsForMap(Maps.newHashMap());
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
	
}