/**
 * 
 */
package com.haoyu.sip.auth.filter;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;

import com.haoyu.sip.core.entity.User;
import com.haoyu.sip.core.utils.ThreadContext;

/**
 * @author Administrator
 *
 */
public class CurrentUserFilter extends AuthorizationFilter {
	
	/* (non-Javadoc)
	 * @see org.apache.shiro.web.filter.AccessControlFilter#isAccessAllowed(javax.servlet.ServletRequest, javax.servlet.ServletResponse, java.lang.Object)
	 */
	@Override
	protected boolean isAccessAllowed(ServletRequest request,
			ServletResponse response, Object obj) throws Exception {
		User user = getCurrentUser(request);
		ThreadContext.bind(user);
		return true;
	}
	
	/**
	 * 获取当前登录用户的信息，可重写封装更多用户信息
	 * @param request
	 * @return
	 */
	protected  User getCurrentUser(ServletRequest request){
		Subject subject = SecurityUtils.getSubject();
		if(subject!=null){
			Map<String, Object> attributes = getPrincipalAttributes(subject.getPrincipals());
			if(attributes!=null){
				User user = new User();
				user.setId(Objects.toString(attributes.get(getUserIdAttributeKey())));
				user.setRealName(Objects.toString(attributes.get("realName")));
				return user;
			}
		}
		return null;
	}
	
	protected String getUserId(Map<String, Object> attributes) {
		return Objects.toString(attributes.get(getUserIdAttributeKey()));
	}

	protected Map<String, Object> getPrincipalAttributes(
			PrincipalCollection principals) {
		if (principals != null) {
			List<Object> listPrincipals = principals.asList();
			if (listPrincipals != null & listPrincipals.size() > 1) {
				Map<String, Object> attributes = (Map<String, Object>) listPrincipals.get(1);
				return attributes;
			}
		}
		return null;
	}
	
	/**
	 * 用户id在principals中所对应的Key，可重写
	 * @return
	 */
	protected String getUserIdAttributeKey() {
		return "id";
	}
}
