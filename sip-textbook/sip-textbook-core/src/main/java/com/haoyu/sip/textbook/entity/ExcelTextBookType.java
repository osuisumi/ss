package com.haoyu.sip.textbook.entity;

import com.haoyu.sip.excel.annotations.ImportField;
import com.haoyu.sip.excel.model.ImportModel;

public class ExcelTextBookType extends ImportModel{
	@ImportField(colName="字典编码")
	private String textBookTypeCode;
	@ImportField(colName="字典名称")
	private String textBookTypeName;
	public String getTextBookTypeCode() {
		return textBookTypeCode;
	}
	public void setTextBookTypeCode(String textBookTypeCode) {
		this.textBookTypeCode = textBookTypeCode;
	}
	public String getTextBookTypeName() {
		return textBookTypeName;
	}
	public void setTextBookTypeName(String textBookTypeName) {
		this.textBookTypeName = textBookTypeName;
	}

	
}
