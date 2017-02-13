/**
 * 
 */
package com.haoyu.sip.gallery.dao.impl.mybatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.sip.core.jdbc.MybatisDao;
import com.haoyu.sip.gallery.dao.IPhotoDao;
import com.haoyu.sip.gallery.entity.Photo;

/**
 * @author lianghuahuang
 *
 */
@Repository
public class PhotoDao extends MybatisDao implements IPhotoDao {

	/* (non-Javadoc)
	 * @see com.haoyu.sip.gallery.dao.IPhotoDao#selectPhotoById(java.lang.String)
	 */
	@Override
	public Photo selectPhotoById(String id) {
		return super.selectByPrimaryKey(id);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.gallery.dao.IPhotoDao#insertPhoto(com.haoyu.sip.gallery.entity.Photo)
	 */
	@Override
	public int insertPhoto(Photo photo) {
		photo.setDefaultValue();
		return super.insert(photo);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.gallery.dao.IPhotoDao#updatePhoto(com.haoyu.sip.gallery.entity.Photo)
	 */
	@Override
	public int updatePhoto(Photo photo) {
		photo.setUpdateValue();
		return super.update(photo);
	}
	
	/* (non-Javadoc)
	 * @see com.haoyu.sip.gallery.dao.IPhotoDao#deletePhotoByLogic(com.haoyu.sip.gallery.entity.Photo)
	 */
	@Override
	public int deletePhotoByLogic(Photo photo) {
		photo.setUpdateValue();
		return super.deleteByLogic(photo);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.gallery.dao.IPhotoDao#deletePhotoByPhysics(java.lang.String)
	 */
	@Override
	public int deletePhotoByPhysics(String id) {
		return super.deleteByPhysics(id);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.gallery.dao.IPhotoDao#findAll(java.util.Map, com.github.miemiedev.mybatis.paginator.domain.PageBounds)
	 */
	@Override
	public List<Photo> findAll(Map<String, Object> parameter,
			PageBounds pageBounds) {
		return super.selectList("selectByParameter", parameter,pageBounds);
	}

	@Override
	public int deletePhotosByLogic(List<String> ids) {
		return super.update("deletePhotosByLogic", ids);
	}


}
