package com.haoyu.sip.cms.magazine.template;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.haoyu.sip.cms.article.entity.Article;
import com.haoyu.sip.cms.article.service.IArticleService;
import com.haoyu.sip.gallery.entity.Photo;
import com.haoyu.sip.gallery.service.IPhotoService;

import freemarker.core.Environment;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.SimpleScalar;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

/**
 * 获取电子杂志内容数据
 * 参数:magazineId 杂志id
 * @author lianghuahuang
 */
@Component
public class MagazineDataDirective implements TemplateDirectiveModel {
	
	@Autowired
	private IPhotoService photoService;

	public void execute(Environment env, @SuppressWarnings("rawtypes") Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		if(params.containsKey("magazineId")){
			TemplateModel paramValue = (TemplateModel)params.get("magazineId");
			String magazineId = ((SimpleScalar)paramValue).getAsString();
			List<Photo> photos = photoService.findPhotoByGallery(magazineId, null);			
			env.setVariable("photos",  new DefaultObjectWrapper().wrap(photos));
			body.render(env.getOut());  
		}
		
	}
	
}