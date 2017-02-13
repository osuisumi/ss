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
public class CourseDataDirective implements TemplateDirectiveModel{
	@Resource
	private ICourseDao courseRelationDao;
	@Override
	public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
		if(params.containsKey("courseRelationId")&&params.get("courseRelationId")!=null){
			Course course = courseRelationDao.selectCourseByCRid(params.get("courseRelationId").toString());	
			env.setVariable("course" , new DefaultObjectWrapper().wrap(course));
		}
		body.render(env.getOut());
		
	}
	


}
