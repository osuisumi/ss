/**
 * 
 */
package com.haoyu.sip.login.ticket;



/**
 * @author Administrator
 *
 */
public interface QRCodeLoginTicket extends Ticket {
	String PREFIX = "QRCLT";
	
	String getAppToken();
	
	String getUsername();
	
	void setUsername(String username);
	
	void setAppToken(String appToken);
	
	boolean isLogged();
	
	void setLogged(boolean logged);
}
