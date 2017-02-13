package com.haoyu.sip.cms.article.controller;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Maps;
import com.haoyu.sip.cms.article.entity.Article;
import com.haoyu.sip.cms.article.service.IArticleSearchService;
import com.haoyu.sip.cms.article.service.IArticleService;
import com.haoyu.sip.cms.channel.service.IChannelService;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.web.AbstractBaseController;


@Controller("CmsArticleController")
@RequestMapping("/cms_articles")
public class ArticleController  extends AbstractBaseController{
	@Autowired
	private IArticleService articleService;
	
	@Autowired
	private IChannelService channelService;
	


	protected String getLogicalViewNamePrefix(){
		return "/admin/cms/article/";
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String getCreateArticle(String channelId,Model model){
		if(StringUtils.isNotEmpty(channelId)){
			model.addAttribute("channel", channelService.findChannelById(channelId));
		}
		return getLogicalViewNamePrefix()+"create_article";
	}
	
	@RequestMapping(value = "/create/image_article", method = RequestMethod.GET)
	public String getCreateImageArticle(String channelId,Model model){
		if(StringUtils.isNotEmpty(channelId)){
			model.addAttribute("channel", channelService.findChannelById(channelId));
		}
		return getLogicalViewNamePrefix()+"create_image_article";
	}
	
	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public String getEditArticle(@PathVariable String id,String channelId, Model model){
		if(StringUtils.isNotEmpty(channelId)){
			model.addAttribute("channel", channelService.findChannelById(channelId));
		}
		model.addAttribute("article",articleService.findArticleById(id));
		return getLogicalViewNamePrefix()+"edit_article";
		
	}
	
	@RequestMapping(value = "/{id}/edit/image_article", method = RequestMethod.GET)
	public String getEditImageArticle(@PathVariable String id,String channelId, Model model){
		if(StringUtils.isNotEmpty(channelId)){
			model.addAttribute("channel", channelService.findChannelById(channelId));
		}
		model.addAttribute("article",articleService.findArticleById(id));
		return getLogicalViewNamePrefix()+"edit_image_article";
		
	}
	
	
	@RequestMapping(value="/{id}/view",method = RequestMethod.GET)
	public String viewArticle(Article article,Model model){
		model.addAttribute("article",articleService.findArticleById(article.getId()));
		return getLogicalViewNamePrefix()+"view_article";
	}
	
	/**
	 * 保存新创的栏目数据 
	 * @param article
	 * @return
	 */
	@RequestMapping(method=RequestMethod.POST)
	@ResponseBody
	public Response createArticle(Article article){
		return articleService.createArticle(article);
	}
	
	@RequestMapping(value="/index",method=RequestMethod.GET)
	public String articleIndex(){
		return getLogicalViewNamePrefix() + "article_index";
	}
	
	@RequestMapping(method=RequestMethod.GET)
	@ResponseBody
	public List<Article> getArticles(Article article){
		List<Article> articles =  articleService.findArticles(article, getPageBounds(10, true));
		return articles;
	}
	
	@RequestMapping(value="list",method=RequestMethod.GET)
	public String articleList(Article article, Model model){
		setParameterToModel(request,model);
		model.addAttribute("article",article);
		return getLogicalViewNamePrefix()+"list_article";
	}
	
	@RequestMapping(value = "/{id}",method=RequestMethod.PUT)
	@ResponseBody
	public Response updateArticle(@PathVariable String id,Article article){
		article.setId(id);
		return articleService.updateArticle(article);
	}
	
	@RequestMapping(value = "/{id}",method=RequestMethod.GET)
	@ResponseBody
	public Article getArticle(@PathVariable String id){
		return articleService.findArticleById(id);
	}
	
	@RequestMapping(value = "/{id}",method=RequestMethod.DELETE)
	@ResponseBody
	public Response deleteArticle(@PathVariable String id){
		return articleService.deleteArticle(new Article(id));
	}
	
}
