/**
 * 
 */
package com.haoyu.sip.auth.template;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Component;

import com.google.common.collect.Maps;
import com.haoyu.sip.auth.entity.AuthMenu;
import com.haoyu.sip.auth.service.IAuthMenuService;

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
public class AuthMenuTreeDirective implements TemplateDirectiveModel {
	@Resource
	private IAuthMenuService authMenuService;
	/* (non-Javadoc)
	 * @see freemarker.template.TemplateDirectiveModel#execute(freemarker.core.Environment, java.util.Map, freemarker.template.TemplateModel[], freemarker.template.TemplateDirectiveBody)
	 */
	@Override
	public void execute(Environment env, Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		Map<String,Object> parameter = Maps.newHashMap();
		if(params.containsKey("relationId")){
			//关联ID，菜单有可能属于不同的站点可以通过站点ID来充当关联ID实现多站点菜单的统一管理
			TemplateModel paramValue = (TemplateModel)params.get("relationId");
			String relationId = ((SimpleScalar)paramValue).getAsString();
			parameter.put("relationId",relationId);
		}
		if(params.containsKey("userId")){
			//关联ID，菜单有可能属于不同的站点可以通过站点ID来充当关联ID实现多站点菜单的统一管理
			TemplateModel paramValue = (TemplateModel)params.get("userId");
			String userId = ((SimpleScalar)paramValue).getAsString();
			parameter.put("userId",userId);
		}
		List<AuthMenu> authMenus =authMenuService.findMenu(parameter, true);
		env.setVariable("authMenus",  new DefaultObjectWrapper().wrap(authMenus));
		body.render(env.getOut());  
	}

}
