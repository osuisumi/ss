package com.haoyu.sip.attitude.mobile.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.haoyu.sip.attitude.entity.AttitudeUser;
import com.haoyu.sip.attitude.mobile.service.IMAttitudeService;
import com.haoyu.sip.attitude.service.IAttitudeService;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.utils.ThreadContext;
import com.haoyu.sip.core.web.AbstractBaseMobileController;

@RestController
@RequestMapping("**/m/attitude")
public class MAttitudeController extends AbstractBaseMobileController {

	@Resource
	private IAttitudeService attitudeService;
	@Resource
	private IMAttitudeService attitudeMobileService;
	
	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public Response create(AttitudeUser attitudeUser) {
		return attitudeMobileService.createAttitudeUser(attitudeUser);
	}

	@RequestMapping(method = RequestMethod.DELETE)
	@ResponseBody
	public Response delete(String relationId,String attitude) {
		return attitudeService.deleteAttitideUser(attitude, relationId, ThreadContext.getUser().getId());
	}

}