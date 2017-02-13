package com.haoyu.sip.category.dao;

import com.haoyu.sip.category.entity.CategoryRelation;

public interface ICategoryRelationDao {

	int insert(CategoryRelation categoryRelation);

	int update(CategoryRelation categoryRelation);

	int deleteByLogic(CategoryRelation categoryRelation);

}
