package com.haoyu.sip.cms.siteinfo.template;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.google.common.collect.Maps;
import com.haoyu.sip.cms.article.entity.Article;
import com.haoyu.sip.cms.article.service.IArticleService;
import com.haoyu.sip.cms.siteinfo.entity.SiteInfo;
import com.haoyu.sip.cms.siteinfo.service.ISiteInfoService;
import com.haoyu.sip.core.utils.PropertiesLoader;

import freemarker.core.Environment;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.SimpleScalar;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

/**
 * 获取站点信息
 */
@Component
public class SiteInfoListDataDirective implements TemplateDirectiveModel {
	
	@Autowired
	private ISiteInfoService siteInfoService;
	
	@Resource
	private PropertiesLoader propertiesLoader;

	public void execute(Environment env, @SuppressWarnings("rawtypes") Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		Map<String,Object> parameter = Maps.newHashMap();
		if(params.containsKey("domain")){
			TemplateModel paramValue = (TemplateModel)params.get("domain");
			parameter.put("domain", ((SimpleScalar)paramValue).getAsString());
		}
		
		int page = 1;
		int size = 10;
		PageBounds pageBounds = null;
		if(params.containsKey("page")){
			String pageStr = ((SimpleScalar)params.get("page")).getAsString();
			if(StringUtils.isEmpty(pageStr))
				pageStr = "1";
			page = Integer.parseInt(pageStr);
			pageBounds = new PageBounds(page,size);
		}
		if(params.containsKey("size")){
			size = Integer.parseInt(((SimpleScalar)params.get("size")).getAsString());
			pageBounds = new PageBounds(page,size);
		}

		List<SiteInfo> siteInfos = siteInfoService.findSiteInfos(parameter,pageBounds);
		if(pageBounds != null){
			PageList pageList = (PageList)siteInfos;
			env.setVariable("paginator" , new DefaultObjectWrapper().wrap(pageList.getPaginator()));
		}
		env.setVariable("siteInfoList",  new DefaultObjectWrapper().wrap(siteInfos));
		body.render(env.getOut());  
		
	}
	
}