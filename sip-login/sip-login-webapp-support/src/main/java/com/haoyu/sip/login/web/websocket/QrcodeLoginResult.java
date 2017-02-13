/**
 * 
 */
package com.haoyu.sip.login.web.websocket;

import java.io.Serializable;

import com.haoyu.sip.login.auth.QrCodeCredential;

/**
 * @author Administrator
 *
 */
public class QrcodeLoginResult implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7979952053349160097L;

	private String state;
	
	private QrCodeCredential credential;
	
	public QrcodeLoginResult(String state) {
		this.state = state;
	}

	public QrcodeLoginResult(String state, QrCodeCredential credential) {
		this.state = state;
		this.credential = credential;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public QrCodeCredential getCredential() {
		return credential;
	}

	public void setCredential(QrCodeCredential credential) {
		this.credential = credential;
	}
}
