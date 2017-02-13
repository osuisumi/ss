package com.haoyu.sip.mobile.file.entity;

import java.io.Serializable;

import com.haoyu.sip.file.utils.FileUtils;

public class MFileInfo implements Serializable{
	
	private static final long serialVersionUID = -5246363250912643166L;

	private String id;
	
	private String fileName;
	
	private String url;
	
	private long fileSize;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getUrl() {
		String prefix = FileUtils.getHttpHost();
		if (url != null && !url.contains(prefix)) {
			url = prefix + url;
		}
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public long getFileSize() {
		return fileSize;
	}

	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}

}
