/**
 * 
 */
package com.haoyu.sip.cms.recommend.entity;

import com.haoyu.sip.cms.channel.entity.Channel;
import com.haoyu.sip.core.entity.BaseEntity;
import com.haoyu.sip.core.entity.Relation;

/**
 * @author lianghuahuang
 *
 */
public class Recommend extends BaseEntity {
	
	private String id;
	
	private Channel channel;
	
	private Relation relation;
	
	private int orderNo;
	
	public Recommend(){}
	
	public Recommend(String id){
		this.id = id;
	}
	
	public Recommend(Channel channel){
		this.channel = channel;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Channel getChannel() {
		return channel;
	}

	public void setChannel(Channel channel) {
		this.channel = channel;
	}

	public Relation getRelation() {
		return relation;
	}

	public void setRelation(Relation relation) {
		this.relation = relation;
	}

	public int getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}
}
