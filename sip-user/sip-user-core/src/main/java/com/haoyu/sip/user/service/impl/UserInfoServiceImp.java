package com.haoyu.sip.user.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.google.common.collect.Maps;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.web.SearchParam;
import com.haoyu.sip.user.dao.IUserInfoDao;
import com.haoyu.sip.user.entity.UserInfo;
import com.haoyu.sip.user.entity.UserBind;
import com.haoyu.sip.user.service.IUserDepartmentService;
import com.haoyu.sip.user.service.IUserInfoService;
import com.haoyu.sip.utils.Identities;

@Service
public class UserInfoServiceImp implements IUserInfoService {

	@Resource
	private IUserInfoDao userInfoDao;
	@Resource
	private IUserDepartmentService userDepartmentService;
	
	@Override
	public Response createUser(UserInfo user) {
		if (user == null) {
			return Response.failInstance().responseMsg("createUser fail!User is null!");
		}
		if (StringUtils.isEmpty(user.getId())){
			user.setId(Identities.uuid2());
		}
		user.setDefaultValue();
		int count = userInfoDao.insertUser(user);
		return count > 0 ? Response.successInstance().responseData(user) : Response.failInstance().responseMsg("createRole fail!");
	}

	@Override
	public Response updateUser(UserInfo user) {
		if (user == null || StringUtils.isEmpty(user.getId())) {
			return Response.failInstance().responseMsg("updateUser is fail!User is null or User's id is null");
		}
		int count = userInfoDao.updateUser(user);
		if (count > 0) {
			if(user.getDepartment() != null){
				if(userDepartmentService.findUserDepartmentByUserId(user.getId()) > 0){
					userDepartmentService.updateUserDepartment(user,user.getDepartment());
				}else{
					userDepartmentService.createUserDepartment(user, user.getDepartment());
				}
			}
		}
		return count > 0 ? Response.successInstance().responseData(user) : Response.failInstance().responseMsg("updateUser fail!");
	}

	@Override
	public Response batchDeleteByIds(String ids) {
		if (StringUtils.isEmpty(ids)) {
			return Response.failInstance().responseMsg("batchDeleteUser fail! param ids is null");
		}
		String[] idArray = ids.split(",");
		int count = this.userInfoDao.batchDeleteByAccountIds(Arrays.asList(idArray));
		return count > 0 ? Response.successInstance() : Response.failInstance().responseMsg("batchDeleteByIds fail!");
	}

	@Override
	public List<UserInfo> listUser(Map<String, Object> param, PageBounds pageBounds) {
		return userInfoDao.select(param, pageBounds);
	}

	@Override
	public UserInfo selectUserInfoById(String id) {
		return userInfoDao.selectUserInfoById(id);
	}

	@Override
	public UserInfo selectUserInfoFromBaseUserView(String id) {
		if(StringUtils.isEmpty(id)){
			return null;
		}
		Map<String,Object> parameter = Maps.newHashMap();
		parameter.put("id", id);
		return userInfoDao.selectFormBaseUserView(parameter);
	}

	@Override
	public Map<String, UserInfo> selectUserInfoFromBaseUserView(List<String> ids) {
		if(CollectionUtils.isEmpty(ids)){
			return null;
		}
		Map<String,Object> parameter = Maps.newHashMap();
		parameter.put("ids", ids);
		return userInfoDao.selectFromBaseUserView(parameter);
	}

	@Override
	public List<UserInfo> selectUserInfoFromBaseUserView(Map<String, Object> parameter, PageBounds pageBounds) {
		return userInfoDao.selectFromBaseUserView(parameter, pageBounds);
	}

	@Override
	public List<UserInfo> list(SearchParam searchParam, PageBounds pageBounds) {
		return userInfoDao.select(searchParam.getParamMap(), pageBounds);
	}

	@Override
	public int countForValidpaperworkNoIsExist(UserInfo userInfo) {
		return userInfoDao.countForValidpaperworkNoIsExist(userInfo);
	}

	@Override
	public Response createUserBind(UserBind userBind) {
		if (StringUtils.isEmpty(userBind.getId())){
			userBind.setId(Identities.uuid2());
		}
		userBind.setDefaultValue();
		int count = userInfoDao.insertUserBind(userBind);
		return count>0?Response.successInstance():Response.failInstance();
	}

	@Override
	public UserInfo selectUserInfoByBind(UserBind userBind) {
		return userInfoDao.selectUserInfoByBind(userBind);
	}

}
