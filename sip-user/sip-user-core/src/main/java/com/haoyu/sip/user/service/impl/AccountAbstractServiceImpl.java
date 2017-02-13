package com.haoyu.sip.user.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.utils.ThreadContext;
import com.haoyu.sip.user.service.IAccountAbstractService;
import com.haoyu.sip.user.service.IAccountService;

@Service
public class AccountAbstractServiceImpl implements IAccountAbstractService{
	
	@Resource
	private IAccountService accountService;
	
	public Response updatePassword(String oldPassword,String newPassword){
		return accountService.updatePassword(oldPassword,newPassword,ThreadContext.getUser().getId());
	}
	
}
