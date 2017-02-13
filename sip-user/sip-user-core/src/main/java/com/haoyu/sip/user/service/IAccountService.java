package com.haoyu.sip.user.service;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.user.entity.Account;

public interface IAccountService {
	
	List<Account> list(Account account,PageBounds pageBounds);
	
	Response batchDeleteByIds(String ids);

	Response createAccount(Account account);

	Account findAccountById(String id);
	
	Response updatePassword(String oldPassword,String newPassword,String userId);

	Response updateAccount(Account account);

	int getAccountCountByUsername(String user_name);

	int getAccountCountForValidUserNameIsExist(Account account);

	Response batchResetPasswordByIds(Account account);

	List<Account> list(Map<String, Object> paramerts, PageBounds pageBounds);

}
