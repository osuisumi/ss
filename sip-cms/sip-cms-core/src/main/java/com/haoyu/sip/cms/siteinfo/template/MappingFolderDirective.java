/**
 * 
 */
package com.haoyu.sip.cms.siteinfo.template;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.collect.Maps;
import com.haoyu.sip.cms.siteinfo.entity.SiteInfo;
import com.haoyu.sip.cms.siteinfo.service.ISiteInfoService;
import com.haoyu.sip.core.utils.ThreadContext;

import freemarker.core.Environment;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.SimpleScalar;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

/**
 * @author lianghuahuang
 *
 */
public class MappingFolderDirective implements TemplateDirectiveModel {

	@Resource
	private ISiteInfoService siteInfoService;
	/* (non-Javadoc)
	 * @see freemarker.template.TemplateDirectiveModel#execute(freemarker.core.Environment, java.util.Map, freemarker.template.TemplateModel[], freemarker.template.TemplateDirectiveBody)
	 */
	@Override
	public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body)
			throws TemplateException, IOException {
		String domain=ThreadContext.getDomain();
		if(params.containsKey("domain")){
			TemplateModel paramValue = (TemplateModel)params.get("domain");
			domain = ((SimpleScalar)paramValue).getAsString();
		}
		String mappingFolder = siteInfoService.findMappingFolderByDomain(domain);
		env.setVariable("mappingFolder",  new DefaultObjectWrapper().wrap(Objects.toString(mappingFolder,"")));
		body.render(env.getOut());  

	}

}
