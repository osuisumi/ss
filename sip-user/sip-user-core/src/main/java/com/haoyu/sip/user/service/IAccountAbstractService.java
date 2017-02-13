package com.haoyu.sip.user.service;

import com.haoyu.sip.core.service.Response;

public interface IAccountAbstractService {
	
	public Response updatePassword(String oldPassword, String newPassword);

}
