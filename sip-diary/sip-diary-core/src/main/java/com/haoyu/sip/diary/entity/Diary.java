package com.haoyu.sip.diary.entity;

import java.util.List;

import com.google.common.collect.Lists;
import com.haoyu.sip.core.entity.BaseEntity;
import com.haoyu.sip.file.entity.FileInfo;

public class Diary extends BaseEntity {

	private static final long serialVersionUID = 6871811036658164582L;

	private String id;

	private String title;
	
	private String content;
	
	private String state;
	
	private int browseNum;
	
	private int supportNum;
	
	private int commentNum;
	
	private DiaryCategory diaryCategory;
	
	private String visitPermission;
	
	private String isTop;
	
	private String isEssence;
	
	private String isHot;
	
	private List<FileInfo> fileInfos = Lists.newArrayList();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getBrowseNum() {
		return browseNum;
	}

	public void setBrowseNum(int browseNum) {
		this.browseNum = browseNum;
	}

	public int getSupportNum() {
		return supportNum;
	}

	public void setSupportNum(int supportNum) {
		this.supportNum = supportNum;
	}

	public int getCommentNum() {
		return commentNum;
	}

	public void setCommentNum(int commentNum) {
		this.commentNum = commentNum;
	}

	public DiaryCategory getDiaryCategory() {
		return diaryCategory;
	}

	public void setDiaryCategory(DiaryCategory diaryCategory) {
		this.diaryCategory = diaryCategory;
	}

	public String getVisitPermission() {
		return visitPermission;
	}

	public void setVisitPermission(String visitPermission) {
		this.visitPermission = visitPermission;
	}

	public String getIsTop() {
		return isTop;
	}

	public void setIsTop(String isTop) {
		this.isTop = isTop;
	}

	public String getIsEssence() {
		return isEssence;
	}

	public void setIsEssence(String isEssence) {
		this.isEssence = isEssence;
	}

	public String getIsHot() {
		return isHot;
	}

	public void setIsHot(String isHot) {
		this.isHot = isHot;
	}

	public List<FileInfo> getFileInfos() {
		return fileInfos;
	}

	public void setFileInfos(List<FileInfo> fileInfos) {
		this.fileInfos = fileInfos;
	}
	
}