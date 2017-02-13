package com.haoyu.sip.textbook.entity;

import java.io.Serializable;

import com.haoyu.sip.excel.annotations.ExcelEntity;
import com.haoyu.sip.excel.annotations.ExportField;

@ExcelEntity(sortHead=true,sheetName="导入结果",wrapText=true)
public class TextBookRelationExport implements Serializable{
	private static final long serialVersionUID = 6529930104085630326L;
	
	@ExportField(colName="学段")
	private String stage;
	@ExportField(colName="学科")
	private String subject;
	@ExportField(colName="年级")
	private String grade;
	@ExportField(colName="信息")
	private String msg;
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
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	

}
