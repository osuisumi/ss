package com.haoyu.sip.cms.article.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.audit4j.core.annotation.Audit;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.google.common.collect.Maps;
import com.haoyu.sip.cms.article.dao.IArticleDao;
import com.haoyu.sip.cms.article.entity.Article;
import com.haoyu.sip.cms.article.service.IArticleService;
import com.haoyu.sip.core.entity.Relation;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.file.entity.FileInfo;
import com.haoyu.sip.file.service.IFileService;
import com.haoyu.sip.utils.Identities;

@Service
public class ArticleServiceImpl implements 	IArticleService {

	@Resource
	private IArticleDao articleDao;
	
	@Resource
	private IFileService fileService;
	
	@CacheEvict(value="articles",allEntries=true)
	public Response createArticle(Article article) {
		if(article==null){
			return Response.failInstance().responseMsg("createArticle fail!article is null!");
		}
		if(StringUtils.isEmpty(article.getId())){
			article.setId(Identities.uuid2());
		}
		
		if(article.getFrontCoverImageFile()!=null){
			fileService.createFile(article.getFrontCoverImageFile(), article.getId(), "cms-article-image");
			article.setFrontCoverImage(article.getFrontCoverImageFile().getUrl());
		}
		if(article.getFileInfos()!=null&&!article.getFileInfos().isEmpty()){
			fileService.createFileList(article.getFileInfos(), article.getId(), "cms-article");
		}
		if(StringUtils.isEmpty(article.getState())){
			article.setState("published");
		}
		int count = articleDao.insertArticle(article);
		return count>0?Response.successInstance().responseData(article):Response.failInstance().responseMsg("createArticle fail!");
	}
	
	@CacheEvict(value="articles",allEntries=true)
	public Response updateArticle(Article article) {
		if(article==null||StringUtils.isEmpty(article.getId())){
			return Response.failInstance().responseMsg("updateArticle is fail!article is null or article's id is null");
		}
				
		if(article.getFrontCoverImageFile()!=null){
				List<FileInfo> oldImageFileInfos = fileService.listFileInfoByRelation(new Relation(article.getId(),"cms-article-image"));
				//article.getFileInfos().add(article.getFrontCoverImageFile());
				fileService.updateFile(article.getFrontCoverImageFile(), oldImageFileInfos!=null&&oldImageFileInfos.size()>0?oldImageFileInfos.get(0):null, article.getId(), "cms-article-image");
				article.setFrontCoverImage(article.getFrontCoverImageFile().getUrl());				
		}else if(StringUtils.isEmpty(article.getIsTop())){
			List<FileInfo> oldImageFileInfos = fileService.listFileInfoByRelation(new Relation(article.getId(),"cms-article-image"));
			if(oldImageFileInfos!=null&&!oldImageFileInfos.isEmpty()){
				fileService.updateFile(null, oldImageFileInfos.get(0), article.getId(), "cms-article-image");
			}
			article.setFrontCoverImage("");
		}
		List<FileInfo> oldFileInfos = fileService.listFileInfoByRelation(new Relation(article.getId(),"cms-article"));
		fileService.updateFileList(article.getFileInfos(), oldFileInfos, article.getId(), "cms-article");
		int count = articleDao.updateArticle(article);
		return count>0?Response.successInstance().responseData(article):Response.failInstance().responseMsg("updateArticle fail!");
	}
	@CacheEvict(value="articles",allEntries=true)
	public Response deleteArticle(Article article) {
		if(article==null||StringUtils.isEmpty(article.getId())){
			return Response.failInstance().responseMsg("deleteArticle is fail!article is null or article's id is null");
		}
		int count = articleDao.deleteArticleByLogic(article);
		return count>0?Response.successInstance():Response.failInstance().responseMsg("deleteArticle fail!");
	}

	public Article findArticleById(String id) {
		if(StringUtils.isEmpty(id)){
			return null;
		}
		return articleDao.selectArticleById(id);
	}
	@Cacheable(key="'article_'+#id",value="articles")
	public Article findById(String id) {
		if(StringUtils.isEmpty(id)){
			return null;
		}
		return articleDao.selectArticleById(id);
	}

	public void updateBrowseNum(Article article) {
		 articleDao.updateBrowseNum(article);
	}

	public List<Article> findArticles(Map<String, Object> parameter) {
		return articleDao.findAll(parameter);
	}
	//@Cacheable(key="'alias_'+(#parameter['state']?:'')+(#parameter['alias']==null?'':#parameter['alias'])+(#parameter['md5child']==null?'':#parameter['md5child'])+(#pageBounds==null?'':(#pageBounds.page+'_'+#pageBounds.limit))",value="articles")
	@Cacheable(key="'alias_'+#parameter['cacheKey']",value="articles")
	public List<Article> findArticles(Map<String, Object> parameter,
			PageBounds pageBounds) {
		return articleDao.findAll(parameter, pageBounds);
	}

	@Override
	public List<Article> findArticles(Article article, PageBounds pageBounds) {
		Map<String, Object> parameter = Maps.newHashMap();
		if(article!=null){
			if(StringUtils.isNotEmpty(article.getState())){
				parameter.put("state", article.getState());
			}
			if(StringUtils.isNotEmpty(article.getState())){
				parameter.put("isTop", article.getIsTop());
			}
			if(StringUtils.isNotEmpty(article.getTitle())){
				parameter.put("title", article.getTitle());
			}
			if(article.getChannel()!=null&&StringUtils.isNotEmpty(article.getChannel().getId())){
				parameter.put("channelId", article.getChannel().getId());
			}
		}
		return articleDao.findAll(parameter, pageBounds);
	}

}
