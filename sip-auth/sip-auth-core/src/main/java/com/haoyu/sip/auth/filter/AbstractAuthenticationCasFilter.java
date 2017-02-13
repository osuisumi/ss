/**
 * 
 */
package com.haoyu.sip.auth.filter;
import java.util.Objects;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.cas.CasFilter;
import org.apache.shiro.subject.Subject;

/**
 * @author Administrator
 * 
 */
public abstract class AbstractAuthenticationCasFilter extends CasFilter {

	@Override
	protected boolean onLoginSuccess(AuthenticationToken token,
			Subject subject, ServletRequest request, ServletResponse response)
			throws Exception {
			String userName = Objects.toString(subject.getPrincipal());
				if(StringUtils.isNotEmpty(userName)){
				UsernamePasswordToken upt = new UsernamePasswordToken();
				upt.setUsername(userName);
				onLoginSuccess(upt, request, response);
			}
		return super.onLoginSuccess(token, subject, request, response);
	}

	protected abstract void onLoginSuccess(UsernamePasswordToken upt,
			ServletRequest request, ServletResponse response) ;

}
