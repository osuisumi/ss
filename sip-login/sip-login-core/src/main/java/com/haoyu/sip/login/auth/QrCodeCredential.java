/**
 * 
 */
package com.haoyu.sip.login.auth;


/**
 * @author lianghuahuang
 *
 */
public class QrCodeCredential extends UsernamePasswordCredential {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6994741815071715320L;

	/**
	 * 移动端App登录后服务器分配的token
	 */
	private String appToken;
	
	private String username;
	public QrCodeCredential(){}
	
	public QrCodeCredential(String appToken, String username) {
		this.appToken = appToken;
		this.username = username;
	}
	/* (non-Javadoc)
	 * @see com.haoyu.sip.login.auth.Credential#getId()
	 */
	@Override
	public String getId() {
		return this.username;
	}
	
	public String getAppToken(){
		return this.appToken;
	}
	
	public String getUsername(){
		return this.username;
	}

}
