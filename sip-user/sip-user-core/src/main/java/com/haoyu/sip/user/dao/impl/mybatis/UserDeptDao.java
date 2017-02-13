package com.haoyu.sip.user.dao.impl.mybatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.google.common.collect.Maps;
import com.haoyu.sip.core.jdbc.MybatisDao;
import com.haoyu.sip.user.dao.IUserDeptDao;
import com.haoyu.sip.user.entity.Department;
import com.haoyu.sip.user.entity.UserInfo;
import com.haoyu.sip.utils.Identities;

@Repository
public class UserDeptDao extends MybatisDao implements IUserDeptDao {

	public int insertUserDepartment(UserInfo user, Department department) {
		Map<String,Object> param = Maps.newHashMap();
		if(user != null && user.getId() != null && department != null && department.getId() != null ){
			param.put("id", Identities.uuid2());
			param.put("user", user);
			param.put("department",department);
			return super.insert("insertByUserDepartment", param);
		}
		return 0;
	}

	@Override
	public int updateUserDepartment(UserInfo user, Department department) {
		Map<String,Object> param = Maps.newHashMap();
		if(user != null && user.getId() != null && department != null && department.getId() != null ){
			param.put("user", user);
			param.put("department",department);
			return super.insert("updateByUserDepartment", param);
		}
		return 0;
	}

	@Override
	public int batchDeleteByAccountIds(List<String> ids) {
		return super.delete("batchDeleteByAccountIds", ids);
	}

	@Override
	public int batchDeleteByDepartmentIds(List<String> ids) {
		return super.delete("batchDeleteByDepartmentIds", ids);
	}

	@Override
	public int findUserDepartmentByUserId(String id) {
		return super.selectOne("findUserDepartmentByUserId", id);
	}
}
