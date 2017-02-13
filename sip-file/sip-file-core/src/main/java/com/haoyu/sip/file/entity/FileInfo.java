package com.haoyu.sip.file.entity;

import java.util.List;

import com.google.common.collect.Lists;
import com.haoyu.base.entity.BaseEntity;

public class FileInfo extends BaseEntity {

	private static final long serialVersionUID = 2728242106013599032L;
	
	private String fileName;
	
	private String state;
	
	private long fileSize;
	
	private String url;
	//卷名
	private String groupName;
	
	private String remark;
	
	private FileResource fileResource;
	
	private List<FileRelation> fileRelations = Lists.newArrayList();
	
	public List<FileRelation> getFileRelations() {
		return fileRelations;
	}

	public void setFileRelations(List<FileRelation> fileRelations) {
		this.fileRelations = fileRelations;
	}

	public FileResource getFileResource() {
		return fileResource;
	}

	public void setFileResource(FileResource fileResource) {
		this.fileResource = fileResource;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public long getFileSize() {
		return fileSize;
	}

	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}
	
	@Override
    public boolean equals(Object obj) {
    	if (obj instanceof FileInfo) {
			obj = (FileInfo)obj;
			if (((FileInfo) obj).getId().equals(this.getId())) {
				return true;
			}
		}
    	return false;
    }
    
    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
		return result;
	}
	
}