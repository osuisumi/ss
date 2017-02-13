package com.haoyu.sip.cms.channel.template;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.google.common.collect.Maps;
import com.haoyu.sip.cms.channel.entity.Channel;
import com.haoyu.sip.cms.channel.service.IChannelService;

import freemarker.core.Environment;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.SimpleScalar;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
/**
 * 获取频道列表数据
 * 参数：parentId 上级频道ID 
 * @author lianghuahuang
 *
 */
@Component
public class ChannelListDataDirective implements TemplateDirectiveModel {
	
	@Autowired
	private IChannelService channelService;

	public void execute(Environment env, @SuppressWarnings("rawtypes") Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		int page = 1;
		int size = 10;
		PageBounds pageBounds = null;
		if(params.containsKey("page")){
			String pageStr = ((SimpleScalar)params.get("page")).getAsString();
			if(StringUtils.isEmpty(pageStr))
				pageStr = "1";
			page = Integer.parseInt(pageStr);
			pageBounds = new PageBounds(page,size);
		}
		if(params.containsKey("size")){
			size = Integer.parseInt(((SimpleScalar)params.get("size")).getAsString());
			pageBounds = new PageBounds(page,size);
		}	
		Map<String,Object> parameter = Maps.newHashMap();
		if(params.containsKey("parentId")){		
			TemplateModel paramValue = (TemplateModel)params.get("parentId");
			String parentId = ((SimpleScalar)paramValue).getAsString();
			parameter.put("parentId", parentId);
		}else{
			if(params.containsKey("parentAlias")){		
				TemplateModel paramValue = (TemplateModel)params.get("parentAlias");
				String parentAlias = ((SimpleScalar)paramValue).getAsString();
				parameter.put("parentAlias", parentAlias);
			}else{
				parameter.put("parentId", "isNull");
			}
		}
		
		List<Channel> channelList = channelService.findChannels(parameter,pageBounds);
		env.__setitem__("channelList", channelList);
		env.setVariable("channelList",  new DefaultObjectWrapper().wrap(channelList));
		body.render(env.getOut());  
	}
	
}