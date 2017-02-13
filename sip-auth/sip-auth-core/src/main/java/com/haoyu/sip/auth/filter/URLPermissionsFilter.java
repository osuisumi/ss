/**
 * 
 */
package com.haoyu.sip.auth.filter;

import java.io.IOException;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.web.filter.authz.PermissionsAuthorizationFilter;

/**
 * @author Administrator
 *
 */
public class URLPermissionsFilter extends PermissionsAuthorizationFilter {

	@Override
	public boolean isAccessAllowed(ServletRequest request,
			ServletResponse response, Object mappedValue) throws IOException {
		return super.isAccessAllowed(request, response, buildPermissions(request));
	}
	
	protected String[] buildPermissions(ServletRequest request) {
		HttpServletRequest req = (HttpServletRequest) request;
		String method = req.getMethod().toUpperCase();
		String[] perms = new String[1];
		
		/*String regex = "/(.*?)/(.*?)\\.(.*)";
		if(url.matches(regex)){
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(url);
			String controller =  matcher.group(1);
			String action = matcher.group(2);
			
		}*/
		return null;
	}

}
