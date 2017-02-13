/**
 * 
 */
package com.haoyu.sip.gallery.entity;

import com.haoyu.sip.core.entity.BaseEntity;

/**
 * @author lianghuahuang
 *
 */
public class PhotoGalleryPhoto extends BaseEntity {
	
	private String id;
	
	private PhotoGallery photoGallery;
	
	private Photo photo;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public PhotoGallery getPhotoGallery() {
		return photoGallery;
	}

	public void setPhotoGallery(PhotoGallery photoGallery) {
		this.photoGallery = photoGallery;
	}

	public Photo getPhoto() {
		return photo;
	}

	public void setPhoto(Photo photo) {
		this.photo = photo;
	}
}
