/**
 * 
 */
package com.haoyu.sip.echarts.entity;

import com.haoyu.sip.core.entity.BaseEntity;
import com.haoyu.sip.core.entity.Relation;

/**
 * @author lianghuahuang
 *
 */
public class StatCharts extends BaseEntity{
	
	private String id;
	
	private Relation relation;
	
	private String name;
	
	//数据类型：二维(2D),三维(3D)
	private String dataType;
	
	private String requestUri;
	
	private int cacheExpireTime;

	public StatCharts(){}
	
	public StatCharts(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	

	public Relation getRelation() {
		return relation;
	}

	public void setRelation(Relation relation) {
		this.relation = relation;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}


	public String getRequestUri() {
		return requestUri;
	}

	public void setRequestUri(String requestUri) {
		this.requestUri = requestUri;
	}

	public int getCacheExpireTime() {
		return cacheExpireTime;
	}

	public void setCacheExpireTime(int cacheExpireTime) {
		this.cacheExpireTime = cacheExpireTime;
	}


}
