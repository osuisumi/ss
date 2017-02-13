/**
 * 
 */
package com.haoyu.sip.echarts.entity;

import org.springframework.util.DigestUtils;

/**
 * @author lianghuahuang
 *
 */
public class StatChartsParam {
	
	private String id;
	
	private StatCharts statCharts;
	
	private String requestParam;
	
	private long lastCacheTime;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public StatCharts getStatCharts() {
		return statCharts;
	}

	public void setStatCharts(StatCharts statCharts) {
		this.statCharts = statCharts;
	}

	public String getRequestParam() {
		return requestParam;
	}

	public void setRequestParam(String requestParam) {
		this.requestParam = requestParam;
	}

	public long getLastCacheTime() {
		return lastCacheTime;
	}

	public void setLastCacheTime(long lastCacheTime) {
		this.lastCacheTime = lastCacheTime;
	}
	
	public static String generateId(String statChartsId,String requestParam){
		return DigestUtils.md5DigestAsHex(new String(statChartsId+requestParam).getBytes());
	}
}
