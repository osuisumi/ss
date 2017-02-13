/**
 * 
 */
package com.haoyu.sip.login.auth;

import javax.security.auth.login.FailedLoginException;

/**
 * @author Administrator
 *
 */
public class IncorrectPasswordException extends FailedLoginException {

	private static final long serialVersionUID = -1104393545494201299L;

	public IncorrectPasswordException() {
	}

	public IncorrectPasswordException(final String msg) {
		super(msg);
	}
}
