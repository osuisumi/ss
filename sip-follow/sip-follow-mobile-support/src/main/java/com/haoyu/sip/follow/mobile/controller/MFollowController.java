package com.haoyu.sip.follow.mobile.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.web.AbstractBaseMobileController;
import com.haoyu.sip.follow.entity.Follow;
import com.haoyu.sip.follow.mobile.service.IMFollowService;
import com.haoyu.sip.follow.service.IFollowService;

@Controller
@RequestMapping("**/m/follow")
public class MFollowController extends AbstractBaseMobileController{

	@Resource
	private IFollowService followService;
	@Resource
	private IMFollowService followMobileService;
	
	@RequestMapping(method=RequestMethod.POST)
	@ResponseBody
	public Response create(Follow follow){
		return followMobileService.createFollow(follow);
	}
	
	@RequestMapping(value="{id}",method=RequestMethod.DELETE)
	@ResponseBody
	public Response delete(Follow follow){
		return followService.deleteFollow(follow);
	}	
	
}
