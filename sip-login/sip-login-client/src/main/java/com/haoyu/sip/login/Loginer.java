/**
 * 
 */
package com.haoyu.sip.login;

import java.io.Serializable;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.jasig.cas.client.authentication.AttributePrincipal;


/**
 * @author lianghuahuang
 *
 */
public class Loginer implements Serializable {
	private String id;
	
	private String userName;
	
	private String realName;
	
	private Map<String,Object> attributes;
	
	public static final String DEFAULT_REAL_NAME_KEY="realName";
	
	public static final String DEFAULT_ID_KEY="id";
	
	public Loginer(){}
	
	public Loginer(AttributePrincipal ap){
		if(ap!=null){
			this.userName = ap.getName();
			this.attributes = ap.getAttributes();
			if(attributes!=null){
				this.realName  =String.valueOf(attributes.get(DEFAULT_REAL_NAME_KEY));
				this.id = String.valueOf(attributes.get(DEFAULT_ID_KEY));
			}
		}
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public Map<String, Object> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<String, Object> attributes) {
		this.attributes = attributes;
	}
	
	
	/**
	 * 获取当前登录用户的信息
	 * @param request
	 * @return
	 */
	public static final Loginer getLoginer(HttpServletRequest request){
		if(request!=null){
			return (Loginer)request.getSession().getAttribute("loginer");
		}
		return null;
	}
	
	/**
	 * 设置当前登录用户的信息
	 * @param request
	 * @param loginer
	 */
	public static void setLoginer(HttpServletRequest request,Loginer loginer){
		if(request!=null){
			request.getSession().setAttribute("loginer", loginer);
		}
	}
	
}
