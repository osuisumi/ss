package com.haoyu.sip.user.dao;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.sip.user.entity.Account;

public interface IAccountDao {
	
	int insertAccount(Account account);

	//根据id集合批量物理删除
	int batchDeleteByIds(List<String> asList);

	List<Account> selectAccountForList(Account account, PageBounds pageBounds);

	Account selectAccountById(String id);
	
	Account selectAccountByUserId(String userId);

	int updateAccount(Account account);

	int getAccountCountByUsername(String user_name);

	int getAccountCountForValidUserNameIsExist(Account account);

	int batchDeleteByDepartmentIds(List<String> ids);

	int batchResetPasswordByIds(Map<String, Object> param);

	List<Account> findAll(Map<String, Object> paramerts, PageBounds pageBounds);
}
	