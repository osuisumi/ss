/**
 * 
 */
package com.haoyu.sip.cms.article.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.sip.cms.article.dao.IArticleSearchDao;
import com.haoyu.sip.cms.article.entity.Article;
import com.haoyu.sip.cms.article.service.IArticleSearchService;

/**
 * @author lianghuahuang
 *
 */
public class ArticleSearchServiceMysqlImpl implements IArticleSearchService {
	@Resource
	private IArticleSearchDao articleSearchDao;

	@Override
	public List<Article> searchArticles(String keywords, PageBounds pageBounds) {		
		return articleSearchDao.selectArticles(keywords, pageBounds);
	}

	@Override
	public List<Article> searchArticles(String keywords, String channelId,
			PageBounds pageBounds) {
		return articleSearchDao.selectArticles(keywords, channelId, pageBounds);
	}
	

}
