/**
 * 
 */
package com.haoyu.sip.gallery.dao;

import java.util.List;
import java.util.Map;

import com.haoyu.sip.gallery.entity.PhotoGallery;
import com.haoyu.sip.gallery.entity.PhotoGalleryPhoto;

/**
 * @author lianghuahuang
 *
 */
public interface IPhotoGalleryPhotoDao {
	
	int insertPhotoGalleryPhoto(PhotoGalleryPhoto photoGalleryPhoto);
	
	int insertPhotoGalleryPhoto(PhotoGallery photoGallery);
	
	int deletePhotoGalleryByLogic(PhotoGallery photoGallery);
	
	int deleteByGalleryIdAndPhotoIds(String galleryId,List<String> photoIds);
	
	int deleteByGalleryId(String galleryId);
	
	int movePhotoGalleryPhotos(Map<String,Object> parameter);
}
