package com.haoyu.sip.user.dao;

import java.util.List;

import com.haoyu.sip.user.entity.Department;
import com.haoyu.sip.user.entity.UserInfo;

public interface IUserDeptDao {

	int insertUserDepartment(UserInfo user, Department department);

	int updateUserDepartment(UserInfo user, Department department);

	int batchDeleteByAccountIds(List<String> ids);

	int batchDeleteByDepartmentIds(List<String> ids);
	
	int findUserDepartmentByUserId(String id);
}
