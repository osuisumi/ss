/**
 * 
 */
package com.haoyu.sip.cms.article.dao.impl.mybatis;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.google.common.collect.Maps;
import com.haoyu.sip.cms.article.dao.IArticleSearchDao;
import com.haoyu.sip.cms.article.entity.Article;
import com.haoyu.sip.core.jdbc.MybatisDao;

/**
 * @author lianghuahuang
 *
 */
@Repository
public class ArticleSearchDao extends MybatisDao implements IArticleSearchDao {

	/* (non-Javadoc)
	 * @see com.haoyu.sip.cms.article.dao.IArticleSearchDao#selectArticles(java.lang.String, com.github.miemiedev.mybatis.paginator.domain.PageBounds)
	 */
	@Override
	public List<Article> selectArticles(String keyword, PageBounds pageBounds) {		
		return this.selectArticles(keyword, null, pageBounds);
	}

	@Override
	public List<Article> selectArticles(String keyword, String channelId,
			PageBounds pageBounds) {
		Map<String,Object> parameter = Maps.newHashMap();
		if(StringUtils.isNotEmpty(keyword)){
			String[] keywordArray = keyword.trim().split(" ");
			StringBuffer sb = new StringBuffer("'");
			for(String kw:keywordArray){
				if(StringUtils.isNotEmpty(kw)){
					sb.append("*").append(kw).append("* ");
				}
			}
			sb.append("'");
			parameter.put("keywordExpr", sb.toString());
		}
		if(StringUtils.isNotEmpty(channelId)){
			parameter.put("channelId", channelId);
		}
		return super.selectList("selectByParameter", parameter, pageBounds);
	}

}
