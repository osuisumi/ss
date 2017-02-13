/**
 * 
 */
package com.haoyu.sip.comment.entity;

import java.io.Serializable;

/**
 * @author lianghuahuang
 *
 */
public class CommentStat implements Serializable {
	
	private String relationId;
	
	private int commentNum;

	/**
	 * @return the relationId
	 */
	public String getRelationId() {
		return relationId;
	}

	/**
	 * @param relationId the relationId to set
	 */
	public void setRelationId(String relationId) {
		this.relationId = relationId;
	}

	/**
	 * @return the commentNum
	 */
	public int getCommentNum() {
		return commentNum;
	}

	/**
	 * @param commentNum the commentNum to set
	 */
	public void setCommentNum(int commentNum) {
		this.commentNum = commentNum;
	}
}
