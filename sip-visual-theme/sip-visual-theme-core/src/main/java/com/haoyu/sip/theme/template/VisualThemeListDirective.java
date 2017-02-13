/**
 * 
 */
package com.haoyu.sip.theme.template;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.google.common.collect.Maps;
import com.haoyu.sip.theme.entity.VisualTheme;
import com.haoyu.sip.theme.entity.VisualThemeSet;
import com.haoyu.sip.theme.service.IVisualThemeService;
import com.haoyu.sip.theme.service.IVisualThemeSetService;

import freemarker.core.Environment;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.SimpleScalar;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

/**
 * @author lianghuahuang
 *
 */
@Component
public class VisualThemeListDirective implements TemplateDirectiveModel{
	@Resource
	private IVisualThemeService visualThemeService;
	@Override
	public void execute(Environment env, Map params, TemplateModel[] loopVars,
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
		
		if(!params.isEmpty()&&params.containsKey("visualThemeSetId")){
			String visualThemeSetId = ((SimpleScalar)params.get("visualThemeSetId")).getAsString();
			parameter.put("visualThemeSetId", visualThemeSetId)	;		
		}
		
		List<VisualTheme> visualThemes = visualThemeService.findVisualThemes(parameter,pageBounds);
		if(pageBounds != null){
			PageList pageList = (PageList)visualThemes;
			env.setVariable("paginator" , new DefaultObjectWrapper().wrap(pageList.getPaginator()));
		}
		env.setVariable("visualThemeList",  new DefaultObjectWrapper().wrap(visualThemes));
		body.render(env.getOut()); 
	}

}
