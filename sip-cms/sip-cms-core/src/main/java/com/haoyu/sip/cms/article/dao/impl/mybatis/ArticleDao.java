/**
 * 
 */
package com.haoyu.sip.cms.article.dao.impl.mybatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.sip.cms.article.dao.IArticleDao;
import com.haoyu.sip.cms.article.entity.Article;
import com.haoyu.sip.core.jdbc.MybatisDao;

/**
 * @author Administrator
 *
 */
@Repository
public class ArticleDao extends MybatisDao implements IArticleDao {

	/* (non-Javadoc)
	 * @see com.haoyu.sip.cms.article.dao.IArticleDao#selectArticleById(java.lang.String)
	 */
	@Override
	public Article selectArticleById(String id) {
		return super.selectByPrimaryKey(id);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.cms.article.dao.IArticleDao#insertArticle(com.haoyu.sip.cms.article.entity.Article)
	 */
	@Override
	public int insertArticle(Article article) {
		article.setDefaultValue();
		super.insert("insertChannelArticle", article);
		return super.insert(article);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.cms.article.dao.IArticleDao#updateArticle(com.haoyu.sip.cms.article.entity.Article)
	 */
	@Override
	public int updateArticle(Article article) {
		article.setUpdateValue();
		if(article.getChannels()!=null&&!article.getChannels().isEmpty()){
			super.delete("deleteCmsChannelArticle", article);
			article.setDefaultValue();
			super.insert("insertChannelArticle", article);
		}
		return super.update(article);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.cms.article.dao.IArticleDao#deleteArticleByLogic(com.haoyu.sip.cms.article.entity.Article)
	 */
	@Override
	public int deleteArticleByLogic(Article article) {
		article.setUpdateValue();
		return super.deleteByLogic(article);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.cms.article.dao.IArticleDao#deleteArticleByPhysics(java.lang.String)
	 */
	@Override
	public int deleteArticleByPhysics(String id) {
		return super.deleteByPhysics(id);
	}


	/* (non-Javadoc)
	 * @see com.haoyu.sip.cms.article.dao.IArticleDao#updateBowerNum(com.haoyu.sip.cms.article.entity.Article)
	 */
	@Override
	public int updateBrowseNum(Article article) {
		return super.update("updateBrowseNum", article);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.cms.article.dao.IArticleDao#findAll(java.util.Map)
	 */
	@Override
	public List<Article> findAll(Map<String, Object> parameter) {
		return super.selectList("selectByParameter", parameter);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.cms.article.dao.IArticleDao#findAll(java.util.Map, com.haoyu.sip.jcr.entity.Pagination)
	 */
	@Override
	public List<Article> findAll(Map<String, Object> parameter,PageBounds pageBounds) {
		return super.selectList("selectByParameter", parameter, pageBounds);
	}

}
