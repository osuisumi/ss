package com.haoyu.sip.attitude.mobile.service.impl;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.haoyu.sip.attitude.dao.IAttitudeUserDao;
import com.haoyu.sip.attitude.entity.AttitudeUser;
import com.haoyu.sip.attitude.mobile.entity.MAttitudeUser;
import com.haoyu.sip.attitude.mobile.service.IMAttitudeService;
import com.haoyu.sip.attitude.service.IAttitudeService;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.utils.BeanUtils;
import com.haoyu.sip.core.utils.ThreadContext;

@Service
public class MAttitudeService implements IMAttitudeService{

	@Resource
	private IAttitudeService attitudeService;
	@Resource
	private IAttitudeUserDao attitudeUserDao;
	@Resource  
	private ApplicationContext applicationContext;
	
	@Override
	public Response createAttitudeUser(AttitudeUser attitudeUser) {
		if (ThreadContext.getUser() != null && StringUtils.isNotEmpty(ThreadContext.getUser().getId())) {
			attitudeUser.setCreator(ThreadContext.getUser());
			Response response = attitudeService.createAttitudeUser(attitudeUser);
			
			if (response.isSuccess()) {
				MAttitudeUser mAttitudeUser = new MAttitudeUser();
				if (response.getResponseData() != null) {
					BeanUtils.copyProperties(response.getResponseData(),mAttitudeUser);
				}
				return Response.successInstance().responseData(mAttitudeUser);
			}

			return response;
		}
		return Response.failInstance();
	}

}
