package com.haoyu.sip.auth.template;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.haoyu.sip.auth.entity.AuthPermission;
import com.haoyu.sip.auth.entity.AuthResource;
import com.haoyu.sip.auth.service.IAuthPermissionService;

import freemarker.core.Environment;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.SimpleScalar;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
@Component
public class AuthPermissionListDataDirective implements TemplateDirectiveModel {
	
	@Resource
	private IAuthPermissionService authPermissionService;
	
	@Override
	public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body)
			throws TemplateException, IOException {
		int page = 1;
		int size = 10;
		if(params.containsKey("page")){
			String pageStr = ((SimpleScalar)params.get("page")).getAsString();
			if(StringUtils.isEmpty(pageStr))
				pageStr = "1";
			page = Integer.parseInt(pageStr);
		}
		if(params.containsKey("size")){
			size = Integer.parseInt(((SimpleScalar)params.get("size")).getAsString());
		}	
		PageBounds pageBounds = new PageBounds(page,size);
		String relationId=null;
		if (params.containsKey("relationId") && params.get("relationId") != null) {
			TemplateModel paramValue = (TemplateModel)params.get("relationId");
			relationId = ((SimpleScalar)paramValue).getAsString();
		}
		AuthResource authResource = new AuthResource(relationId);
		List<AuthPermission> authPermissions = authPermissionService.findPermissionByResource(authResource, pageBounds);
		if (pageBounds != null) {
			PageList pageList = (PageList)authPermissions;
			env.setVariable("paginator" , new DefaultObjectWrapper().wrap(pageList.getPaginator()));
		}
		env.setVariable("authPermissionList" , new DefaultObjectWrapper().wrap(authPermissions));
		body.render(env.getOut());
	}
}
