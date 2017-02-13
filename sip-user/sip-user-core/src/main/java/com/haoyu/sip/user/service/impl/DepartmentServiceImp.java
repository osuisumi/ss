package com.haoyu.sip.user.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.user.dao.IDeparmentDao;
import com.haoyu.sip.user.entity.Department;
import com.haoyu.sip.user.service.IDepartmentService;
import com.haoyu.sip.user.service.IUserDepartmentService;
import com.haoyu.sip.utils.Identities;

@Service
public class DepartmentServiceImp implements IDepartmentService {

	@Resource
	private IDeparmentDao deparmentDao;
	@Resource
	private IUserDepartmentService userDepartmentService;
	
	@Override
	public List<Department> list(Department department, PageBounds pageBounds) {
		return this.deparmentDao.selectDeparmentForList(department, pageBounds);
	}

	@Override
	public Response createDepartment(Department authDeparment) {
		if (authDeparment == null) {
			return Response.failInstance().responseMsg("createDepartment fail!deparment is null!");
		}
		if (StringUtils.isEmpty(authDeparment.getId())) {
			authDeparment.setId(Identities.uuid2());
		}
		int count = deparmentDao.insertDepartment(authDeparment);
		return count > 0 ? Response.successInstance().responseData(authDeparment) : Response.failInstance().responseMsg("createDepartment fail!");
	}

	@Override
	public Department findDepartmentById(String id) {
		return deparmentDao.selectDepartmentById(id);
	}

	@Override
	public Response updateDepartment(Department department) {
		if (department == null || StringUtils.isEmpty(department.getId())) {
			return Response.failInstance().responseMsg("updateDepartment is fail!department is null or department's id is null");
		}
		int count = deparmentDao.updateDeparment(department);
		return count > 0 ? Response.successInstance().responseData(department) : Response.failInstance().responseMsg("updatedepartment fail!");
	}

	@Override
	public Response batchDeleteByIds(String ids) {
		if (StringUtils.isEmpty(ids)) {
			return Response.failInstance().responseMsg("batchDeleteDepartment fail! param ids is null");
		}
		String[] idArray = ids.split(",");
		int count = this.deparmentDao.batchDeleteByIds(Arrays.asList(idArray));
		if (count > 0) {
			userDepartmentService.batchDeleteByDepartmentIds(ids);
			return Response.successInstance();
		} else {
			return Response.failInstance();
		}
	}

	@Override
	public List<Department> listAllDepartment() {
		return this.list(new Department(), null);
	}

	@Override
	public int validDeptNameIsExist(Department department) {
		return this.deparmentDao.getDepartmentCountForValidDeptNameIsExist(department);
	}

	@Override
	public int validDeptCodeIsExist(Department department) {
		return this.deparmentDao.getDepartmentCodeForValidDeptCodeIsExist(department);
	}

	@Override
	public List<Department> list(Map<String, Object> param, PageBounds pageBounds) {
		return this.deparmentDao.select(param, pageBounds);
	}
}
		