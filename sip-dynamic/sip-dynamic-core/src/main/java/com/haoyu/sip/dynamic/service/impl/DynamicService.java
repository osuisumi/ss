package com.haoyu.sip.dynamic.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.sip.dynamic.dao.IDynamicDao;
import com.haoyu.sip.dynamic.entity.Dynamic;
import com.haoyu.sip.dynamic.event.CreateDynamicEvent;
import com.haoyu.sip.dynamic.service.IDynamicService;
import com.haoyu.base.utils.BaseServiceUtils;
import com.haoyu.sip.core.jdbc.MybatisDao;
import com.haoyu.sip.core.service.Response;

@Service
public class DynamicService implements IDynamicService {

	@Resource
	private IDynamicDao dynamicDao;
	
	@Resource  
	private ApplicationContext applicationContext;  

	public Response create(Dynamic obj) {
		Response response =  BaseServiceUtils.create(obj, (MybatisDao) this.dynamicDao);
		if(response.isSuccess()){
			applicationContext.publishEvent(new CreateDynamicEvent(obj));
		}
		return response;
	}

	public Response update(Dynamic obj) {
		return BaseServiceUtils.update(obj, (MybatisDao) this.dynamicDao);
	}

	public Response delete(Dynamic dynamic) {
		return BaseServiceUtils.delete(dynamic.getId(), (MybatisDao) this.dynamicDao);
	}

	public Dynamic get(String id) {
		return ((Dynamic) BaseServiceUtils.get(id, (MybatisDao) this.dynamicDao));
	}

	public List<Dynamic> list(Dynamic dynamic, PageBounds pageBounds) {
		return ((MybatisDao) this.dynamicDao).selectList("select",dynamic, pageBounds);
	}

	/** hqy*/
	public List<Dynamic> selectDynamic(Map<String,Object> paramMap, PageBounds paramPageBounds) {
		return ((MybatisDao) this.dynamicDao).selectList("selectDynamic",paramMap, paramPageBounds);
	}

	@Override
	public Response update(Map<String, Object> parameter) {
		return dynamicDao.update(parameter)>0?Response.successInstance():Response.failInstance();
	}



}
