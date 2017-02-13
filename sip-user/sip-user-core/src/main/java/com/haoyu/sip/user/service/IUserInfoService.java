package com.haoyu.sip.user.service;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.web.SearchParam;
import com.haoyu.sip.user.entity.UserInfo;
import com.haoyu.sip.user.entity.UserBind;

public interface IUserInfoService {
	
	Response createUser(UserInfo u);

	Response updateUser(UserInfo user);

	Response batchDeleteByIds(String ids);

	List<UserInfo> listUser(Map<String, Object> param, PageBounds pageBounds);

	UserInfo selectUserInfoById(String id);
	
	UserInfo selectUserInfoFromBaseUserView(String id);
	
	Map<String,UserInfo> selectUserInfoFromBaseUserView(List<String> ids);
	
	List<UserInfo> selectUserInfoFromBaseUserView(Map<String,Object> parameter,PageBounds pageBounds);

	List<UserInfo> list(SearchParam searchParam, PageBounds pageBounds);
	
	int countForValidpaperworkNoIsExist(UserInfo userInfo);

	Response createUserBind(UserBind userBind);

	UserInfo selectUserInfoByBind(UserBind userBind);
}
