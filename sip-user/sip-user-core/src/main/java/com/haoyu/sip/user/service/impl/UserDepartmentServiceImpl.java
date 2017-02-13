package com.haoyu.sip.user.service.impl;

import java.util.Arrays;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.user.dao.IUserDeptDao;
import com.haoyu.sip.user.entity.Department;
import com.haoyu.sip.user.entity.UserInfo;
import com.haoyu.sip.user.service.IUserDepartmentService;
@Service
public class UserDepartmentServiceImpl implements IUserDepartmentService {

	@Resource
	private IUserDeptDao userDeptDao;
	
	@Override
	public Response createUserDepartment(UserInfo user, Department department) {
		int count = userDeptDao.insertUserDepartment(user,department);
		return count > 0 ? Response.successInstance().responseData(user) : Response.failInstance().responseMsg("createRole fail!");
	}

	@Override
	public Response batchDeleteByAccountIds(String ids) {
		if (StringUtils.isEmpty(ids)) {
			return Response.failInstance().responseMsg("batchDeleteByIds fail! param ids is null");
		}
		String[] idArray = ids.split(",");
		int count = userDeptDao.batchDeleteByAccountIds(Arrays.asList(idArray));
		return count>0?Response.successInstance():Response.failInstance();
	}

	@Override
	public Response updateUserDepartment(UserInfo user, Department department) {
		if (user == null || StringUtils.isEmpty(user.getId()) || department == null || StringUtils.isEmpty(department.getId())) {
			return Response.failInstance().responseMsg("updateUserDepartment is fail!UserDepartment is null or UserDepartment's id is null");
		}
		int count = userDeptDao.updateUserDepartment(user,department);
		return count > 0 ? Response.successInstance().responseData(user) : Response.failInstance().responseMsg("updateUserDepartment fail!");
	}

	@Override
	public Response batchDeleteByDepartmentIds(String ids) {
		if (StringUtils.isEmpty(ids)) {
			return Response.failInstance().responseMsg("batchDeleteByDepartmentIds fail! param ids is null");
		}
		String[] idArray = ids.split(",");
		int count = userDeptDao.batchDeleteByDepartmentIds(Arrays.asList(idArray));
		return count>0?Response.successInstance():Response.failInstance();
	}

	@Override
	public int findUserDepartmentByUserId(String id) {
		return userDeptDao.findUserDepartmentByUserId(id);
	}
}
