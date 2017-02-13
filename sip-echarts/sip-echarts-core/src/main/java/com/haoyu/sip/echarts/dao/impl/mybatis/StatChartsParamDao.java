/**
 * 
 */
package com.haoyu.sip.echarts.dao.impl.mybatis;

import org.springframework.stereotype.Repository;

import com.haoyu.sip.core.jdbc.MybatisDao;
import com.haoyu.sip.echarts.dao.IStatChartsParamDao;
import com.haoyu.sip.echarts.entity.StatChartsParam;

/**
 * @author lianghuahuang
 *
 */
@Repository
public class StatChartsParamDao extends MybatisDao implements IStatChartsParamDao {

	/* (non-Javadoc)
	 * @see com.haoyu.sip.echarts.dao.IStatChartsParamDao#selectStatChartsParamById(java.lang.String)
	 */
	@Override
	public StatChartsParam selectStatChartsParamById(String id) {
		return super.selectByPrimaryKey(id);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.echarts.dao.IStatChartsParamDao#insertStatChartsParam(com.haoyu.sip.echarts.entity.StatChartsParam)
	 */
	@Override
	public int insertStatChartsParam(StatChartsParam scp) {
		return super.insert(scp);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.echarts.dao.IStatChartsParamDao#updateStatChartsParam(com.haoyu.sip.echarts.entity.StatChartsParam)
	 */
	@Override
	public int updateStatChartsParam(StatChartsParam scp) {
		return super.update(scp);
	}

}
