/**
 * 
 */
package com.haoyu.sip.cms.teacher.entity;

import java.util.List;

import com.haoyu.sip.core.entity.BaseEntity;
import com.haoyu.sip.file.entity.FileInfo;

/**
 * @author lianghuahuang
 *
 */
public class Teacher extends BaseEntity {
	
	private String id;
	
	private List<String> relationIds;
	
	/**
	 * 姓名
	 */
	private String fullName;
	
	/**
	 * 个人头像、照片
	 */
	private String avatar;
	
	/**
	 * 学历
	 */
	private String eduBackground;
	
	/**
	 * 职称
	 */
	private String jobTitle;
	
	/**
	 * 职务
	 */
	private String position;
	
	/**
	 * 个人简介
	 */
	private String personalProfile;
	
	/**
	 * 证件编号，用于身份识别
	 */
	private String idNo;
	
	private int orderNo;
	
	/**
	 * 用于头像上传请求参数
	 */
	private FileInfo fileInfo;
	
	public Teacher(){}
	
	public Teacher(String id) {
		super();
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<String> getRelationIds() {
		return relationIds;
	}

	public void setRelationIds(List<String> relationIds) {
		this.relationIds = relationIds;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getEduBackground() {
		return eduBackground;
	}

	public void setEduBackground(String eduBackground) {
		this.eduBackground = eduBackground;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getPersonalProfile() {
		return personalProfile;
	}

	public void setPersonalProfile(String personalProfile) {
		this.personalProfile = personalProfile;
	}

	public FileInfo getFileInfo() {
		return fileInfo;
	}

	public void setFileInfo(FileInfo fileInfo) {
		this.fileInfo = fileInfo;
	}

	public String getIdNo() {
		return idNo;
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}

	public int getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}
	
}
