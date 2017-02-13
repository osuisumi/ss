/**
 * 
 */
package com.haoyu.sip.attitude.entity;

import org.apache.commons.codec.digest.DigestUtils;

import com.haoyu.sip.core.entity.BaseEntity;
import com.haoyu.sip.core.entity.Relation;

/**
 * @author lianghuahuang
 * 
 */
public class AttitudeUser extends BaseEntity {
	
	private String id;
	/**
	 * 观点：赞、踩、打赏等等，由调用方提供
	 */
	private String attitude;
	
	private Relation relation = new Relation();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAttitude() {
		return attitude;
	}

	public void setAttitude(String attitude) {
		this.attitude = attitude;
	}

	public Relation getRelation() {
		return relation;
	}

	public void setRelation(Relation relation) {
		this.relation = relation;
	}
	
	public static String getId(String attitude,String relationId,String creator){
		return DigestUtils.md5Hex(attitude+relationId+creator);
	}
}
