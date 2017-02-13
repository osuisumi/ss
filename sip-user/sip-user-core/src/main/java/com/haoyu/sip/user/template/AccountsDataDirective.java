package com.haoyu.sip.user.template;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.google.common.collect.Maps;
import com.haoyu.sip.auth.entity.AuthRole;
import com.haoyu.sip.auth.entity.AuthUser;
import com.haoyu.sip.auth.service.IAuthRoleService;
import com.haoyu.sip.core.freemarker.AbstractTemplateDirectiveModel;
import com.haoyu.sip.core.freemarker.TemplateDirective;
import com.haoyu.sip.user.entity.Account;
import com.haoyu.sip.user.service.IAccountService;
import com.haoyu.sip.utils.Collections3;

import freemarker.core.Environment;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

@Component
@TemplateDirective(directiveName="accountsDirective")
public class AccountsDataDirective extends AbstractTemplateDirectiveModel{

	@Resource
	private IAccountService accountService;
	@Resource
	private IAuthRoleService authRoleService;
	
	@Override
	public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body)
			throws TemplateException, IOException {
		PageBounds pageBounds = getPageBounds(params);
		Map<String,Object> paramerts = getSelectParam(params);
		
		List<Account> accounts = accountService.list(paramerts, pageBounds);
		
		if (Collections3.isNotEmpty(accounts)) {			
			if(paramerts.containsKey("getRole")){
				boolean getRole = (boolean) paramerts.get("getRole");
				if (getRole) {					
					Map<String, List<AuthRole>> userRoleMap = Maps.newHashMap();
					
					for (Account a : accounts) {
						AuthUser authUser = new AuthUser();
						if (a.getUser() != null && StringUtils.isNotEmpty(a.getUser().getId())) {						
							authUser.setId(a.getUser().getId());
							List<AuthRole> authRoles = authRoleService.findRoleByAuthUser(authUser);
							
							if (Collections3.isNotEmpty(authRoles)) {
								userRoleMap.put(a.getUser().getId(), authRoles);
							}
						}
					}
					env.setVariable("userRoleMap", new DefaultObjectWrapper().wrap(userRoleMap));
				}
			}
		}
		
		env.setVariable("accounts", new DefaultObjectWrapper().wrap(accounts));
		
		if (pageBounds != null && pageBounds.isContainsTotalCount()) {
			PageList pageList = (PageList)accounts;
			env.setVariable("paginator" , new DefaultObjectWrapper().wrap(pageList.getPaginator()));
		}
		
		body.render(env.getOut());	
	}

}
