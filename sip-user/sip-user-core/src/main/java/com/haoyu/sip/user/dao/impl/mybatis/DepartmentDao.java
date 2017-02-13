package com.haoyu.sip.user.dao.impl.mybatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.sip.core.jdbc.MybatisDao;
import com.haoyu.sip.core.utils.ThreadContext;
import com.haoyu.sip.user.dao.IDeparmentDao;
import com.haoyu.sip.user.entity.Department;

@Repository
public class DepartmentDao extends MybatisDao implements IDeparmentDao {

	@Override
	public List<Department> selectDeparmentForList(Department department, PageBounds pageBounds) {
		return super.selectList("selectByDepartment", department, pageBounds);
	}

	@Override
	public int insertDepartment(Department authDeparment) {
		authDeparment.setDefaultValue();
		return super.insert(authDeparment);
	}

	@Override
	public Department selectDepartmentById(String id) {
		return super.selectByPrimaryKey(id);
	}

	@Override
	public int updateDeparment(Department department) {
		department.setUpdateTime(System.currentTimeMillis());
		department.setUpdatedby(ThreadContext.getUser());
		return super.update(department);
	}

	@Override
	public int batchDeleteByIds(List<String> id) {
		return super.update("batchDeleteByIds", id);
	}

	@Override
	public int getDepartmentCountForValidDeptNameIsExist(Department department) {
		return super.selectOne("getDepartmentCountForValidDeptNameIsExist", department);
	}

	@Override
	public int getDepartmentCodeForValidDeptCodeIsExist(Department department) {
		return super.selectOne("getDepartmentCodeForValidDeptCodeIsExist", department);
	}

	@Override
	public List<Department> select(Map<String, Object> param, PageBounds pageBounds) {
		return selectList("select", param, pageBounds);
	}

}