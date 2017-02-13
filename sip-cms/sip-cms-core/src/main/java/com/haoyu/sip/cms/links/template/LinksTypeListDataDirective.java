package com.haoyu.sip.cms.links.template;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.haoyu.sip.cms.links.entity.Links;


import com.haoyu.sip.cms.links.service.ILinksService;

import freemarker.core.Environment;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
/**
 * 获取友情链接数据
 * 
 * @author lianghuahuang
 *
 */
@Component
public class LinksTypeListDataDirective implements TemplateDirectiveModel {
	
	@Resource
	private ILinksService linksService;

	public void execute(Environment env, @SuppressWarnings("rawtypes") Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		List<Links> links = linksService.findLinks(params);
		env.setVariable("linksList",  new DefaultObjectWrapper().wrap(links));
		body.render(env.getOut());  
	}
	
}