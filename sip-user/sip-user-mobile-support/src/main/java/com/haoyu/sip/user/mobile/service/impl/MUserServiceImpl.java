package com.haoyu.sip.user.mobile.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.utils.BeanUtils;
import com.haoyu.sip.textbook.mobile.entity.MTextBookEntry;
import com.haoyu.sip.textbook.utils.TextBookUtils;
import com.haoyu.sip.user.dao.IUserInfoDao;
import com.haoyu.sip.user.entity.UserInfo;
import com.haoyu.sip.user.mobile.entity.MUser;
import com.haoyu.sip.user.mobile.service.IMUserService;
import com.haoyu.sip.user.service.IUserInfoService;

@Repository
public class MUserServiceImpl implements IMUserService{

	@Resource
	private IUserInfoService userInfoService;
	@Resource
	private IUserInfoDao userInfoDao;
	
	@Override
	public Response getUser(UserInfo userInfo) {
		if (StringUtils.isNotEmpty(userInfo.getId())) {
			MUser mUser = new MUser();
			userInfo = userInfoService.selectUserInfoFromBaseUserView(userInfo.getId());
			BeanUtils.copyProperties(userInfo,mUser);
			
			if (StringUtils.isNotEmpty(userInfo.getStage())) {
				MTextBookEntry mTextBookEntry = new MTextBookEntry();
				mTextBookEntry.setTextBookValue(userInfo.getStage());
				mTextBookEntry.setTextBookName(TextBookUtils.getEntryName("STAGE",userInfo.getStage()));
				mUser.setmStage(mTextBookEntry);
			}
			
			if (StringUtils.isNotEmpty(userInfo.getSubject())) {
				MTextBookEntry mTextBookEntry = new MTextBookEntry();
				mTextBookEntry.setTextBookValue(userInfo.getSubject());
				mTextBookEntry.setTextBookName(TextBookUtils.getEntryName("SUBJECT",userInfo.getSubject()));
				mUser.setmSubject(mTextBookEntry);
			}
			
			return Response.successInstance().responseData(mUser);
		}
		return Response.failInstance();
	}

	@Override
	public Response list(Map<String, Object> parameter, PageBounds pageBounds) {
		Map<String,Object> result = Maps.newHashMap();
		List<MUser> mUsers = Lists.newArrayList();
		List<UserInfo> userInfos = userInfoDao.selectFromBaseUserView(parameter, pageBounds);
		if(CollectionUtils.isNotEmpty(userInfos)){
			for(UserInfo userInfo:userInfos){
				MUser mUser = new MUser();
				BeanUtils.copyProperties(userInfo, mUser);
				mUsers.add(mUser);
			}
		}
		if(userInfos instanceof PageList){
			PageList pageList = (PageList) userInfos;
			result.put("paginator", pageList.getPaginator());
		}
		
		result.put("mUsers", mUsers);
		return Response.successInstance().responseData(result);
	}

}
