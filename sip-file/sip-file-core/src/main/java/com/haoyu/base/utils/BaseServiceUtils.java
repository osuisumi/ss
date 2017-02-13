package com.haoyu.base.utils;

import org.apache.commons.lang3.StringUtils;

import com.haoyu.base.entity.BaseEntity;
import com.haoyu.sip.core.jdbc.MybatisDao;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.utils.ThreadContext;
import com.haoyu.sip.utils.Identities;

public class BaseServiceUtils{

	public static Response create(Object obj, MybatisDao mybatisDao) {
		if (obj instanceof BaseEntity) {
			BaseEntity entity = (BaseEntity)obj;
			if (StringUtils.isEmpty(entity.getId())) {
				entity.setId(Identities.uuid2());
			}
			entity.setDefaultValue();
			int count = mybatisDao.insert(entity);
			return count>0?Response.successInstance():Response.failInstance();
		}
		return Response.failInstance();
	}
	
	public static Response update(Object obj, MybatisDao mybatisDao) {
		if (obj instanceof BaseEntity) {
			BaseEntity entity = (BaseEntity)obj;
			entity.setUpdatedby(ThreadContext.getUser());
			entity.setUpdateTime(System.currentTimeMillis());
			int count = mybatisDao.update(entity);
			return count>0?Response.successInstance():Response.failInstance();
		}
		return Response.failInstance();
	}
	
	public static Response delete(String id, MybatisDao mybatisDao) {
		BaseEntity entity = new BaseEntity();
		entity.setId(id);
		entity.setUpdatedby(ThreadContext.getUser());
		entity.setUpdateTime(System.currentTimeMillis());
		int count = mybatisDao.deleteByLogic(entity);
		return count>0?Response.successInstance():Response.failInstance();
	}
	
	public static BaseEntity get(String id, MybatisDao mybatisDao) {
		return mybatisDao.selectByPrimaryKey(id);
	}
	
}
