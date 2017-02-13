/**
 * 
 */
package com.haoyu.sip.echarts.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Table;
import com.haoyu.sip.echarts.dao.IStatChartsDao;
import com.haoyu.sip.echarts.dao.IStatChartsParamDao;
import com.haoyu.sip.echarts.dao.IStatDataValueDao;
import com.haoyu.sip.echarts.entity.StatChartsParam;
import com.haoyu.sip.echarts.entity.StatDataValue;
import com.haoyu.sip.echarts.service.IStatChartsParamService;
import com.haoyu.sip.echarts.service.IStatDataValueService;

/**
 * @author lianghuahuang
 *
 */
@Service
public class StatDataValueServiceImpl implements IStatDataValueService {
	@Resource
	private IStatDataValueDao statDataValueDao;
	
	@Resource
	private IStatChartsParamDao statChartsParamDao;
	
	@Resource
	private IStatChartsDao statChartsDao;
	
	@Resource
	private IStatChartsParamService statChartsParamService;
	/* (non-Javadoc)
	 * @see com.haoyu.sip.echarts.service.IStatDataValueService#findStatDataValues(com.haoyu.sip.echarts.entity.StatChartsParam)
	 */
	@Override
	public List<StatDataValue> findStatDataValues(
			StatChartsParam statChartsParam) {
		if(statChartsParam.getId()==null){
			String statChartsId = statChartsParam.getStatCharts().getId();
			String requestParam = statChartsParam.getRequestParam();
			statChartsParam.setId(StatChartsParam.generateId(statChartsId, requestParam));
		}
		StatChartsParam scp = statChartsParamService.findStatChartsParamById(statChartsParam.getId());
		if(scp!=null){
			long lastCacheTime  =scp.getLastCacheTime();
			int cacheExpireTime = scp.getStatCharts().getCacheExpireTime();
			//缓存超时 重新获取
			if(System.currentTimeMillis()-lastCacheTime>cacheExpireTime*1000*60){
				StatDataValue[] statDataValues = reloadStatDataValue(scp);
				//更新缓存最后更新时间
				scp.setLastCacheTime(System.currentTimeMillis());
				statChartsParamService.updateStatChartsParam(scp);
				return Lists.newArrayList(statDataValues);
			}
			return statDataValueDao.selectStatDataValueByStatChartsParam(scp);
		}else{
			//查询数据
			StatDataValue[] statDataValues = reloadStatDataValue(statChartsParam);
			statChartsParam.setLastCacheTime(System.currentTimeMillis());
			//插入参数表
			statChartsParamService.insertStatChartsParam(statChartsParam);
			return Lists.newArrayList(statDataValues);
		}
	}
	/**
	 * @param scp
	 * @return
	 */
	private StatDataValue[] reloadStatDataValue(StatChartsParam scp) {
		RestTemplate restTemplate = new RestTemplate();
		StatDataValue[] statDataValues = restTemplate.getForObject(scp.getStatCharts().getRequestUri(), StatDataValue[].class,scp.getRequestParam());
		if(statDataValues!=null){
			//插入
			for(StatDataValue sdv:statDataValues){
				sdv.setStatChartsParam(scp);
				statDataValueDao.insertStatDataValue(sdv);
			}			
		}
		return statDataValues;
	}
	@Override
	public Table<String, String, Float> findStatDataValueTable(
			StatChartsParam statChartsParam) {
		//List<StatDataValue> statDataValues = this.findStatDataValues(statChartsParam);
		List<StatDataValue> statDataValues = this.findStatDataValues3DDemo(statChartsParam);
		if(statDataValues!=null&&!statDataValues.isEmpty()){
			Table<String, String, Float> statDataValueTable = HashBasedTable.create();
			for(StatDataValue sdv:statDataValues){
				statDataValueTable.put(sdv.getRowFieldName(), sdv.getColumnFieldName(), Float.valueOf(sdv.getDataValue()));
			}
			return statDataValueTable;
		}
		return HashBasedTable.create();
	}
	@Override
	public Map<String, Float> findStatDataValueMap(
			StatChartsParam statChartsParam) {
		//List<StatDataValue> statDataValues = this.findStatDataValues(statChartsParam);
		List<StatDataValue> statDataValues = this.findStatDataValues2DDemo(statChartsParam);
		if(statDataValues!=null&&!statDataValues.isEmpty()){
			Map<String,Float> statDataValueMap = Maps.newHashMap();
			for(StatDataValue sdv:statDataValues){
				statDataValueMap.put(sdv.getRowFieldName(), sdv.getDataValue());
			}
			return statDataValueMap;
		}
		return null;
	}
	
	private List<StatDataValue> findStatDataValues3DDemo(StatChartsParam statChartsParam){
		List<StatDataValue> statDataValues = Lists.newArrayList();
		StatDataValue sdv = new StatDataValue("18-25岁","女性教师",10000);
		statDataValues.add(sdv);
		sdv = new StatDataValue("26-30岁","女性教师",7000);
		statDataValues.add(sdv);
		sdv = new StatDataValue("31-35岁","女性教师",1000);
		statDataValues.add(sdv);
		sdv = new StatDataValue("36-40岁","女性教师",400);
		statDataValues.add(sdv);
		sdv = new StatDataValue("41-50岁","女性教师",400);
		statDataValues.add(sdv);
		sdv = new StatDataValue("51-55岁","女性教师",1000);
		statDataValues.add(sdv);
		sdv = new StatDataValue("55岁以上","女性教师",13000);
		statDataValues.add(sdv);
		

		sdv = new StatDataValue("18-25岁","男性教师",1000);
		statDataValues.add(sdv);
		sdv = new StatDataValue("26-30岁","男性教师",10000);
		statDataValues.add(sdv);
		sdv = new StatDataValue("31-35岁","男性教师",9000);
		statDataValues.add(sdv);
		sdv = new StatDataValue("36-40岁","男性教师",4000);
		statDataValues.add(sdv);
		sdv = new StatDataValue("41-50岁","男性教师",4000);
		statDataValues.add(sdv);
		sdv = new StatDataValue("51-55岁","男性教师",10000);
		statDataValues.add(sdv);
		sdv = new StatDataValue("55岁以上","男性教师",9000);
		statDataValues.add(sdv);

		sdv = new StatDataValue("18-25岁","全部教师",26000);
		statDataValues.add(sdv);
		sdv = new StatDataValue("26-30岁","全部教师",26000);
		statDataValues.add(sdv);
		sdv = new StatDataValue("31-35岁","全部教师",22000);
		statDataValues.add(sdv);
		sdv = new StatDataValue("36-40岁","全部教师",14000);
		statDataValues.add(sdv);
		sdv = new StatDataValue("41-50岁","全部教师",14000);
		statDataValues.add(sdv);
		sdv = new StatDataValue("51-55岁","全部教师",18000);
		statDataValues.add(sdv);
		sdv = new StatDataValue("55岁以上","全部教师",15000);
		statDataValues.add(sdv);
		
		return statDataValues;
	}
	
	private List<StatDataValue> findStatDataValues2DDemo(StatChartsParam statChartsParam){
		List<StatDataValue> statDataValues = Lists.newArrayList();
		StatDataValue sdv = new StatDataValue("男","",1000);
		statDataValues.add(sdv);
		sdv = new StatDataValue("女","",750);
		statDataValues.add(sdv);
		return statDataValues;
	}

}
