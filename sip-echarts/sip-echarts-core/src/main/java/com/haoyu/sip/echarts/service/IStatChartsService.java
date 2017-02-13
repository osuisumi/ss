/**
 * 
 */
package com.haoyu.sip.echarts.service;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.sip.echarts.entity.StatCharts;
import com.haoyu.sip.core.service.Response;

/**
 * @author lianghuahuang
 *
 */
public interface IStatChartsService {
	
	Response createStatCharts(StatCharts statCharts);
	
	Response updateStatCharts(StatCharts statCharts);
	
	Response deleteStatCharts(StatCharts statCharts);
	
	StatCharts findStatChartsById(String id);
	
	List<StatCharts> findStatCharts(StatCharts statCharts);
	
	List<StatCharts> findStatCharts(StatCharts statCharts,PageBounds pageBounds);
	
	List<StatCharts> findStatCharts(Map<String,Object> parameter,PageBounds pageBounds);
}
