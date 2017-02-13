/**
 * 
 */
package com.haoyu.sip.echarts.dao;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.sip.echarts.entity.StatCharts;

/**
 * @author lianghuahuang
 *
 */
public interface IStatChartsDao {
	StatCharts selectStatChartsById(String id);
	
	int insertStatCharts(StatCharts resource);
	
	int updateStatCharts(StatCharts resource);
	
	int deleteStatChartsByLogic(StatCharts resource);
	
	int deleteStatChartsByPhysics(String id);

	List<StatCharts> findAll(Map<String, Object> parameter);
	
	List<StatCharts> findAll(Map<String, Object> parameter, PageBounds pageBounds);
}
