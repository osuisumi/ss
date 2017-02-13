package com.haoyu.sip.category.dao.impl.mybatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.sip.category.dao.ICategoryDao;
import com.haoyu.sip.category.entity.Category;
import com.haoyu.sip.core.jdbc.MybatisDao;
import com.haoyu.sip.core.utils.ThreadContext;

@Repository
public class CategoryDao extends MybatisDao implements ICategoryDao{
	
	@Override
	public int insert(Category category) {
		category.setDefaultValue();
		return super.insert(category);
	}

	@Override
	public int update(Category category) {
		category.setUpdateTime(System.currentTimeMillis());
		category.setUpdatedby(ThreadContext.getUser());
		return super.update(category);
	}

	@Override
	public int deleteByLogic(Category category) {
		category.setUpdateTime(System.currentTimeMillis());
		category.setUpdatedby(ThreadContext.getUser());
		return super.deleteByLogic(category);
	}

	@Override
	public Category selectByPrimaryKey(String id) {
		return super.selectByPrimaryKey(id);
	}

	@Override
	public List<Category> select(Map<String, Object> param,PageBounds pageBounds) {
		return super.selectList("select", param, pageBounds);
	}
}
