package com.haoyu.sip.echarts.service;

import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.echarts.entity.StatChartsParam;

public interface IStatChartsParamService {
	
	StatChartsParam findStatChartsParamById(String id);
	
	StatChartsParam findStatChartsParamByChartsPram(String statChartsId,String requestParam);
	
	Response updateStatChartsParam(StatChartsParam statChartsParam);
	
	Response  insertStatChartsParam(StatChartsParam statChartsParam);
}
