/**
 * 
 */
package com.haoyu.sip.cms.resource.entity;

import com.haoyu.sip.core.entity.BaseEntity;
import com.haoyu.sip.core.entity.Relation;
import com.haoyu.sip.file.entity.FileInfo;

/**
 * @author lianghuahuang
 *
 */
public class Resource extends BaseEntity {
	
	private String id;
	
	private Relation relation;
	
	private String name;
	
	private String type;
	
	private String summary;
	
	private String url;
	
	private String frontCover;
	
	private FileInfo fileInfo;
	
	public Resource(){}
	
	public Resource(String id){
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Relation getRelation() {
		return relation;
	}

	public void setRelation(Relation relation) {
		this.relation = relation;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getFrontCover() {
		return frontCover;
	}

	public void setFrontCover(String frontCover) {
		this.frontCover = frontCover;
	}

	/**
	 * @return the fileInfo
	 */
	public FileInfo getFileInfo() {
		return fileInfo;
	}

	/**
	 * @param fileInfo the fileInfo to set
	 */
	public void setFileInfo(FileInfo fileInfo) {
		this.fileInfo = fileInfo;
	}
	
	
}
