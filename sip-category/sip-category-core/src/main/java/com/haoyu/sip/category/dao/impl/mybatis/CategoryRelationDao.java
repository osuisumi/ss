package com.haoyu.sip.category.dao.impl.mybatis;

import org.springframework.stereotype.Repository;

import com.haoyu.sip.category.dao.ICategoryRelationDao;
import com.haoyu.sip.category.entity.CategoryRelation;
import com.haoyu.sip.core.jdbc.MybatisDao;
import com.haoyu.sip.core.utils.ThreadContext;

@Repository
public class CategoryRelationDao extends MybatisDao implements ICategoryRelationDao{

	@Override
	public int insert(CategoryRelation categoryRelation) {
		categoryRelation.setDefaultValue();
		return super.insert(categoryRelation);
	}

	@Override
	public int update(CategoryRelation categoryRelation) {
		categoryRelation.setUpdateTime(System.currentTimeMillis());
		categoryRelation.setUpdatedby(ThreadContext.getUser());
		return super.update(categoryRelation);
	}

	@Override
	public int deleteByLogic(CategoryRelation categoryRelation) {
		categoryRelation.setUpdateTime(System.currentTimeMillis());
		categoryRelation.setUpdatedby(ThreadContext.getUser());
		return super.deleteByLogic(categoryRelation);
	}

}
