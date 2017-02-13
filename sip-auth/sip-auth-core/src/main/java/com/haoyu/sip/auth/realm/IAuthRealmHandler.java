/**
 * 
 */
package com.haoyu.sip.auth.realm;

import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * @author lianghuahuang
 *
 */
public interface IAuthRealmHandler {
	
	void addAuthorize(SimpleAuthorizationInfo info,
			PrincipalCollection principals);
}
