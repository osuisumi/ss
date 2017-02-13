package com.haoyu.sip.point.dao;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.sip.point.entity.PointStrategy;

public interface IPointStrategyDao {
	
	PointStrategy selectPointStrategyById(String id);
	
	int insertPointStrategy(PointStrategy pointStrategy);

	int updatePointStrategy(PointStrategy pointStrategy);

	int deletePointStrategyByLogic(PointStrategy pointStrategy);

	int deletePointStrategyByPhysics(String id);

	List<PointStrategy> findAll(Map<String, Object> parameter, PageBounds pageBounds);

}
