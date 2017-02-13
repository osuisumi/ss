/**
 * 
 */
package com.haoyu.sip.cms.article.dao;

import java.util.List;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.sip.cms.article.entity.Article;

/**
 * @author lianghuahuang
 *
 */
public interface IArticleSearchDao {
	List<Article> selectArticles(String keywords,PageBounds pageBounds);
	
	List<Article> selectArticles(String keywords,String channelId,PageBounds pageBounds);
}
