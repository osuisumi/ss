package com.haoyu.sip.cms.article.template;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.haoyu.sip.cms.article.entity.Article;
import com.haoyu.sip.cms.article.service.IArticleService;
import com.haoyu.sip.cms.channel.entity.Channel;

import freemarker.core.Environment;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.SimpleScalar;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

/**
 * 通过从articleId 获取 article
 * 参数：articleId 文章id,incrBrowseNum 是否更新浏览数
 * @author lianghuahuang
 */
@Component
public class ArticleDataDirective implements TemplateDirectiveModel {
	
	@Autowired
	private IArticleService articleService;

	public void execute(Environment env, @SuppressWarnings("rawtypes") Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		if(params.containsKey("articleId")){
			TemplateModel paramValue = (TemplateModel)params.get("articleId");
			String id = ((SimpleScalar)paramValue).getAsString();
			Article article = articleService.findArticleById(id);
			if(article!=null){
				TemplateModel incrBrowseNumModel = (TemplateModel)params.get("incrBrowseNum");
				boolean incrBrowseNum = Boolean.valueOf(((SimpleScalar)incrBrowseNumModel).getAsString());
				//更新浏览数
				if(incrBrowseNum){
					Runnable r = ()->{
						try {
							Thread.sleep(1000*5);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						articleService.updateBrowseNum(article);
					};
					new Thread(r).start();
				}
			}else{			
				if(params.containsKey("createIfNotExists")){
					Article newArticle = new Article(id);
					if(params.containsKey("alias")){
						paramValue = (TemplateModel)params.get("alias");
						String alias = ((SimpleScalar)paramValue).getAsString();
						//将alias放入channel的ID字段中存放，用于数据库数据的查询
						newArticle.setChannel(new Channel(alias));					
					}
					articleService.createArticle(newArticle);
					env.setVariable("article",  new DefaultObjectWrapper().wrap(newArticle));
					body.render(env.getOut());  
					return;
				}
				
			}
			env.setVariable("article",  new DefaultObjectWrapper().wrap(article));
			body.render(env.getOut());  
		}
		
	}
	
}