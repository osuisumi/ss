/**
 * 
 */
package com.haoyu.sip.gallery.dao;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.sip.gallery.entity.Photo;

/**
 * @author lianghuahuang
 *
 */
public interface IPhotoDao {
	
	Photo selectPhotoById(String id);
	
	int insertPhoto(Photo photo);

	int updatePhoto(Photo photo);
	
	int deletePhotoByLogic(Photo photo);
	
	int deletePhotosByLogic(List<String> ids);

	int deletePhotoByPhysics(String id);
	
	List<Photo> findAll(Map<String, Object> parameter, PageBounds pageBounds);
}
