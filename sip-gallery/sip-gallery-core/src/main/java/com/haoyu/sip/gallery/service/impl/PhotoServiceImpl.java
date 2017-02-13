/**
 * 
 */
package com.haoyu.sip.gallery.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.google.common.collect.Maps;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.gallery.dao.IPhotoDao;
import com.haoyu.sip.gallery.entity.Photo;
import com.haoyu.sip.gallery.service.IPhotoService;

/**
 * @author lianghuahuang
 *
 */
@Service
public class PhotoServiceImpl implements IPhotoService {
	@Resource
	private IPhotoDao photoDao;
	/* (non-Javadoc)
	 * @see com.haoyu.sip.gallery.service.IPhotoService#createPhoto(com.haoyu.sip.gallery.entity.Photo)
	 */
	@Override
	public Response createPhoto(Photo photo) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.gallery.service.IPhotoService#updatePhoto(com.haoyu.sip.gallery.entity.Photo)
	 */
	@Override
	public Response updatePhoto(Photo photo) {
		return photoDao.updatePhoto(photo)>0?Response.successInstance():Response.failInstance();
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.gallery.service.IPhotoService#deletePhoto(com.haoyu.sip.gallery.entity.Photo)
	 */
	@Override
	public Response deletePhoto(Photo photo) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.gallery.service.IPhotoService#findPhotoById(java.lang.String)
	 */
	@Override
	public Photo findPhotoById(String id) {
		return photoDao.selectPhotoById(id);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.gallery.service.IPhotoService#findPhotoByGallery(java.lang.String, com.github.miemiedev.mybatis.paginator.domain.PageBounds)
	 */
	@Override
	public List<Photo> findPhotoByGallery(String galleryId,
			PageBounds pageBounds) {
		Map<String,Object> parameter = Maps.newHashMap();
		parameter.put("galleryId", galleryId);
		return photoDao.findAll(parameter, pageBounds);
	}

	@Override
	public List<Photo> findPhotoByParameter(Map<String, Object> parameter, PageBounds pageBounds) {
		return photoDao.findAll(parameter, pageBounds);
	}

}
