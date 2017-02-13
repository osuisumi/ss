package com.haoyu.sip.sign.dao.impl.mybatis;

import org.springframework.stereotype.Repository;

import com.haoyu.sip.core.jdbc.MybatisDao;
import com.haoyu.sip.sign.dao.ISignUserDao;
import com.haoyu.sip.sign.entity.SignUser;

@Repository
public class SignUserDao extends MybatisDao implements ISignUserDao{

	@Override
	public int insert(SignUser signUser) {
		return super.insert(signUser);
	}

}
