package com.haoyu.sip.cms.channel.template;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
public class ChannelAliasDataDirective implements TemplateDirectiveModel {
	
	@Autowired
	private IChannelService channelService;

	public void execute(Environment env, @SuppressWarnings("rawtypes") Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		String alias = "index";
		if(params.containsKey("alias")){
			TemplateModel paramValue = (TemplateModel)params.get("alias");
			alias = ((SimpleScalar)paramValue).getAsString();
		}
		String domain = Objects.toString(ThreadContext.get("domain"));
		if(params.containsKey("domain")){
			TemplateModel paramValue = (TemplateModel)params.get("domain");
			domain = ((SimpleScalar)paramValue).getAsString();
		}
		Channel channel = channelService.findChannelByAlias(alias,domain);		
		env.setVariable("channel",  new DefaultObjectWrapper().wrap(channel));
		body.render(env.getOut());  
	}
	
}