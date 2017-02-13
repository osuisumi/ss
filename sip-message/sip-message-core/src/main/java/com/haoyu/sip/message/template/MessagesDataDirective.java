package com.haoyu.sip.message.template;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.haoyu.sip.message.entity.Message;
import com.haoyu.sip.message.service.IMessageService;

import freemarker.core.Environment;
import freemarker.ext.beans.BeanModel;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
@Component
public class MessagesDataDirective implements TemplateDirectiveModel {
	@Resource
	private IMessageService messageService;

	@Override
	public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
		if (params.containsKey("message")) {
			BeanModel beanModel = (BeanModel) params.get("message");
			Message message = (Message) beanModel.getWrappedObject();
			PageBounds pageBounds = null;
			if (params.containsKey("pageBounds")) {
				BeanModel pageBoundsBeanModel = (BeanModel) params.get("pageBounds");
				pageBounds = (PageBounds) pageBoundsBeanModel.getWrappedObject();
			}
			List<Message> messages = messageService.findMessages(message, pageBounds);
			if (pageBounds != null && pageBounds.isContainsTotalCount()) {
				PageList pageList = (PageList) messages;
				env.setVariable("paginator", new DefaultObjectWrapper().wrap(pageList.getPaginator()));
			}
			env.setVariable("messages", new DefaultObjectWrapper().wrap(messages));
		}
		body.render(env.getOut());

	}

}
