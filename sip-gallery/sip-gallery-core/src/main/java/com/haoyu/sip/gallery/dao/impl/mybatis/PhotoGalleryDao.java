/**
 * 
 */
package com.haoyu.sip.gallery.dao.impl.mybatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.sip.core.jdbc.MybatisDao;
import com.haoyu.sip.gallery.dao.IPhotoGalleryDao;
import com.haoyu.sip.gallery.entity.PhotoGallery;

/**
 * @author lianghuahuang
 *
 */
@Repository
public class PhotoGalleryDao extends MybatisDao implements IPhotoGalleryDao {

	/* (non-Javadoc)
	 * @see com.haoyu.sip.gallery.dao.IPhotoGalleryDao#selectPhotoGalleryById(java.lang.String)
	 */
	@Override
	public PhotoGallery selectPhotoGalleryById(String id) {
		return super.selectByPrimaryKey(id);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.gallery.dao.IPhotoGalleryDao#insertPhotoGallery(com.haoyu.sip.gallery.entity.PhotoGallery)
	 */
	@Override
	public int insertPhotoGallery(PhotoGallery gallery) {
		gallery.setDefaultValue();
		return super.insert(gallery);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.gallery.dao.IPhotoGalleryDao#updatePhotoGallery(com.haoyu.sip.gallery.entity.PhotoGallery)
	 */
	@Override
	public int updatePhotoGallery(PhotoGallery gallery) {
		gallery.setUpdateValue();
		return super.update(gallery);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.gallery.dao.IPhotoGalleryDao#deletePhotoGalleryByLogic(com.haoyu.sip.gallery.entity.PhotoGallery)
	 */
	@Override
	public int deletePhotoGalleryByLogic(PhotoGallery gallery) {
		gallery.setUpdateValue();
		return super.deleteByLogic(gallery);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.gallery.dao.IPhotoGalleryDao#deletePhotoGalleryByPhysics(java.lang.String)
	 */
	@Override
	public int deletePhotoGalleryByPhysics(String id) {
		return super.deleteByPhysics(id);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.gallery.dao.IPhotoGalleryDao#updatePhotoNum(com.haoyu.sip.gallery.entity.PhotoGallery)
	 */
	@Override
	public int updatePhotoNum(PhotoGallery gallery) {
		gallery.setUpdateTime(System.currentTimeMillis());
		return super.update("updatePhotoNum", gallery);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.gallery.dao.IPhotoGalleryDao#findAll(java.util.Map, com.github.miemiedev.mybatis.paginator.domain.PageBounds)
	 */
	@Override
	public List<PhotoGallery> findAll(Map<String, Object> parameter,
			PageBounds pageBounds) {
		return super.selectList("selectByParameter", parameter,pageBounds);
	}

}
