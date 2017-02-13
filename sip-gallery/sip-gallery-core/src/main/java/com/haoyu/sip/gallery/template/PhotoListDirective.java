package com.haoyu.sip.gallery.template;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.google.common.collect.Maps;
import com.haoyu.sip.gallery.entity.Photo;
import com.haoyu.sip.gallery.entity.PhotoGallery;
import com.haoyu.sip.gallery.service.IPhotoGalleryService;
import com.haoyu.sip.gallery.service.IPhotoService;

import freemarker.core.Environment;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.SimpleScalar;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
/**
 * 相片数据列表
 * 参数：relationId 关联id 可以是频道ID，也可以是文章ID等
 * @author lianghuahuang
 *
 */
@Component
public class PhotoListDirective implements TemplateDirectiveModel {
	
	@Resource
	private IPhotoService photoService;

	public void execute(Environment env, @SuppressWarnings("rawtypes") Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		int page = 1;
		int size = 10;
		PageBounds pageBounds = null;
		if(params.containsKey("page")){
			String pageStr = ((SimpleScalar)params.get("page")).getAsString();
			if(StringUtils.isEmpty(pageStr))
				pageStr = "1";
			page = Integer.parseInt(pageStr);
			pageBounds = new PageBounds(page,size);
		}
		if(params.containsKey("size")){
			size = Integer.parseInt(((SimpleScalar)params.get("size")).getAsString());
			pageBounds = new PageBounds(page,size);
		}	
		Map<String,Object> parameter = Maps.newHashMap();
		if(!params.isEmpty()&&params.containsKey("photoGalleryId")){
			String photoGalleryId = ((SimpleScalar)params.get("photoGalleryId")).getAsString();
			List<Photo> photoList = photoService.findPhotoByGallery(photoGalleryId,pageBounds);
			if(photoList!=null&&pageBounds!=null){
				PageList pageList = (PageList)photoList;
				env.setVariable("paginator" , new DefaultObjectWrapper().wrap(pageList.getPaginator()));
			}
			env.setVariable("photoList",  new DefaultObjectWrapper().wrap(photoList));		
		}
		body.render(env.getOut());  
	}
	
}