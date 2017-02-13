/**
 * 
 */
package com.haoyu.sip.follow.entity;

import java.io.Serializable;
import com.haoyu.sip.core.entity.Relation;

/**
 * @author lianghuahuang
 *
 */
public class FollowStat  implements Serializable  {
	
	private String followedId;
	
	private int followNum;

	public String getFollowedId() {
		return followedId;
	}

	public void setFollowedId(String followedId) {
		this.followedId = followedId;
	}

	public int getFollowNum() {
		return followNum;
	}

	public void setFollowNum(int followNum) {
		this.followNum = followNum;
	}
}
