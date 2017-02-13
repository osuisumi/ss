package com.haoyu.sip.cms.teacher.template;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.google.common.collect.Maps;
import com.haoyu.sip.cms.teacher.entity.Teacher;
import com.haoyu.sip.cms.teacher.service.ITeacherService;

import freemarker.core.Environment;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.SimpleScalar;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
/**
 * freemarker .ftl文件中所使用的标签处理程序
 * 用于从后台获取相对应的数据
 * 参数有
 * page 第几页 1 缺省为 1
 * rp 行数  9	缺省为10
 * catalogId 查找所有在该栏目下的文章  不可少
 *
 */
@Component
public class TeacherListDataDirective implements TemplateDirectiveModel {
	
	@Resource
	private ITeacherService teacherService;

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
		if(params.containsKey("fullName")){
			String fullName = ((SimpleScalar)params.get("fullName")).getAsString();
			parameter.put("fullName", fullName);
		}
		List<Teacher> teacherList =teacherService.findTeachers(parameter, pageBounds);
		PageList pageList = (PageList)teacherList;
		env.setVariable("teacherList",  new DefaultObjectWrapper().wrap(teacherList));
		env.setVariable("paginator" , new DefaultObjectWrapper().wrap(pageList.getPaginator()));
		body.render(env.getOut());  
	}
	
}