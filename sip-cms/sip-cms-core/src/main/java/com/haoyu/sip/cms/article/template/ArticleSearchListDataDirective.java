package com.haoyu.sip.cms.article.template;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.haoyu.sip.cms.article.entity.Article;
import com.haoyu.sip.cms.article.service.IArticleSearchService;
import com.haoyu.sip.cms.article.service.IArticleService;
import com.haoyu.sip.cms.channel.entity.Channel;
import com.haoyu.sip.cms.channel.service.IChannelService;
import com.haoyu.sip.core.mapper.JsonMapper;
import com.haoyu.sip.core.utils.ThreadContext;

import freemarker.core.Environment;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.SimpleScalar;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

/**
 * 获取文章列表数据
 * 参数：page页码，size 条数,alias 频道别名
 * @author lianghuahuang
 *
 */
public class ArticleSearchListDataDirective implements TemplateDirectiveModel {
	
	@Resource
	private IArticleSearchService articleSearchService;

	public void execute(Environment env, @SuppressWarnings("rawtypes") Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		int page = 1;
		int size = 10;
		if(params.containsKey("page")){
			String pageStr = ((SimpleScalar)params.get("page")).getAsString();
			if(StringUtils.isEmpty(pageStr))
				pageStr = "1";
			page = Integer.parseInt(pageStr);
		}
		if(params.containsKey("size")){
			size = Integer.parseInt(((SimpleScalar)params.get("size")).getAsString());
			if(size>50){
				size=50;
			}
		}	
		PageBounds pageBounds = new PageBounds(page,size);
		Map<String,Object> parameter = Maps.newHashMap();
		String channelId=null;
		if(params.containsKey("channelId")&&params.get("channelId")!=null){
			TemplateModel paramValue = (TemplateModel)params.get("channelId");
			channelId = ((SimpleScalar)paramValue).getAsString();
		}
		
		String keyword = "";
		if(params.containsKey("keyword")&&params.get("keyword")!=null){
			TemplateModel paramValue = (TemplateModel)params.get("keyword");
			keyword = ((SimpleScalar)paramValue).getAsString();
		}
		List<Article> articleList = articleSearchService.searchArticles(keyword, pageBounds);
		PageList pageList = (PageList)articleList;
		env.setVariable("articleList",  new DefaultObjectWrapper().wrap(articleList));
		env.setVariable("paginator" , new DefaultObjectWrapper().wrap(pageList.getPaginator()));
		body.render(env.getOut());  
	}

	
}