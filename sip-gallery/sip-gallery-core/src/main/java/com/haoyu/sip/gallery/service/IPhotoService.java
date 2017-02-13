/**
 * 
 */
package com.haoyu.sip.gallery.service;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.gallery.entity.Photo;

/**
 * @author lianghuahuang
 *
 */
public interface IPhotoService {
	
	Response createPhoto(Photo photo);
	
	Response updatePhoto(Photo photo);
	
	Response deletePhoto(Photo photo);
	
	Photo findPhotoById(String id); 
	
	List<Photo> findPhotoByGallery(String  galleryId,PageBounds pageBounds);
	
	List<Photo> findPhotoByParameter(Map<String,Object> parameter,PageBounds pageBounds);
}
