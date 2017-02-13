package com.haoyu.sip.user.dao;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.sip.user.entity.Department;


public interface IDeparmentDao {
	
	List<Department> selectDeparmentForList(Department department,PageBounds pageBounds);

	int insertDepartment(Department authDeparment);

	Department selectDepartmentById(String id);

	int updateDeparment(Department department);

	//根据id集合批量物理删除
	int batchDeleteByIds(List<String> asList);

	int getDepartmentCountForValidDeptNameIsExist(Department department);

	int getDepartmentCodeForValidDeptCodeIsExist(Department department);

	List<Department> select(Map<String, Object> param, PageBounds pageBounds);


}
	