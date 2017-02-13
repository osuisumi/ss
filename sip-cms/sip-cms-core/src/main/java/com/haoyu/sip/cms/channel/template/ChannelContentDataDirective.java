package com.haoyu.sip.cms.channel.template;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.haoyu.sip.cms.channel.entity.ChannelContent;
import com.haoyu.sip.cms.channel.service.IChannelContentService;
import com.haoyu.sip.core.utils.ThreadContext;

import freemarker.core.Environment;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.SimpleScalar;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

/**
 * 通过从ChannelContentId 获取 ChannelContent
 * 参数：ChannelContentId 文章id,incrBrowseNum 是否更新浏览数
 * @author lianghuahuang
 */
@Component
public class ChannelContentDataDirective implements TemplateDirectiveModel {
	
	@Autowired
	private IChannelContentService channelContentService;

	public void execute(Environment env, @SuppressWarnings("rawtypes") Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		if(params.containsKey("channelId")){
			TemplateModel paramValue = (TemplateModel)params.get("channelId");
			String id = ((SimpleScalar)paramValue).getAsString();
			ChannelContent channelContent = channelContentService.findChannelContentById(id);		
			if(channelContent==null&&params.containsKey("createIfNotExists")){
					ChannelContent newChannelContent = new ChannelContent(id);
					channelContentService.createChannelContent(newChannelContent);
					env.setVariable("channelContent",  new DefaultObjectWrapper().wrap(newChannelContent));
					body.render(env.getOut());  
					return;
			}	
			env.setVariable("channelContent",  new DefaultObjectWrapper().wrap(channelContent));
			body.render(env.getOut());  
		}else if(params.containsKey("alias")){
			TemplateModel paramValue = (TemplateModel)params.get("alias");
			String alias = ((SimpleScalar)paramValue).getAsString();
			ChannelContent channelContent =channelContentService.findChannelContentByAliasAndDomain(alias, ThreadContext.getDomain());
			env.setVariable("channelContent",  new DefaultObjectWrapper().wrap(channelContent));
			body.render(env.getOut());  
		}
		
	}
	
}