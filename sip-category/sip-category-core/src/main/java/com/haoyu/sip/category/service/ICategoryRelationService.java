package com.haoyu.sip.category.service;

import com.haoyu.sip.category.entity.CategoryRelation;
import com.haoyu.sip.core.service.Response;

public interface ICategoryRelationService {

	Response createCategoryRelation(CategoryRelation obj);

	Response updateCategoryRelation(CategoryRelation obj);
	
	Response deleteCategoryRelationByLogic(CategoryRelation obj);

}
