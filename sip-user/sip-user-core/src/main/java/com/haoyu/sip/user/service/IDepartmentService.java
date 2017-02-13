package com.haoyu.sip.user.service;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.user.entity.Department;

public interface IDepartmentService {
	
	Response createDepartment(Department authDeparment);

	List<Department> list(Department authDeparment,PageBounds pageBounds);
	
	List<Department> list(Map<String, Object> param,PageBounds pageBounds);

	Department findDepartmentById(String id);

	Response updateDepartment(Department department);

	Response batchDeleteByIds(String ids);

	List<Department> listAllDepartment();

	int validDeptNameIsExist(Department department);

	int validDeptCodeIsExist(Department department);

}
