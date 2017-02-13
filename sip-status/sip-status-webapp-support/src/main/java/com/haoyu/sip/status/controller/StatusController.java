package com.haoyu.sip.status.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.web.AbstractBaseController;
import com.haoyu.sip.status.entity.Status;
import com.haoyu.sip.status.service.IStatusService;

@Controller
@RequestMapping("**/status")
public class StatusController extends AbstractBaseController{
	
	@Resource
	private IStatusService statusService;
	
	@RequestMapping(method=RequestMethod.PUT)
	@ResponseBody
	public Response update(Status status){
		return statusService.updateStatus(status);
	}

}
