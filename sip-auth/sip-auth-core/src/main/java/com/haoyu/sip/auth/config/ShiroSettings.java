/**
 * 
 */
package com.haoyu.sip.auth.config;

import org.apache.shiro.cas.CasFilter;
import org.apache.shiro.cas.CasRealm;
import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.apache.shiro.web.servlet.ShiroFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;


/**
 * @author lianghuahuang
 *
 */
//@ConfigurationProperties(value = "shiro")
public class ShiroSettings {
	
	private CasRealm casRealm;
	
	private LogoutFilter logoutFilter;
	
	private ShiroFilter shiroFilter;
	
	private CasFilter casFilter;

	public CasRealm getCasRealm() {
		return casRealm;
	}

	public void setCasRealm(CasRealm casRealm) {
		this.casRealm = casRealm;
	}

	public LogoutFilter getLogoutFilter() {
		return logoutFilter;
	}

	public void setLogoutFilter(LogoutFilter logoutFilter) {
		this.logoutFilter = logoutFilter;
	}

	public ShiroFilter getShiroFilter() {
		return shiroFilter;
	}

	public void setShiroFilter(ShiroFilter shiroFilter) {
		this.shiroFilter = shiroFilter;
	}

	public CasFilter getCasFilter() {
		return casFilter;
	}

	public void setCasFilter(CasFilter casFilter) {
		this.casFilter = casFilter;
	}
	
	
	
}
