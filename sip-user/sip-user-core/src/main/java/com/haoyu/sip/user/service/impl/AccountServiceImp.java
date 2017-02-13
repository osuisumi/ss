	package com.haoyu.sip.user.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.google.common.collect.Maps;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.user.dao.IAccountDao;
import com.haoyu.sip.user.entity.Account;
import com.haoyu.sip.user.entity.UserInfo;
import com.haoyu.sip.user.event.CreateAccountEvent;
import com.haoyu.sip.user.event.UpdateAccountEvent;
import com.haoyu.sip.user.service.IAccountService;
import com.haoyu.sip.user.service.IUserDepartmentService;
import com.haoyu.sip.user.service.IUserInfoService;
import com.haoyu.sip.utils.Identities;

@Service
public class AccountServiceImp implements IAccountService {

	@Resource
	private IAccountDao accountDao;
	@Resource
	private IUserInfoService userService;
	@Resource
	private IUserDepartmentService userDepartmentService;
	@Autowired
	private ApplicationContext applicationContext;

	@Override
	public List<Account> list(Account account, PageBounds pageBounds) {
		return this.accountDao.selectAccountForList(account, pageBounds);
	}
	
	@Override
	public Response createAccount(Account account) {
		if (account == null) {
			return Response.failInstance().responseMsg("createAccount fail!role is null!");
		}
		if (StringUtils.isEmpty(account.getId())) {
			account.setId(Identities.uuid2());
		}
		if (StringUtils.isEmpty(account.getUser().getId())) {
			account.getUser().setId(Identities.uuid2());
		}
		if (StringUtils.isEmpty(account.getRoleCode())) {
			account.setRoleCode("1");
		}
		Response response = userService.createUser(account.getUser());
		if (response.isSuccess() == true) {
			account.setDefaultValue();
			accountDao.insertAccount(account);
			userDepartmentService.createUserDepartment(account.getUser(), account.getUser().getDepartment());
			applicationContext.publishEvent(new CreateAccountEvent(account));
			return Response.successInstance();
		} else {
			return Response.failInstance();
		}
	}

	@Override
	public Response batchDeleteByIds(String ids) {
		if (StringUtils.isEmpty(ids)) {
			return Response.failInstance().responseMsg("batchDeleteAccount fail! param ids is null");
		}
		String[] idArray = ids.split(",");
		Response response = userService.batchDeleteByIds(ids);
		if (response.isSuccess()) {	
			userDepartmentService.batchDeleteByAccountIds(ids);
			int count = accountDao.batchDeleteByIds(Arrays.asList(idArray));
			return count>0?Response.successInstance():Response.failInstance();
		}
		return Response.failInstance();
	}

	@Override
	public Account findAccountById(String id) { 
		return accountDao.selectAccountById(id);
	}

	@Override
	public Response updateAccount(Account account) {
		if (account == null || StringUtils.isEmpty(account.getId())) {
			return Response.failInstance().responseMsg("updateAccount is fail!account is null or account's id is null");
		}
		int count = accountDao.updateAccount(account);
		if(count > 0){
			if(userDepartmentService.findUserDepartmentByUserId(account.getUser().getId()) > 0){
				userDepartmentService.updateUserDepartment(account.getUser(),account.getUser().getDepartment());
			}else{
				userDepartmentService.createUserDepartment(account.getUser(), account.getUser().getDepartment());
			}
			Response response = userService.updateUser(account.getUser());
			if(response.isSuccess()){
				applicationContext.publishEvent(new UpdateAccountEvent(account));
				return Response.successInstance();
			}
		}
		return Response.failInstance();
	}

	@Override
	public int getAccountCountByUsername(String user_name) {
		return accountDao.getAccountCountByUsername(user_name);
	}

	@Override
	public int getAccountCountForValidUserNameIsExist(Account account) {
		return accountDao.getAccountCountForValidUserNameIsExist(account);
	}

	@Override
	public Response batchResetPasswordByIds(Account account) {
		if (StringUtils.isAnyEmpty(account.getId(),account.getPassword())) {
			return Response.failInstance().responseMsg("ResetPassword fail! param id is null or password is null");
		}
		Map<String, Object> param = Maps.newHashMap();
		param.put("ids", Arrays.asList(account.getId()));
		param.put("password",account.getPassword());
		int count = accountDao.batchResetPasswordByIds(param);
		return count>0?Response.successInstance():Response.failInstance();
	}

	@Override
	public Response updatePassword(String oldPassword,String newPassword, String userId) {
		Response response = Response.failInstance();
		if(StringUtils.isEmpty(newPassword)){
			return response.responseMsg("new password is empty");
		}
		
		Account old = accountDao.selectAccountByUserId(userId);
		if(old != null){
			if(!old.getPassword().equals(DigestUtils.md5Hex(oldPassword))){
				return response.responseMsg("error old password");
			}
			Account account = new Account();
			account.setId(old.getId());
			account.setPassword(newPassword);
			account.setUser(new UserInfo(userId));
			response = batchResetPasswordByIds(account);
		}
		return response;
	}

	@Override
	public List<Account> list(Map<String, Object> paramerts, PageBounds pageBounds) {
		return accountDao.findAll(paramerts, pageBounds);
	}

}
		