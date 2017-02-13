package com.haoyu.sip.cms.links.template;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.haoyu.sip.cms.links.entity.Links;
import com.haoyu.sip.cms.links.entity.LinksType;
import com.haoyu.sip.cms.links.service.ILinksTypeService;

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
public class LinksDataDirective implements TemplateDirectiveModel {
	
	@Autowired
	private ILinksTypeService linksTypeService;

	public void execute(Environment env, @SuppressWarnings("rawtypes") Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		List<LinksType> linksTypes = linksTypeService.findLinksTypeAndLinks(null);
		env.setVariable("linksTypes",  new DefaultObjectWrapper().wrap(linksTypes));
		body.render(env.getOut());  
	}
	
}