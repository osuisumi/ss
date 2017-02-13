package com.haoyu.sip.auth.realm;

import java.util.List;

import javax.annotation.Resource;

import com.haoyu.sip.auth.dao.IAuthUserDao;
import com.haoyu.sip.auth.entity.AuthUser;
import com.haoyu.sip.auth.realm.AuthenticationRealm;

public class DefaultAuthRealm extends AuthenticationRealm {

	@Resource
	private IAuthUserDao authUserDao;

	@Override
	public List<AuthUser> findAuthUserByIds(List<String> ids) {
		return authUserDao.selectAuthUserByIds(ids);
	}


	@Override
	protected AuthUser findAuthUserByUsername(String username) {
		return authUserDao.selectAuthUserByUsername(username);
	}

	@Override
	protected AuthUser findAuthUserById(String id) {
		return authUserDao.selectAuthUserById(id);
	}

}
