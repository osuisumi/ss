/**
 * 
 */
package com.haoyu.sip.echarts.dao.impl.mybatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.sip.echarts.dao.IStatDataValueDao;
import com.haoyu.sip.echarts.entity.StatChartsParam;
import com.haoyu.sip.echarts.entity.StatDataValue;
import com.haoyu.sip.core.jdbc.MybatisDao;

/**
 * @author lianghuahuang
 *
 */
@Repository
public class StatDataValueDao extends MybatisDao implements IStatDataValueDao {


	/* (non-Javadoc)
	 * @see com.haoyu.sip.echarts.dao.IStatDataObjectDao#insertStatDataObject(com.haoyu.sip.echarts.entity.StatDataObject)
	 */
	@Override
	public int insertStatDataValue(StatDataValue statDataObject) {
		return super.insert(statDataObject);
	}


	/* (non-Javadoc)
	 * @see com.haoyu.sip.echarts.dao.IStatDataObjectDao#deleteStatDataObjectByPhysics(java.lang.String)
	 */
	@Override
	public int deleteStatDataValueByPhysics(StatChartsParam statChartsParam) {
		return super.deleteByPhysics(statChartsParam);
	}


	@Override
	public List<StatDataValue> selectStatDataValueByStatChartsParam(
			StatChartsParam scp) {
		return super.selectList("selectStatDataValueByStatChartsParam", scp);
	}


}
