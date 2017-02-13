package com.haoyu.sip.user.template;

import java.io.IOException;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.haoyu.sip.user.entity.UserInfo;
import com.haoyu.sip.user.service.IUserInfoService;

import freemarker.core.Environment;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

@Component
public class UserInfoDirective implements TemplateDirectiveModel {

	@Resource
	private IUserInfoService userInfoService;

	public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
		String id = params.get("id").toString();
		UserInfo userInfo = userInfoService.selectUserInfoFromBaseUserView(id);
		env.setVariable("userInfo", new DefaultObjectWrapper().wrap(userInfo));
		body.render(env.getOut());
	}
	
}
