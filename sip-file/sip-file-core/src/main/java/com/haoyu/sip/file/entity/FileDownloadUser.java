package com.haoyu.sip.file.entity;

import com.haoyu.base.entity.BaseEntity;

public class FileDownloadUser extends BaseEntity{
	
	private static final long serialVersionUID = 4731417695061183276L;

	private String id;
	
	private String fileId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

}
