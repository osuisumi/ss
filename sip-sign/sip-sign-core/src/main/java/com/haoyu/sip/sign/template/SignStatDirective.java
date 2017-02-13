package com.haoyu.sip.sign.template;

import java.io.IOException;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.haoyu.sip.core.freemarker.AbstractTemplateDirectiveModel;
import com.haoyu.sip.core.utils.ThreadContext;
import com.haoyu.sip.sign.entity.SignStat;
import com.haoyu.sip.sign.service.ISignStatService;

import freemarker.core.Environment;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

@Component
public class SignStatDirective extends AbstractTemplateDirectiveModel{

	@Resource
	private ISignStatService signStatService;

	public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
		Map<String,Object> parameter = getSelectParam(params);
		if(parameter.containsKey("relationId")){
			String relationId = params.get("relationId").toString();
			String id = SignStat.getId(relationId, ThreadContext.getUser().getId());
			SignStat signStat = signStatService.getSignStat(id);
			env.setVariable("signStat", new DefaultObjectWrapper().wrap(signStat));
		}
		body.render(env.getOut());
	}

}
