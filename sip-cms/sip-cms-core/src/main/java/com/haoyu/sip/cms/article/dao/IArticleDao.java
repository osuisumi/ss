package com.haoyu.sip.cms.article.dao;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.sip.cms.article.entity.Article;

public interface IArticleDao {
    /** 查 */
	Article selectArticleById(String id);
	/** 增 */
	int insertArticle(Article article);
	/** 改 */
	int updateArticle(Article article);
	/** 删  逻辑上 */
	int deleteArticleByLogic(Article article);
	/** 删  物理上 */
	int deleteArticleByPhysics(String id);
    /** 查  条件 */
	int updateBrowseNum(Article article);

	List<Article> findAll(Map<String, Object> parameter);
	/** 查  条件  分页 */
	List<Article> findAll(Map<String, Object> parameter, PageBounds pageBounds);
}
