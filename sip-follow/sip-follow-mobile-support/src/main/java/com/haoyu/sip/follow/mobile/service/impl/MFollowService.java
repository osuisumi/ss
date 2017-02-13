package com.haoyu.sip.follow.mobile.service.impl;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.utils.BeanUtils;
import com.haoyu.sip.core.utils.ThreadContext;
import com.haoyu.sip.follow.entity.Follow;
import com.haoyu.sip.follow.mobile.entity.MFollow;
import com.haoyu.sip.follow.mobile.service.IMFollowService;
import com.haoyu.sip.follow.service.IFollowService;

@Service
public class MFollowService implements IMFollowService{

	@Resource
	private IFollowService followService;
	
	@Override
	public Response createFollow(Follow follow) {
		if (ThreadContext.getUser() != null && StringUtils.isNotEmpty(ThreadContext.getUser().getId())) {
			follow.setCreator(ThreadContext.getUser());
			Response response = followService.createFollow(follow);
			
			if (response.isSuccess()) {
				MFollow mFollow = new MFollow();
				if (response.getResponseData() != null) {
					BeanUtils.copyProperties(response.getResponseData(),mFollow);
				}
				return Response.successInstance().responseData(mFollow);
			}
		}
		return Response.failInstance();
	}

}
