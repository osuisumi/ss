/**
 * 
 */
package com.haoyu.sip.gallery.dao.impl.mybatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.google.common.collect.Maps;
import com.haoyu.sip.core.jdbc.MybatisDao;
import com.haoyu.sip.gallery.dao.IPhotoGalleryPhotoDao;
import com.haoyu.sip.gallery.entity.PhotoGallery;
import com.haoyu.sip.gallery.entity.PhotoGalleryPhoto;

/**
 * @author lianghuahuang
 *
 */
@Repository
public class PhotoGalleryPhotoDao extends MybatisDao implements
		IPhotoGalleryPhotoDao {

	/* (non-Javadoc)
	 * @see com.haoyu.sip.gallery.dao.IPhotoGalleryPhotoDao#insertPhotoGalleryPhoto(com.haoyu.sip.gallery.entity.PhotoGallery)
	 */
	@Override
	public int insertPhotoGalleryPhoto(PhotoGallery photoGallery) {
		Map<String,Object> parameter = Maps.newHashMap();
		photoGallery.setDefaultValue();
		parameter.put("photoGallery", photoGallery);
		parameter.put("uuid",super.getDbUuid());
		return super.insert("insertByPhotoGallery",parameter);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.gallery.dao.IPhotoGalleryPhotoDao#deletePhotoGalleryByLogic(com.haoyu.sip.gallery.entity.PhotoGallery)
	 */
	@Override
	public int deletePhotoGalleryByLogic(PhotoGallery photoGallery) {
		return super.deleteByLogic(photoGallery);
	}

	@Override
	public int insertPhotoGalleryPhoto(PhotoGalleryPhoto photoGalleryPhoto) {
		return super.insert(photoGalleryPhoto);
	}
	
	@Override
	public int movePhotoGalleryPhotos(Map<String, Object> parameter) {
		if((!parameter.containsKey("photoIds")) || (!parameter.containsKey("from")) ||(!parameter.containsKey("to"))){
			return -1;
		}
		return super.update("movePhotos", parameter);
	}

	@Override
	public int deleteByGalleryIdAndPhotoIds(String galleryId, List<String> photoIds) {
		Map<String,Object> parameter = Maps.newHashMap();
		parameter.put("galleryId", galleryId);
		parameter.put("photoIds",photoIds);
		return super.update("deleteByGalleryIdAndPhotoIds", parameter);
	}

	@Override
	public int deleteByGalleryId(String galleryId) {
		return super.delete("deleteByGalleryId", galleryId);
	}



}
