package com.haoyu.sip.user.dao;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.sip.user.entity.UserInfo;
import com.haoyu.sip.user.entity.UserBind;

public interface IUserInfoDao {
	
	int insertUser(UserInfo u);

	int updateUser(UserInfo user);

	int batchDeleteByAccountIds(List<String> ids);

	int batchDeleteByDepartmentIds(List<String> list);

	List<UserInfo> select(Map<String, Object> param, PageBounds pageBounds);

	UserInfo selectUserInfoById(String id);
	
	UserInfo selectFormBaseUserView(Map<String,Object> parameter);
	
	Map<String,UserInfo> selectFromBaseUserView(Map<String,Object> parameter);
	
	List<UserInfo> selectFromBaseUserView(Map<String,Object> parameter,PageBounds pageBounds);
	
	int countForValidpaperworkNoIsExist(UserInfo userInfo);

	int insertUserBind(UserBind userBind);

	UserInfo selectUserInfoByBind(UserBind userBind);
}
