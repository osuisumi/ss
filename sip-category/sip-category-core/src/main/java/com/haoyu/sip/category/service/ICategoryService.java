package com.haoyu.sip.category.service;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.sip.category.entity.Category;
import com.haoyu.sip.core.service.Response;

public interface ICategoryService {

	List<Category> listCategory(Map<String, Object> param,PageBounds pageBounds);

	Response createCategory(Category obj);

	Response updateCategory(Category obj);
	
	Response deleteCategoryByLogic(Category obj);
	
	Category findCategoryById(String id);
}
