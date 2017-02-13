package com.haoyu.sip.category.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.sip.category.dao.ICategoryDao;
import com.haoyu.sip.category.entity.Category;
import com.haoyu.sip.category.entity.CategoryRelation;
import com.haoyu.sip.category.service.ICategoryRelationService;
import com.haoyu.sip.category.service.ICategoryService;
import com.haoyu.sip.core.entity.Relation;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.utils.ThreadContext;
import com.haoyu.sip.utils.Identities;

@Service
public class CategoryServiceImpl implements ICategoryService {
	
	@Resource
	private ICategoryDao categoryDao;
	@Resource
	private ICategoryRelationService categoryRelationService;
	
	public List<Category> listCategory(Map<String, Object> param,PageBounds pageBounds) {
		return categoryDao.select(param, pageBounds);
	}

	@Override
	public Response createCategory(Category category) {
		if (category == null){
			return Response.failInstance().responseMsg("create category fail,because category is null !");
		}
		if (StringUtils.isEmpty(category.getId())) {
			category.setId(Identities.uuid2());
		}
		int count = this.categoryDao.insert(category);
		if(count > 0){
			if(category.getCategoryRelation() != null ){
				CategoryRelation cr = category.getCategoryRelation();
				cr.setCategory(category);
				categoryRelationService.createCategoryRelation(cr);
			}
		}
		return count>0?Response.successInstance():Response.failInstance();
	}

	public Response updateCategory(Category category) {
		if (category == null||StringUtils.isEmpty(category.getId())){
			return Response.failInstance().responseMsg("update category fail,because category is null or category.id is null!");
		}
		return this.categoryDao.update(category)>0?Response.successInstance():Response.failInstance();
	}

	@Override
	public Response deleteCategoryByLogic(Category category) {
		if (category == null||StringUtils.isEmpty(category.getId())){
			return Response.failInstance().responseMsg("deleteCategoryByLogic category fail,because category is null or category.id is null!");
		}
		return this.categoryDao.deleteByLogic(category)>0?Response.successInstance():Response.failInstance();
	}

	@Override
	public Category findCategoryById(String id) {
		return this.categoryDao.selectByPrimaryKey(id);
	}
	
}
