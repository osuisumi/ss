package com.haoyu.sip.cms.course.entity;

import java.util.List;

import com.haoyu.sip.core.entity.TimePeriod;
import com.haoyu.sip.core.entity.User;

public class Course {
	private String id;

	private String relationId;

	private TimePeriod timePeriod;

	private int participateNum;
	
	private String courseId;
	
	private String courseTitle;
	
	private String courseImage;
	
	private String studyHours;
	
	private String courseType ;
	
	private String trainId;
	
	private String trainName;
	
	private List<User> authors;
	
	private String courseContent;
	
	private String courseSummary;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRelationId() {
		return relationId;
	}

	public void setRelationId(String relationId) {
		this.relationId = relationId;
	}

	public TimePeriod getTimePeriod() {
		return timePeriod;
	}

	public void setTimePeriod(TimePeriod timePeriod) {
		this.timePeriod = timePeriod;
	}

	public int getParticipateNum() {
		return participateNum;
	}

	public void setParticipateNum(int participateNum) {
		this.participateNum = participateNum;
	}


	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	public String getCourseTitle() {
		return courseTitle;
	}

	public void setCourseTitle(String coursetTitle) {
		this.courseTitle = coursetTitle;
	}

	public String getCourseImage() {
		return courseImage;
	}

	public void setCourseImage(String courseImage) {
		this.courseImage = courseImage;
	}

	public String getStudyHours() {
		return studyHours;
	}

	public void setStudyHours(String studyHours) {
		this.studyHours = studyHours;
	}

	public String getCourseType() {
		return courseType;
	}

	public void setCourseType(String courseType) {
		this.courseType = courseType;
	}

	public String getTrainId() {
		return trainId;
	}

	public void setTrainId(String trainId) {
		this.trainId = trainId;
	}

	public String getTrainName() {
		return trainName;
	}

	public void setTrainName(String trainName) {
		this.trainName = trainName;
	}

	public List<User> getAuthors() {
		return authors;
	}

	public void setAuthors(List<User> authors) {
		this.authors = authors;
	}

	public String getCourseContent() {
		return courseContent;
	}

	public void setCourseContent(String courseContent) {
		this.courseContent = courseContent;
	}

	public String getCourseSummary() {
		return courseSummary;
	}

	public void setCourseSummary(String courseSummary) {
		this.courseSummary = courseSummary;
	}
	
}
