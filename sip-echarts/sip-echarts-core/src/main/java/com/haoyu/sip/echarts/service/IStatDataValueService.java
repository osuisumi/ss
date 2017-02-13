/**
 * 
 */
package com.haoyu.sip.echarts.service;

import java.util.List;
import java.util.Map;

import com.google.common.collect.Table;
import com.haoyu.sip.echarts.entity.StatChartsParam;
import com.haoyu.sip.echarts.entity.StatDataValue;

/**
 * @author lianghuahuang
 *
 */
public interface IStatDataValueService {
	
	List<StatDataValue> findStatDataValues(StatChartsParam statChartsParam);
	
	Table<String,String,Float> findStatDataValueTable(StatChartsParam statChartsParam);
	
	Map<String,Float> findStatDataValueMap(StatChartsParam statChartsParam);
}
