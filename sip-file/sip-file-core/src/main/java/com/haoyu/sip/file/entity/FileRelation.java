package com.haoyu.sip.file.entity;

import org.apache.commons.codec.digest.DigestUtils;

import com.haoyu.base.entity.BaseEntity;
import com.haoyu.sip.core.entity.Relation;

public class FileRelation extends BaseEntity{
	
	private static final long serialVersionUID = 6279412838141194755L;

	private String fileId; //可以是fileInfo.id, 也可以是fileResource.id
	
	private Relation relation;
	
	private String type;
	
	private int downloadNum;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public Relation getRelation() {
		return relation;
	}

	public void setRelation(Relation relation) {
		this.relation = relation;
	}

	public int getDownloadNum() {
		return downloadNum;
	}

	public void setDownloadNum(int downloadNum) {
		this.downloadNum = downloadNum;
	}
	
	public static String getId(String fileId, String relationId){
		return DigestUtils.md5Hex(fileId+relationId);
	}
	
}
