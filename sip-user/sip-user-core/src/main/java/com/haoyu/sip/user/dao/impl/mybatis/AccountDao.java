package com.haoyu.sip.user.dao.impl.mybatis;

import java.util.List;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.google.common.collect.Maps;
import com.haoyu.sip.core.jdbc.MybatisDao;
import com.haoyu.sip.core.utils.ThreadContext;
import com.haoyu.sip.user.dao.IAccountDao;
import com.haoyu.sip.user.entity.Account;
import com.haoyu.sip.utils.Collections3;

@Repository	
public class AccountDao extends MybatisDao implements IAccountDao {

	@Override
	public List<Account> selectAccountForList(Account account , PageBounds pageBounds) {
		Map<String,Object> param = Maps.newHashMap();
		param.put("account", account);
		if( account.getUser() != null &&  account.getUser().getRealName() != null){
			param.put("realName", account.getUser().getRealName());
		}
		if(account.getUser() != null && account.getUser().getDepartment() != null && account.getUser().getDepartment().getDeptName() != null){
			param.put("deptName",account.getUser().getDepartment().getDeptName());
		}
		if(Collections3.isNotEmpty(account.getRoles()) && account.getRoles().get(0) != null && account.getRoles().get(0).getId() != ""){
			param.put("roleId",account.getRoles().get(0).getId());
		}
		return super.selectList("selectByAccount", param, pageBounds);
	}

	@Override
	public int batchDeleteByIds(List<String> id) {
		return super.delete("batchDeleteByIds", id);
	}

	@Override
	public int insertAccount(Account account) {
		account.setDefaultValue();
		if(StringUtils.isNotEmpty(account.getPassword())){
			account.setPassword(DigestUtils.md5Hex(account.getPassword()));
		}
		return super.insert(account);
	}

	@Override
	public Account selectAccountById(String id) {
		return super.selectByPrimaryKey(id);

	}

	@Override
	public int updateAccount(Account account) {
		account.setUpdateTime(System.currentTimeMillis());
		account.setUpdatedby(ThreadContext.getUser());
		if(StringUtils.isNotEmpty(account.getPassword())){
			account.setPassword(DigestUtils.md5Hex(account.getPassword()));
		}
		return super.update(account);
	}

	@Override
	public int getAccountCountByUsername(String userName) {
		return super.selectOne("getAccountCountByUsername", userName);
	}

	@Override
	public int getAccountCountForValidUserNameIsExist(Account account) {
		return super.selectOne("getAccountCountForValidUserNameIsExist", account);
	}

	@Override
	public int batchDeleteByDepartmentIds(List<String> ids) {
		return super.delete("batchDeleteByDepartmentIds", ids);
	}

	@Override
	public int batchResetPasswordByIds(Map<String, Object> param) {
		if(param.containsKey("password")){
			String password = (String) param.get("password");
			param.put("password", DigestUtils.md5Hex(password));
			return super.update("updateForResetPasswordyIds",param);
		}
		return 0;
	}

	@Override
	public Account selectAccountByUserId(String userId) {
		return super.selectOne("selectByUserId",userId);
	}

	@Override
	public List<Account> findAll(Map<String, Object> param, PageBounds pageBounds) {
		return super.selectList("selectByParameter", param, pageBounds);
	}
}