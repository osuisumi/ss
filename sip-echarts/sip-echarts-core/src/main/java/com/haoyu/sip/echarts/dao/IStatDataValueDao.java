/**
 * 
 */
package com.haoyu.sip.echarts.dao;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.sip.echarts.entity.StatChartsParam;
import com.haoyu.sip.echarts.entity.StatDataValue;

/**
 * @author lianghuahuang
 *
 */
public interface IStatDataValueDao {
	
	
	int insertStatDataValue(StatDataValue statDataValue);
	
	int deleteStatDataValueByPhysics(StatChartsParam scp);
	
	List<StatDataValue> selectStatDataValueByStatChartsParam(StatChartsParam scp);

}
