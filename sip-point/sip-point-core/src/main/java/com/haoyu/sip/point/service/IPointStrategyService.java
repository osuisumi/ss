package com.haoyu.sip.point.service;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.point.entity.PointStrategy;

public interface IPointStrategyService {
	
	Response createPointStrategy(PointStrategy pointStrategy);
	
	Response updatePointStrategy(PointStrategy pointStrategy);
	
	Response deletePointStrategy(PointStrategy pointStrategy);
	
	PointStrategy findPointStrategyById(String id);
	
	List<PointStrategy> findPointStrategys(PointStrategy pointStrategy,PageBounds pageBounds);
	
	List<PointStrategy> findPointStrategys(Map<String,Object> parameter,PageBounds pageBounds);
	
}
