package com.haoyu.sip.cms.siteinfo.template;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.collect.Maps;
import com.haoyu.sip.cms.article.entity.Article;
import com.haoyu.sip.cms.article.service.IArticleService;
import com.haoyu.sip.cms.siteinfo.entity.SiteInfo;
import com.haoyu.sip.cms.siteinfo.service.ISiteInfoService;
import com.haoyu.sip.core.utils.PropertiesLoader;
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
 */
@Component
public class SiteInfoDataDirective implements TemplateDirectiveModel {
	
	@Autowired
	private ISiteInfoService siteInfoService;
	
	@Resource
	private PropertiesLoader propertiesLoader;

	public void execute(Environment env, @SuppressWarnings("rawtypes") Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		String domain=ThreadContext.getDomain();
		if(params.containsKey("domain")){
			TemplateModel paramValue = (TemplateModel)params.get("domain");
			domain = ((SimpleScalar)paramValue).getAsString();
		}

		Map<String,Object> parameter = Maps.newHashMap();
		parameter.put("domain", domain);
		List<SiteInfo> siteInfos = siteInfoService.findSiteInfos(parameter);
		if(siteInfos!=null&&!siteInfos.isEmpty()){
			env.setVariable("siteInfo",  new DefaultObjectWrapper().wrap(siteInfos.get(0)));
			body.render(env.getOut());  
		}
		
	}
	
}