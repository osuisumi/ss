package com.haoyu.sip.sign.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.web.AbstractBaseController;
import com.haoyu.sip.sign.service.ISignUserService;

@Controller
@RequestMapping("**/sign/user")
public class SignUserController extends AbstractBaseController{
	
	@Resource
	private ISignUserService signUserService;
	
	@RequestMapping(method=RequestMethod.POST)
	@ResponseBody
	public Response createSignUser(String relationId){
		return signUserService.createSignUser(relationId);
	}

}
