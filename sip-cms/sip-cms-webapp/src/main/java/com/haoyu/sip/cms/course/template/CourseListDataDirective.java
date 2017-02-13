package com.haoyu.sip.cms.course.template;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.haoyu.sip.cms.course.dao.ICourseDao;
import com.haoyu.sip.cms.course.entity.Course;

import freemarker.core.Environment;
import freemarker.ext.beans.BeanModel;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.SimpleScalar;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

@Component
public class CourseListDataDirective implements TemplateDirectiveModel{
	@Resource
	private ICourseDao courseRelationDao;
	@Override
	public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
		Map<String,Object> parameter = Maps.newHashMap();
		List<Course> result = Lists.newArrayList();
		PageBounds pageBounds = null;
		if(params.containsKey("subject")&&params.get("subject")!=null){
			parameter.put("subject", params.get("subject").toString());			
		}
		if(params.containsKey("stage")&&params.get("stage")!=null){
			parameter.put("stage", params.get("stage").toString());			
		}
		if(params.containsKey("courseName")&&params.get("courseName")!=null){
			parameter.put("courseName", params.get("courseName").toString());			
		}
		int page = 1;
		int size = 12;
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
		result = courseRelationDao.selectCourses(parameter,pageBounds);
		if(pageBounds != null){
			PageList pageList = (PageList)result;
			env.setVariable("paginator" , new DefaultObjectWrapper().wrap(pageList.getPaginator()));
		}
		env.setVariable("courses" , new DefaultObjectWrapper().wrap(result));
		body.render(env.getOut());
		
	}
	


}
