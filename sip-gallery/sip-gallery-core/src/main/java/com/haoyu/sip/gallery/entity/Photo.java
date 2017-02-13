/**
 * 
 */
package com.haoyu.sip.gallery.entity;

import java.util.List;

import com.haoyu.sip.core.entity.BaseEntity;
import com.haoyu.sip.core.entity.Relation;
import com.haoyu.sip.file.entity.FileInfo;

/**
 * @author lianghuahuang
 *
 */
public class Photo extends BaseEntity {
	
	private String id;
	
	private String name;
	
	private String description;
	
	private int orderNo;
	
	private String state;
	
	private List<PhotoGallery> photoGalleries;
	
	private FileInfo fileInfo;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public List<PhotoGallery> getPhotoGalleries() {
		return photoGalleries;
	}

	public void setPhotoGalleries(List<PhotoGallery> photoGalleries) {
		this.photoGalleries = photoGalleries;
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

	public int getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public FileInfo getFileInfo() {
		return fileInfo;
	}

	public void setFileInfo(FileInfo fileInfo) {
		this.fileInfo = fileInfo;
	}
}
