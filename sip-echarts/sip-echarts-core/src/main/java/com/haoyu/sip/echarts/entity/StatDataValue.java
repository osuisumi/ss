/**
 * 
 */
package com.haoyu.sip.echarts.entity;

/**
 * @author lianghuahuang
 *
 */
public class StatDataValue {
	
	private StatChartsParam statChartsParam;
	//列名，在echarts的三维图中主要体现为图例对应的名称，字段名称可以为空
	private String columnFieldName;
	//行名称，在echarts中主要体现为横向坐标轴所以对应的名称 ，或者二维图中的数据值，改字段名称不能为空，否则数据加载会出错
	private String rowFieldName;
	//行列所对应的统计数据值
	private float dataValue;
	
	public StatDataValue(){}
	
	public StatDataValue(String rowFieldName, 
			float dataValue) {
		this.rowFieldName = rowFieldName;
		this.dataValue = dataValue;
	}

	public StatDataValue(String rowFieldName,String columnFieldName, 
			float dataValue) {
		this.columnFieldName = columnFieldName;
		this.rowFieldName = rowFieldName;
		this.dataValue = dataValue;
	}

	public StatChartsParam getStatChartsParam() {
		return statChartsParam;
	}

	public void setStatChartsParam(StatChartsParam statChartsParam) {
		this.statChartsParam = statChartsParam;
	}

	public String getColumnFieldName() {
		return columnFieldName;
	}

	public void setColumnFieldName(String columnFieldName) {
		this.columnFieldName = columnFieldName;
	}

	public String getRowFieldName() {
		return rowFieldName;
	}

	public void setRowFieldName(String rowFieldName) {
		this.rowFieldName = rowFieldName;
	}

	public float getDataValue() {
		return dataValue;
	}

	public void setDataValue(float dataValue) {
		this.dataValue = dataValue;
	}
}
