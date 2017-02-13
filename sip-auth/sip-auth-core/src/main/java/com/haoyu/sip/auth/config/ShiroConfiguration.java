package com.haoyu.sip.auth.config;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;



public abstract class ShiroConfiguration {

	private static Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();

	//@Bean(name = "shiroRealm")
	protected abstract AuthorizingRealm getShiroRealm();

	//@Bean(name = "shiroCacheManager")
	protected abstract CacheManager getCacheManager();

	//@Bean(name = "lifecycleBeanPostProcessor")
	protected LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
		return new LifecycleBeanPostProcessor();
	}

	//@Bean
	protected DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {
		DefaultAdvisorAutoProxyCreator daap = new DefaultAdvisorAutoProxyCreator();
		daap.setProxyTargetClass(true);
		return daap;
	}

	//@Bean(name = "securityManager")
	protected DefaultWebSecurityManager getDefaultWebSecurityManager() {
		DefaultWebSecurityManager dwsm = new DefaultWebSecurityManager();
		dwsm.setRealm(getShiroRealm());
	//	dwsm.setCacheManager(getCacheManager());
		return dwsm;
	}

	//@Bean
	protected AuthorizationAttributeSourceAdvisor getAuthorizationAttributeSourceAdvisor() {
		AuthorizationAttributeSourceAdvisor aasa = new AuthorizationAttributeSourceAdvisor();
		aasa.setSecurityManager(getDefaultWebSecurityManager());
		return new AuthorizationAttributeSourceAdvisor();
	}

	//@Bean(name = "shiroFilter")
	protected ShiroFilterFactoryBean getShiroFilterFactoryBean() {
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		shiroFilterFactoryBean.setSecurityManager(getDefaultWebSecurityManager());
		shiroFilterFactoryBean.setLoginUrl("/login");
		shiroFilterFactoryBean.setSuccessUrl("/debates");
		filterChainDefinitionMap.put("/debates/**", "authc");
		filterChainDefinitionMap.put("/**", "anon");
		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
		return shiroFilterFactoryBean;
	}
	
	protected LogoutFilter getLogoutFilter(){
		LogoutFilter logoutFilter = new LogoutFilter();
	//	logoutFilter.setRedirectUrl(redirectUrl);
		return logoutFilter;
	}

}
