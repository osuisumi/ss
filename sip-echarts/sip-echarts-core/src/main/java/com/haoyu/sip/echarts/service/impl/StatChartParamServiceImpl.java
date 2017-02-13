/**
 * 
 */
package com.haoyu.sip.echarts.service.impl;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.echarts.dao.IStatChartsParamDao;
import com.haoyu.sip.echarts.entity.StatChartsParam;
import com.haoyu.sip.echarts.service.IStatChartsParamService;
import com.haoyu.sip.utils.Identities;

/**
 * @author lianghuahuang
 *
 */
@Service
public class StatChartParamServiceImpl implements IStatChartsParamService {
	
	@Resource
	private IStatChartsParamDao statChartsParamDao;

	/* (non-Javadoc)
	 * @see com.haoyu.sip.echarts.service.IStatChartsParamService#findStatChartsParamById(java.lang.String)
	 */
	@Override
	public StatChartsParam findStatChartsParamById(String id) {
		return statChartsParamDao.selectStatChartsParamById(id);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.echarts.service.IStatChartsParamService#findStatChartsParamByChartsPram(java.lang.String, java.lang.String)
	 */
	@Override
	public StatChartsParam findStatChartsParamByChartsPram(String statChartsId,
			String requestParam) {
		return statChartsParamDao.selectStatChartsParamById(StatChartsParam.generateId(statChartsId, requestParam));
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.echarts.service.IStatChartsParamService#updateStatChartsParam(com.haoyu.sip.echarts.entity.StatChartsParam)
	 */
	@Override
	public Response updateStatChartsParam(StatChartsParam statChartsParam) {
		if(statChartsParam==null||StringUtils.isEmpty(statChartsParam.getId())){
			return Response.failInstance().responseMsg("updateStatChartsParam is fail!statChartsParam is null or statChartsParam's id is null");
		}
		int count = statChartsParamDao.updateStatChartsParam(statChartsParam);
		return count>0?Response.successInstance().responseData(statChartsParam):Response.failInstance().responseMsg("updateStatChartsParam fail!");
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.echarts.service.IStatChartsParamService#insertStatChartsParam(com.haoyu.sip.echarts.entity.StatChartsParam)
	 */
	@Override
	public Response insertStatChartsParam(StatChartsParam statChartsParam) {
		if(statChartsParam==null){
			return Response.failInstance().responseMsg("createStatChartsParam fail!statChartsParam is null!");
		}
		if(StringUtils.isEmpty(statChartsParam.getId())){
			statChartsParam.setId(StatChartsParam.generateId(statChartsParam.getStatCharts().getId(), statChartsParam.getRequestParam()));
		}
		int count = statChartsParamDao.insertStatChartsParam(statChartsParam);
		return count>0?Response.successInstance().responseData(statChartsParam):Response.failInstance().responseMsg("createStatChartsParam fail!");
	}

}
