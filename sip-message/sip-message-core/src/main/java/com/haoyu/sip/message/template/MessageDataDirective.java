package com.haoyu.sip.message.template;

import java.io.IOException;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.haoyu.sip.message.entity.Message;
import com.haoyu.sip.message.service.IMessageService;

import freemarker.core.Environment;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

@Component
public class MessageDataDirective implements TemplateDirectiveModel{
	@Resource
	private IMessageService messageService;

	@Override
	public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
		if(params.containsKey("id")){
			String id = params.get("id").toString();
			Message message = messageService.findMessageById(id);
			env.setVariable("message", new DefaultObjectWrapper().wrap(message));
		}
		body.render(env.getOut());
	}

}
