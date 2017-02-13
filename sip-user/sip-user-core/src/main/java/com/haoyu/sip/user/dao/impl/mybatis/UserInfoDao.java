package com.haoyu.sip.user.dao.impl.mybatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.sip.core.jdbc.MybatisDao;
import com.haoyu.sip.core.utils.ThreadContext;
import com.haoyu.sip.user.dao.IUserInfoDao;
import com.haoyu.sip.user.entity.UserInfo;
import com.haoyu.sip.user.entity.UserBind;

@Repository
public class UserInfoDao extends MybatisDao implements IUserInfoDao {

	@Override
	public int insertUser(UserInfo user) {
		user.setDefaultValue();
		return super.insert(user);
	}

	@Override
	public int updateUser(UserInfo user) {
		user.setUpdateTime(System.currentTimeMillis());
		user.setUpdatedby(ThreadContext.getUser());
		return super.update(user);	
	}

	@Override
	public int batchDeleteByAccountIds(List<String> ids) {
		return super.delete("batchDeleteByAccountIds", ids);
	}

	@Override
	public int batchDeleteByDepartmentIds(List<String> list) {
		return super.delete("batchDeleteByDepartmentIds", list);
	}

	@Override
	public List<UserInfo> select(Map<String, Object> param, PageBounds pageBounds) {
		return super.selectList("selectByParameter",param,pageBounds);
	}

	@Override
	public UserInfo selectUserInfoById(String id) {
		return super.selectByPrimaryKey(id);
	}

	@Override
	public UserInfo selectFormBaseUserView(Map<String,Object> parameter) {
		return super.selectOne("selectFromBaseUserView", parameter);
	}

	@Override
	public Map<String, UserInfo> selectFromBaseUserView(Map<String,Object> parameter) {
		return super.selectMap("selectFromBaseUserView", parameter, "id");
	}

	@Override
	public List<UserInfo> selectFromBaseUserView(Map<String, Object> parameter, PageBounds pageBounds) {
		return super.selectList("selectFromBaseUserView", parameter, pageBounds);
	}

	@Override
	public int countForValidpaperworkNoIsExist(UserInfo userInfo) {
		return super.selectOne("countForValidpaperworkNoIsExist",userInfo);
	}

	@Override
	public int insertUserBind(UserBind userBind) {
		return super.insert("insertUserBind", userBind);
	}

	@Override
	public UserInfo selectUserInfoByBind(UserBind userBind) {
		return selectOne("selectUserInfoByBind", userBind);
	}
	

}
