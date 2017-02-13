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
import com.haoyu.sip.gallery.entity.PhotoGallery;
import com.haoyu.sip.gallery.service.IPhotoGalleryService;

import freemarker.core.Environment;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.SimpleScalar;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
/**
 * 相册数据列表
 * 参数：relationId 关联id 可以是频道ID，也可以是文章ID等
 * @author lianghuahuang
 *
 */
@Component
public class PhotoGalleryListDirective implements TemplateDirectiveModel {
	
	@Resource
	private IPhotoGalleryService photoGalleryService;

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
		if(!params.isEmpty()&&params.containsKey("relationId")){
			String relationId = ((SimpleScalar)params.get("relationId")).getAsString();
			parameter.put("relationId", relationId)	;		
		}
		List<PhotoGallery> photoGalleryList = photoGalleryService.findPhotoGalleries(parameter,pageBounds);
		if(photoGalleryList!=null&&pageBounds!=null){
			PageList pageList = (PageList)photoGalleryList;
			env.setVariable("paginator" , new DefaultObjectWrapper().wrap(pageList.getPaginator()));
		}
		env.setVariable("photoGalleryList",  new DefaultObjectWrapper().wrap(photoGalleryList));
		body.render(env.getOut());  
	}
	
}