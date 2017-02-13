package com.haoyu.sip.sign.template;

import java.io.IOException;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.haoyu.sip.core.freemarker.AbstractTemplateDirectiveModel;
import com.haoyu.sip.sign.service.ISignStatService;

import freemarker.core.Environment;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

@Component
public class SignStatCountDirective extends AbstractTemplateDirectiveModel{

	@Resource
	private ISignStatService signStatService;

	public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
		Map<String,Object> parameter = getSelectParam(params);
		if(parameter.containsKey("relationId")){
			int count = signStatService.getCount(parameter);
			env.setVariable("count", new DefaultObjectWrapper().wrap(count));
		}
		body.render(env.getOut());
	}

}
