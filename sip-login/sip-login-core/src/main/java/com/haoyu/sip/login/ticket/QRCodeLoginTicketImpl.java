/**
 * 
 */
package com.haoyu.sip.login.ticket;



import com.haoyu.sip.login.auth.Authentication;

/**
 * @author Administrator
 *
 */
public class QRCodeLoginTicketImpl extends AbstractTicket implements QRCodeLoginTicket {

	private static final long serialVersionUID = -9015969683047288118L;

	private boolean logged;
	
	private String appToken;
	
	private String username;
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.haoyu.sip.login.ticket.TicketState#getAuthentication()
	 */
	@Override
	public Authentication getAuthentication() {
		return null;
	}
	
	public QRCodeLoginTicketImpl(){}

	public QRCodeLoginTicketImpl(final String id,final ExpirationPolicy expirationPolicy) {
		super(id,expirationPolicy);
	}

	public boolean isLogged() {
		return logged;
	}

	public void setLogged(boolean logged) {
		this.logged = logged;
	}

	@Override
	public String getAppToken() {
		return appToken;
	}

	@Override
	public void setAppToken(String appToken) {
		this.appToken = appToken;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public void setUsername(String username) {
		this.username = username;		
	}


}
