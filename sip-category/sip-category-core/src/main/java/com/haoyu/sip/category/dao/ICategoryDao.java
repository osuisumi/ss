package com.haoyu.sip.category.dao;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.sip.category.entity.Category;

public interface ICategoryDao {

	int insert(Category category);

	int update(Category category);

	int deleteByLogic(Category category);

	Category selectByPrimaryKey(String id);

	List<Category> select(Map<String, Object> param, PageBounds pageBounds);

}
