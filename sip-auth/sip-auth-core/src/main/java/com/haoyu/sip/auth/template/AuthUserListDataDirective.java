package com.haoyu.sip.auth.template;

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
import com.haoyu.sip.auth.entity.AuthUser;
import com.haoyu.sip.auth.service.IAuthUserService;

import freemarker.core.Environment;
import freemarker.ext.beans.BeanModel;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.SimpleScalar;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
@Component
public class AuthUserListDataDirective implements TemplateDirectiveModel {
	
	@Resource
	private IAuthUserService authUserService;
	
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
		
		Map<String,Object> parameter = Maps.newHashMap();
		if (params.containsKey("realName") && params.get("realName") != null) {
			TemplateModel paramValue = (TemplateModel)params.get("realName");
			String realName = ((SimpleScalar)paramValue).getAsString();
			parameter.put("realName", realName);
		}
		if (params.containsKey("userName") && params.get("userName") != null) {
			TemplateModel paramValue = (TemplateModel)params.get("userName");
			String userName = ((SimpleScalar)paramValue).getAsString();
			parameter.put("userName", userName);
		}
		
		List<AuthUser> authUsers = authUserService.findAuthUsers(parameter, pageBounds);
		if (pageBounds != null) {
			PageList pageList = (PageList)authUsers;
			env.setVariable("paginator" , new DefaultObjectWrapper().wrap(pageList.getPaginator()));
		}
		env.setVariable("authUserList" , new DefaultObjectWrapper().wrap(authUsers));
		body.render(env.getOut());
	}
}
