package com.haoyu.sip.user.mobile.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.web.AbstractBaseMobileController;
import com.haoyu.sip.core.web.SearchParam;
import com.haoyu.sip.user.entity.UserInfo;
import com.haoyu.sip.user.mobile.service.IMUserService;

@RestController
@RequestMapping("**/m/user")
public class MUserController extends AbstractBaseMobileController{

	@Resource
	private IMUserService mUserService;
	
	@RequestMapping(value="{id}",method=RequestMethod.GET)
	public Response get(UserInfo userInfo){
		return mUserService.getUser(userInfo);
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public Response list(SearchParam searchParam){
		PageBounds pageBounds = getPageBounds(10, true);
		return mUserService.list(searchParam.getParamMap(), pageBounds);
	}

}
