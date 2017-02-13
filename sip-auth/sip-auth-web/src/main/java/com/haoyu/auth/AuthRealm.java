package com.haoyu.auth;

import java.util.List;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.subject.PrincipalCollection;

import com.haoyu.sip.auth.entity.AuthUser;
import com.haoyu.sip.auth.realm.AuthenticationRealm;

public class AuthRealm extends AuthenticationRealm {

	@Override
	public List<AuthUser> findAuthUserByIds(List<String> ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void addAuthorize(SimpleAuthorizationInfo info, PrincipalCollection principals) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected AuthUser findAuthUserByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected AuthUser findAuthUserById(String id) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
