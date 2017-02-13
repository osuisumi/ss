/**
 * 
 */
package com.haoyu.sip.gallery.dao;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.sip.gallery.entity.PhotoGallery;

/**
 * @author lianghuahuang
 *
 */
public interface IPhotoGalleryDao {

	PhotoGallery selectPhotoGalleryById(String id);
	
	int insertPhotoGallery(PhotoGallery gallery);

	int updatePhotoGallery(PhotoGallery gallery);

	int deletePhotoGalleryByLogic(PhotoGallery gallery);

	int deletePhotoGalleryByPhysics(String id);

	int updatePhotoNum(PhotoGallery gallery);
	
	List<PhotoGallery> findAll(Map<String, Object> parameter, PageBounds pageBounds);
}
