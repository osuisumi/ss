/**
 * 
 */
package com.haoyu.sip.core.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 时间段
 * @author lianghuahuang
 *
 */
public class TimePeriod implements Serializable {
	
	/**
	 * 开始时间
	 */
	private Date startTime;
	
	/**
	 * 结束时间
	 */
	private Date endTime;
	
	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
}
