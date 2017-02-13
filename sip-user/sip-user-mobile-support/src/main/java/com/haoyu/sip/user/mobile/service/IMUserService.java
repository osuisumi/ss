package com.haoyu.sip.user.mobile.service;

import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.user.entity.UserInfo;

public interface IMUserService {

	Response getUser(UserInfo userInfo);
	
	Response list(Map<String,Object> parameter,PageBounds pageBounds);

}
