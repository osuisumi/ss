package com.haoyu.sip.comment.entity;

import java.util.List;

import com.google.common.collect.Lists;
import com.haoyu.base.entity.BaseEntity;
import com.haoyu.sip.core.entity.Relation;

public class Comment extends BaseEntity {

	private static final long serialVersionUID = 3915766281591163800L;

	private Relation relation;

	private int childNum;

	private String content;

	private String mainId;

	private String parentId;

	private float evaluateScore;

	private List<Comment> childComments = Lists.newArrayList();

	private Comment parentComment;

	private String targetId;

	public float getEvaluateScore() {
		return evaluateScore;
	}

	public void setEvaluateScore(float evaluateScore) {
		this.evaluateScore = evaluateScore;
	}

	public String getMainId() {
		return mainId;
	}

	public void setMainId(String mainId) {
		this.mainId = mainId;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public int getChildNum() {
		return childNum;
	}

	public void setChildNum(int childNum) {
		this.childNum = childNum;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @return the relation
	 */
	public Relation getRelation() {
		return relation;
	}

	/**
	 * @param relation
	 *            the relation to set
	 */
	public void setRelation(Relation relation) {
		this.relation = relation;
	}

	public List<Comment> getChildComments() {
		return childComments;
	}

	public void setChildComments(List<Comment> childComments) {
		this.childComments = childComments;
	}

	public Comment getParentComment() {
		return parentComment;
	}

	public void setParentComment(Comment parentComment) {
		this.parentComment = parentComment;
	}

	public String getTargetId() {
		return targetId;
	}

	public void setTargetId(String targetId) {
		this.targetId = targetId;
	}

}