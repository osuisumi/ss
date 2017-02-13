/**
 * 
 */
package com.haoyu.sip.echarts.dao;

import com.haoyu.sip.echarts.entity.StatChartsParam;

/**
 * @author lianghuahuang
 *
 */
public interface IStatChartsParamDao {

	StatChartsParam selectStatChartsParamById(String id);
	
	int insertStatChartsParam(StatChartsParam scp);
	
	int updateStatChartsParam(StatChartsParam scp);
}
