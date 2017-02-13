/**
 * 
 */
package com.haoyu.sip.follow.entity;

import org.apache.commons.codec.digest.DigestUtils;

import com.haoyu.sip.core.entity.BaseEntity;
import com.haoyu.sip.core.entity.Relation;

/**
 * @author lianghuahuang
 * 关注
 */
public class Follow extends BaseEntity {
	
	private static final long serialVersionUID = 6412381488676342613L;

	private String id;
	
	private Relation followEntity = new Relation();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Relation getFollowEntity() {
		return followEntity;
	}

	public void setFollowEntity(Relation followEntity) {
		this.followEntity = followEntity;
	}
	
	public static String getId(String userId,String followedId,String followedType){
		return DigestUtils.md5Hex(userId+followedId+followedType);
	}
}
