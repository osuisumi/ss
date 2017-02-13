package com.haoyu.sip.cms.template.template;

import java.io.IOException;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import com.haoyu.sip.cms.template.entity.TemplateFile;
import com.haoyu.sip.cms.template.service.ITemplateFileService;

import freemarker.core.Environment;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.SimpleScalar;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

@Component
public class ChannelTemplateDataDirective implements TemplateDirectiveModel {
	
	@Resource
	private ITemplateFileService templateFileService;


	public void execute(Environment env, @SuppressWarnings("rawtypes") Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		if(params.containsKey("id")){
			String alias = ((SimpleScalar)params.get("id")).getAsString();
			if(params.containsKey("type")){
				String type = ((SimpleScalar)params.get("type")).getAsString();
				TemplateFile templateFile = templateFileService.findTemplateFile(type, alias);
				env.setVariable("templateFile",  new DefaultObjectWrapper().wrap(templateFile));
			}
		}
		body.render(env.getOut());  
	}
	
	
}