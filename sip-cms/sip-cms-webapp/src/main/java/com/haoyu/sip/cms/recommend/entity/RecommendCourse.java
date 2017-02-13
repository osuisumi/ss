/**
 * 
 */
package com.haoyu.sip.cms.recommend.entity;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author lianghuahuang
 *
 */
public class RecommendCourse implements Serializable {
	
	private Recommend recommend;
	
	private String id;
	
	private String courseName;
	
	private String frontCover;
	
	private int starLevel;
	
	private String author;
	
	private BigDecimal studyHours;
	
	public RecommendCourse(){}
	
	public RecommendCourse(Recommend recommend){
		this.recommend =recommend;
	}
	
	public RecommendCourse(Recommend recommend,String id){
		this.recommend =recommend;
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Recommend getRecommend() {
		return recommend;
	}

	public void setRecommend(Recommend recommend) {
		this.recommend = recommend;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getFrontCover() {
		return frontCover;
	}

	public void setFrontCover(String frontCover) {
		this.frontCover = frontCover;
	}

	public int getStarLevel() {
		return starLevel;
	}

	public void setStarLevel(int starLevel) {
		this.starLevel = starLevel;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public BigDecimal getStudyHours() {
		return studyHours;
	}

	public void setStudyHours(BigDecimal studyHours) {
		this.studyHours = studyHours;
	}
	
	

}
