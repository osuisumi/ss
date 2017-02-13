package com.haoyu.sip.textbook.entity;

import com.haoyu.sip.excel.annotations.ImportField;
import com.haoyu.sip.excel.model.ImportModel;

public class ExcelTextBookEntry extends ImportModel{
	@ImportField(colName="字典编码")
	private String textBookTypeCode;
	@ImportField(colName="字典项名")
	private String textBookEntryName;
	
	
	public String getTextBookTypeCode() {
		return textBookTypeCode;
	}
	public void setTextBookTypeCode(String textBookTypeCode) {
		this.textBookTypeCode = textBookTypeCode;
	}
	public String getTextBookEntryName() {
		return textBookEntryName;
	}
	public void setTextBookEntryName(String textBookEntryName) {
		this.textBookEntryName = textBookEntryName;
	}
	
	

}
