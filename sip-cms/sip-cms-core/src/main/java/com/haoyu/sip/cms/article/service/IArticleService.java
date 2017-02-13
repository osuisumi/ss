package com.haoyu.sip.cms.article.service;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.sip.cms.article.entity.Article;
import com.haoyu.sip.core.service.Response;

public interface IArticleService {
	Response createArticle(Article article);
	
	Response updateArticle(Article article);
	
	void updateBrowseNum(Article article);
	
	Response deleteArticle(Article article);
	
	Article findArticleById(String id);
	
	List<Article> findArticles(Article article,PageBounds pageBounds);
	
	List<Article> findArticles(Map<String,Object> parameter);
	
	List<Article> findArticles(Map<String,Object> parameter,PageBounds pageBounds);

}
