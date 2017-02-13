package com.haoyu.sip.textbook.entity;

import com.haoyu.sip.excel.annotations.ImportField;
import com.haoyu.sip.excel.model.ImportModel;

public class ExcelTextBookRelation extends ImportModel{
	@ImportField(colName="学段")
	private String stage;
	@ImportField(colName="学科")
	private String subject;
	@ImportField(colName="年级")
	private String grade;
	@ImportField(colName="版本")
	private String version;
	@ImportField(colName="章")
	private String chapter;
	@ImportField(colName="节")
	private String section;
	public String getStage() {
		return stage;
	}
	public void setStage(String stage) {
		this.stage = stage;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getChapter() {
		return chapter;
	}
	public void setChapter(String chapter) {
		this.chapter = chapter;
	}
	public String getSection() {
		return section;
	}
	public void setSection(String section) {
		this.section = section;
	}
	
	
	
}
