/**
 * 
 */
package com.haoyu.sip.gallery.service;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.gallery.entity.Photo;
import com.haoyu.sip.gallery.entity.PhotoGallery;
import com.haoyu.sip.gallery.entity.PhotoGalleryPhoto;

/**
 * @author lianghuahuang
 *
 */
public interface IPhotoGalleryService {
	
	Response createPhotoGallery(PhotoGallery gallery);
	
	Response addPhotosToGallery(String id,List<Photo> photos);
	
	Response updatePhotosToGallery(String id,List<Photo> photos);
	
	Response removePhotosFromGallery(String id,List<Photo> photos);
	
	Response updatePhotoGallery(PhotoGallery gallery);
	
	Response movePhotosToAnotherGallery(String from,String to,List<String> photoIds);
	
	Response deletePhotosFromGallery(String galleryId,List<String> photoIds);
	
	void updatePhotoNum(PhotoGallery gallery);
	
	Response deletePhotoGallery(PhotoGallery gallery);
	
	PhotoGallery findPhotoGalleryById(String id);
	
	List<PhotoGallery> findPhotoGalleries(PhotoGallery gallery,PageBounds pageBounds);
	
	List<PhotoGallery> findPhotoGalleries(Map<String,Object> parameter,PageBounds pageBounds);
}
