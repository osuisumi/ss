/**
 * 
 */
package com.haoyu.sip.gallery.entity;

import java.util.List;

import com.google.common.collect.Lists;
import com.haoyu.sip.core.entity.BaseEntity;
import com.haoyu.sip.core.entity.Relation;
import com.haoyu.sip.file.entity.FileInfo;

/**
 * @author lianghuahuang
 *
 */
public class PhotoGallery extends BaseEntity {
	private String id;
	
	private Relation relation = new Relation();
	
	private String name;
	
	private String description;
	
	private String state;
	
	private String frontCover;
	
	//隐私设置，公开，对好友开放，仅对自己
	private String privacy;
	
	/** 附件:用于封面图片 */
	private FileInfo fileInfo;
	
	
	public PhotoGallery(){}
	
	public PhotoGallery(String id) {
		super();
		this.id = id;
	}

	/**
	 * 相片数量
	 */
	private int photoNumber;
	
	private List<Photo> photos;

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

	

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getFrontCover() {
		return frontCover;
	}

	public void setFrontCover(String frontCover) {
		this.frontCover = frontCover;
	}

	public String getPrivacy() {
		return privacy;
	}

	public void setPrivacy(String privacy) {
		this.privacy = privacy;
	}

	public List<Photo> getPhotos() {
		return photos;
	}

	public void setPhotos(List<Photo> photos) {
		this.photos = photos;
	}

	public int getPhotoNumber() {
		return photoNumber;
	}

	public void setPhotoNumber(int photoNumber) {
		this.photoNumber = photoNumber;
	}

	public FileInfo getFileInfo() {
		return fileInfo;
	}

	public void setFileInfo(FileInfo fileInfo) {
		this.fileInfo = fileInfo;
	}

	
	
	
}
