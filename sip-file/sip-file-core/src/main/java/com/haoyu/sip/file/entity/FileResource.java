package com.haoyu.sip.file.entity;

import java.util.List;

import com.google.common.collect.Lists;
import com.haoyu.base.entity.BaseEntity;

public class FileResource extends BaseEntity{

	private static final long serialVersionUID = 5117792943333084433L;

	private String name;

    private String parentId;
    
    private String parentIds;

    private int fileCount;
    
    private String isFolder;
    
    private String type;
    
    private FileInfo newestFile;
    
    private List<FileRelation> fileRelations = Lists.newArrayList();

	public String getParentIds() {
		return parentIds;
	}

	public void setParentIds(String parentIds) {
		this.parentIds = parentIds;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public FileInfo getNewestFile() {
		return newestFile;
	}

	public void setNewestFile(FileInfo newestFile) {
		this.newestFile = newestFile;
	}

	public List<FileRelation> getFileRelations() {
		return fileRelations;
	}

	public void setFileRelations(List<FileRelation> fileRelations) {
		this.fileRelations = fileRelations;
	}

	public String getIsFolder() {
		return isFolder;
	}

	public void setIsFolder(String isFolder) {
		this.isFolder = isFolder;
	}

	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId == null ? null : parentId.trim();
    }

    public int getFileCount() {
        return fileCount;
    }

    public void setFileCount(int fileCount) {
        this.fileCount = fileCount;
    }

}