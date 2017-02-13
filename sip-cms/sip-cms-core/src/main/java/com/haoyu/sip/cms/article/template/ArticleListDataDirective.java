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
@Component
public class ArticleListDataDirective implements TemplateDirectiveModel {
	
	@Resource
	private IArticleService articleService;
	@Resource
	private IChannelService channelService;

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
		}	
		PageBounds pageBounds = new PageBounds(page,size);
		Map<String,Object> parameter = Maps.newHashMap();
		StringBuffer cacheKeyBuffer = new StringBuffer();
		if(params.containsKey("alias")&&params.get("alias")!=null){
			TemplateModel paramValue = (TemplateModel)params.get("alias");
			String alias = ((SimpleScalar)paramValue).getAsString();			
			
			Channel channel = channelService.findChannelByAlias(alias,ThreadContext.getDomain());
			//env.setVariable("channel",  new DefaultObjectWrapper().wrap(channel));
			//获取栏目以及子栏目别名
			if(params.containsKey("getAllChildren")){
				List<Channel> channels = channelService.findChannels(Maps.newHashMap());
				List<String> allChildrenId = Lists.newArrayList(channel.getId());
				setAllChildren(channels,allChildrenId,channel);
				parameter.put("allChildrenId", allChildrenId);
				JsonMapper jm = new JsonMapper();	
				cacheKeyBuffer.append(jm.toJson(allChildrenId));
			}else{
				parameter.put("alias", alias);
				cacheKeyBuffer.append(alias);
			} 
			
		}else if(params.containsKey("channelId")&&params.get("channelId")!=null){
			TemplateModel paramValue = (TemplateModel)params.get("channelId");
			String channelId = ((SimpleScalar)paramValue).getAsString();
			
			Channel channel = channelService.findChannelById(channelId);
			env.setVariable("channel",  new DefaultObjectWrapper().wrap(channel));
			//获取栏目以及子栏目别名
			if(params.containsKey("getAllChildren")){
				List<Channel> channels = channelService.findChannels(Maps.newHashMap());
				List<String> allChildrenId = Lists.newArrayList(channel.getId());
				setAllChildren(channels,allChildrenId,channel);
				parameter.put("allChildrenId", allChildrenId);
				JsonMapper jm = new JsonMapper();	
				cacheKeyBuffer.append(jm.toJson(allChildrenId));
			}else{
				parameter.put("channelId", channelId);
				cacheKeyBuffer.append(channelId);
			} 
			
		}
		
		if(params.containsKey("state")&&params.get("state")!=null){
			TemplateModel paramValue = (TemplateModel)params.get("state");
			String state = ((SimpleScalar)paramValue).getAsString();
			parameter.put("state", state);
			cacheKeyBuffer.append(state);
		}
		
		if(params.containsKey("title")&&params.get("title")!=null){
			TemplateModel paramValue = (TemplateModel)params.get("title");
			String title = ((SimpleScalar)paramValue).getAsString();
			parameter.put("title", title);
			cacheKeyBuffer.append(title);
		}
		if(params.containsKey("isTop")&&params.get("isTop")!=null){
			TemplateModel paramValue = (TemplateModel)params.get("isTop");
			String isTop = ((SimpleScalar)paramValue).getAsString();
			parameter.put("isTop", isTop);
			cacheKeyBuffer.append(isTop);
		}
		parameter.put("cacheKey", DigestUtils.md5Hex(cacheKeyBuffer.toString()+pageBounds.getLimit()+pageBounds.getPage()));
		List<Article> articleList = articleService.findArticles(parameter, pageBounds);
		PageList pageList = (PageList)articleList;
		env.setVariable("articleList",  new DefaultObjectWrapper().wrap(articleList));
		env.setVariable("paginator" , new DefaultObjectWrapper().wrap(pageList.getPaginator()));
		body.render(env.getOut());  
	}
	
	/**
	 * @param channels
	 * @param channel
	 * @return
	 */
	private void setAllChildren(List<Channel> channels,List<String> allChildrenIds,Channel channel){
		if(channel!=null){
			for(Channel node:channels){
				if(node.getParent()!=null&&node.getParent().getId().equals(channel.getId())){
					channel.addChild(node);
					allChildrenIds.add(node.getId());
					setAllChildren(channels,allChildrenIds,node);
				}
			}
		}
	}
	
}