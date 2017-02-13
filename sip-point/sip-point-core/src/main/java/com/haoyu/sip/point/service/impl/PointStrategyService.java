package com.haoyu.sip.point.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.google.common.collect.Maps;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.point.dao.IPointStrategyDao;
import com.haoyu.sip.point.entity.PointStrategy;
import com.haoyu.sip.point.service.IPointStrategyService;
@Service
public class PointStrategyService implements IPointStrategyService{	
	@Resource
	private IPointStrategyDao pointStrategyDao;
	
	@Override
	public Response createPointStrategy(PointStrategy pointStrategy) {
		if(StringUtils.isEmpty(pointStrategy.getId())){
			pointStrategy.setId(PointStrategy.getId(pointStrategy.getType(), pointStrategy.getRelationId()));
		}
		pointStrategy.setDefaultValue();
		return this.pointStrategyDao.insertPointStrategy(pointStrategy)>0?Response.successInstance():Response.failInstance();
	}

	@Override
	public Response updatePointStrategy(PointStrategy pointStrategy) {
		pointStrategy.setUpdateValue();
		return this.pointStrategyDao.updatePointStrategy(pointStrategy)>0?Response.successInstance():Response.failInstance();
	}

	@Override
	public Response deletePointStrategy(PointStrategy pointStrategy) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PointStrategy findPointStrategyById(String id) {
		return pointStrategyDao.selectPointStrategyById(id);
	}

	@Override
	public List<PointStrategy> findPointStrategys(PointStrategy pointStrategy, PageBounds pageBounds) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PointStrategy> findPointStrategys(Map<String, Object> parameter, PageBounds pageBounds) {
		return this.pointStrategyDao.findAll(parameter, pageBounds);
	}

}
