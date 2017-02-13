/**
 * 
 */
package com.haoyu.sip.cms.article.service;

import java.util.List;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.sip.cms.article.entity.Article;

/**
 * @author lianghuahuang
 *
 */
public interface IArticleSearchService {
	
	List<Article> searchArticles(String keyWords,PageBounds pageBounds);
	
	List<Article> searchArticles(String keyWords,String channelId,PageBounds pageBounds);
}
