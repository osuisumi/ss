package com.haoyu.sip.category.service.impl;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.haoyu.sip.category.dao.ICategoryRelationDao;
import com.haoyu.sip.category.entity.CategoryRelation;
import com.haoyu.sip.category.service.ICategoryRelationService;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.utils.Identities;

@Service
public class CategoryRelationSercviceImpl implements ICategoryRelationService{
	
	@Resource
	private ICategoryRelationDao categoryRelationDao;

	@Override
	public Response createCategoryRelation(CategoryRelation categoryRelation) {
		if (StringUtils.isEmpty(categoryRelation.getId())) {
			categoryRelation.setId(Identities.uuid2());
		}
		return this.categoryRelationDao.insert(categoryRelation)>0?Response.successInstance():Response.failInstance();
	}

	@Override
	public Response updateCategoryRelation(CategoryRelation categoryRelation) {
		return this.categoryRelationDao.update(categoryRelation)>0?Response.successInstance():Response.failInstance();
	}

	@Override
	public Response deleteCategoryRelationByLogic(CategoryRelation categoryRelation) {
		return this.categoryRelationDao.deleteByLogic(categoryRelation)>0?Response.successInstance():Response.failInstance();
	}
	
	

}
