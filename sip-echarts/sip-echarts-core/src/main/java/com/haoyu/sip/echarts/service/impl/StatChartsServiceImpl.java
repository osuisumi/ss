/**
 * 
 */
package com.haoyu.sip.echarts.service.impl;

import java.util.List;
import java.util.Map;






import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.google.common.collect.Maps;
import com.haoyu.sip.echarts.dao.IStatChartsDao;
import com.haoyu.sip.echarts.entity.StatCharts;
import com.haoyu.sip.echarts.service.IStatChartsService;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.utils.Identities;

/**
 * @author lianghuahuang
 *
 */
@Service
public class StatChartsServiceImpl implements IStatChartsService {
	@Resource
	private IStatChartsDao statChartsDao;

	/* (non-Javadoc)
	 * @see com.haoyu.sip.echarts.service.IStatChartsService#createStatCharts(com.haoyu.sip.echarts.entity.StatCharts)
	 */
	@Override
	@CacheEvict(value="statCharts",allEntries=true)
	public Response createStatCharts(StatCharts statCharts) {
		if(statCharts==null){
			return Response.failInstance().responseMsg("createStatCharts fail!statCharts is null!");
		}
		if(StringUtils.isEmpty(statCharts.getId())){
			statCharts.setId(Identities.uuid2());
		}
		int count = statChartsDao.insertStatCharts(statCharts);
		return count>0?Response.successInstance().responseData(statCharts):Response.failInstance().responseMsg("createStatCharts fail!");
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.echarts.service.IStatChartsService#updateStatCharts(com.haoyu.sip.echarts.entity.StatCharts)
	 */
	@Override
	public Response updateStatCharts(StatCharts statCharts) {
		if(statCharts==null||StringUtils.isEmpty(statCharts.getId())){
			return Response.failInstance().responseMsg("updateStatCharts is fail!statCharts is null or statCharts's id is null");
		}
		int count = statChartsDao.updateStatCharts(statCharts);
		return count>0?Response.successInstance().responseData(statCharts):Response.failInstance().responseMsg("updateStatCharts fail!");
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.echarts.service.IStatChartsService#deleteStatCharts(com.haoyu.sip.echarts.entity.StatCharts)
	 */
	@Override
	@CacheEvict(value="statCharts",allEntries=true)
	public Response deleteStatCharts(StatCharts statCharts) {
		if(statCharts==null||StringUtils.isEmpty(statCharts.getId())){
			return Response.failInstance().responseMsg("deleteStatCharts is fail!statCharts is null or statCharts's id is null");
		}
		int count = statChartsDao.deleteStatChartsByLogic(statCharts);
		return count>0?Response.successInstance():Response.failInstance().responseMsg("deleteStatCharts fail!");
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.echarts.service.IStatChartsService#findStatChartsById(java.lang.String)
	 */
	@Override
	public StatCharts findStatChartsById(String id) {
		if(StringUtils.isEmpty(id)){
			return null;
		}
		return statChartsDao.selectStatChartsById(id);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.echarts.service.IStatChartsService#findStatChartss(com.haoyu.sip.echarts.entity.StatCharts)
	 */
	@Override
	public List<StatCharts> findStatCharts(StatCharts statCharts) {
		Map<String,Object> parameter = Maps.newHashMap();
		if(statCharts!=null){
			if(StringUtils.isNotEmpty(statCharts.getName())){
				parameter.put("name", statCharts.getName());
			}
			if(StringUtils.isNotEmpty(statCharts.getDataType())){
				parameter.put("dataType", statCharts.getDataType());
			}
		}
		return statChartsDao.findAll(parameter);
	}

	@Override
	public List<StatCharts> findStatCharts(StatCharts statCharts, PageBounds pageBounds) {
		Map<String,Object> parameter = Maps.newHashMap();
		if(statCharts!=null){
			if(StringUtils.isNotEmpty(statCharts.getName())){
				parameter.put("name", statCharts.getName());
			}
			if(StringUtils.isNotEmpty(statCharts.getDataType())){
				parameter.put("dataType", statCharts.getDataType());
			}
		}
		return statChartsDao.findAll(parameter,pageBounds);
	}

	@Override
	@Cacheable(key="'stat_'+(#parameter['type']==null?'':#parameter['type'])+(#parameter['relationId']==null?'':#parameter['relationId'])+(#pageBounds==null?'':(#pageBounds.page+'_'+#pageBounds.limit))",value="statCharts")
	public List<StatCharts> findStatCharts(Map<String, Object> parameter,
			PageBounds pageBounds) {
		return statChartsDao.findAll(parameter,pageBounds);
	}

}
