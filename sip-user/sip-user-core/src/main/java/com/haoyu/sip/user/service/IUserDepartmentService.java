package com.haoyu.sip.user.service;

import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.user.entity.Department;
import com.haoyu.sip.user.entity.UserInfo;

public interface IUserDepartmentService {
	
	Response createUserDepartment(UserInfo u,Department d);

	Response batchDeleteByAccountIds(String ids);
	
	Response batchDeleteByDepartmentIds(String ids);
	
	Response updateUserDepartment(UserInfo user, Department department);
	
	int findUserDepartmentByUserId(String id);
}
