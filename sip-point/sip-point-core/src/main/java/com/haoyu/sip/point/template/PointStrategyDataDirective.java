package com.haoyu.sip.point.template;

import java.io.IOException;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.haoyu.sip.core.freemarker.AbstractTemplateDirectiveModel;
import com.haoyu.sip.point.entity.PointStrategy;
import com.haoyu.sip.point.service.IPointStrategyService;

import freemarker.core.Environment;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

@Component
public class PointStrategyDataDirective extends AbstractTemplateDirectiveModel{
	@Resource
	private IPointStrategyService pointStrategyService;

	@Override
	public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
		String id = getId(params);
		Map<String,Object> parameter = getSelectParam(params);
		if(StringUtils.isNotEmpty(id)){
			env.setVariable("pointStrategy", new DefaultObjectWrapper().wrap(pointStrategyService.findPointStrategyById(id)));
		}else if(parameter.containsKey("type")&&parameter.containsKey("relationId")){
			id = PointStrategy.getId(parameter.get("type").toString(), parameter.get("relationId").toString());
			env.setVariable("pointStrategy", new DefaultObjectWrapper().wrap(pointStrategyService.findPointStrategyById(id)));
		}
		body.render(env.getOut());
	}

}
