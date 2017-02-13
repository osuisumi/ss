package com.haoyu.sip.textbook.mobile.entity;

import java.io.Serializable;

public class MTextBookEntry implements Serializable{

	private static final long serialVersionUID = 2307961539584132604L;

	private String textBookValue;
	
	private String textBookName;

	public MTextBookEntry() {
	}

	public String getTextBookValue() {
		return textBookValue;
	}

	public void setTextBookValue(String textBookValue) {
		this.textBookValue = textBookValue;
	}

	public String getTextBookName() {
		return textBookName;
	}

	public void setTextBookName(String textBookName) {
		this.textBookName = textBookName;
	}
	
}
