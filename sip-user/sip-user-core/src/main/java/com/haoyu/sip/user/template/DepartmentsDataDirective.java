package com.haoyu.sip.user.template;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.github.miemiedev.mybatis.paginator.domain.Order;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.haoyu.sip.user.entity.Department;
import com.haoyu.sip.user.service.IDepartmentService;

import freemarker.core.Environment;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
@Component
public class DepartmentsDataDirective implements TemplateDirectiveModel{
	
	@Resource
	private IDepartmentService departmentService;

	@Override
	public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
		PageBounds pageBounds = null;
		if (params.containsKey("page")  && params.get("page") != null) {
			pageBounds = new PageBounds();
			String page = params.get("page").toString();
			pageBounds.setPage(Integer.valueOf(page));
		}
		if (params.containsKey("limit")  && params.get("limit") != null) {
			String limit = params.get("limit").toString();
			pageBounds.setLimit(Integer.valueOf(limit));
			pageBounds.setContainsTotalCount(true);
		}
		if (params.containsKey("orders")  && params.get("orders") != null) {
			String orders = params.get("orders").toString();
			pageBounds.setOrders(Order.formString(orders));
		}
		
		Department department = new Department();
		if (params.containsKey("deptName") && StringUtils.isNotEmpty(params.get("deptName").toString())) {
			department.setDeptName(params.get("deptName").toString());
		}
		if(params.containsKey("deptType") &&StringUtils.isNotEmpty(params.get("deptType").toString())){
			department.setDeptType(params.get("deptType").toString());
		}
		List<Department> departments = departmentService.list(department, pageBounds);
		env.setVariable("departments" , new DefaultObjectWrapper().wrap(departments));
		if (pageBounds != null && pageBounds.isContainsTotalCount()) {
			PageList pageList = (PageList)departments;
			env.setVariable("paginator" , new DefaultObjectWrapper().wrap(pageList.getPaginator()));
		}
		body.render(env.getOut());
		
	}

}
