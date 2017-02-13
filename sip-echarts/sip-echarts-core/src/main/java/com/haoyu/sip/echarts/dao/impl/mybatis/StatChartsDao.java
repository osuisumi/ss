/**
 * 
 */
package com.haoyu.sip.echarts.dao.impl.mybatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.sip.echarts.dao.IStatChartsDao;
import com.haoyu.sip.echarts.entity.StatCharts;
import com.haoyu.sip.core.jdbc.MybatisDao;

/**
 * @author lianghuahuang
 *
 */
@Repository
public class StatChartsDao extends MybatisDao implements IStatChartsDao {

	/* (non-Javadoc)
	 * @see com.haoyu.sip.echarts.dao.IStatChartsDao#selectStatChartsById(java.lang.String)
	 */
	@Override
	public StatCharts selectStatChartsById(String id) {
		return super.selectByPrimaryKey(id);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.echarts.dao.IStatChartsDao#insertStatCharts(com.haoyu.sip.echarts.entity.StatCharts)
	 */
	@Override
	public int insertStatCharts(StatCharts statCharts) {
		statCharts.setDefaultValue();
		return super.insert(statCharts);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.echarts.dao.IStatChartsDao#updateStatCharts(com.haoyu.sip.echarts.entity.StatCharts)
	 */
	@Override
	public int updateStatCharts(StatCharts statCharts) {
		statCharts.setUpdateValue();
		return super.update(statCharts);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.echarts.dao.IStatChartsDao#deleteStatChartsByLogic(com.haoyu.sip.echarts.entity.StatCharts)
	 */
	@Override
	public int deleteStatChartsByLogic(StatCharts statCharts) {
		statCharts.setUpdateValue();
		return super.deleteByLogic(statCharts);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.echarts.dao.IStatChartsDao#deleteStatChartsByPhysics(java.lang.String)
	 */
	@Override
	public int deleteStatChartsByPhysics(String id) {
		return super.deleteByPhysics(id);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.echarts.dao.IStatChartsDao#findAll(java.util.Map)
	 */
	@Override
	public List<StatCharts> findAll(Map<String, Object> parameter) {
		return super.selectList("selectByParameter", parameter);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.echarts.dao.IStatChartsDao#findAll(java.util.Map, com.github.miemiedev.mybatis.paginator.domain.PageBounds)
	 */
	@Override
	public List<StatCharts> findAll(Map<String, Object> parameter,
			PageBounds pageBounds) {
		return super.selectList("selectByParameter", parameter,pageBounds);
	}

}
