/**
 * 
 */
package com.haoyu.sip.gallery.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.file.entity.FileInfo;
import com.haoyu.sip.file.service.IFileService;
import com.haoyu.sip.gallery.dao.IPhotoDao;
import com.haoyu.sip.gallery.dao.IPhotoGalleryDao;
import com.haoyu.sip.gallery.dao.IPhotoGalleryPhotoDao;
import com.haoyu.sip.gallery.entity.Photo;
import com.haoyu.sip.gallery.entity.PhotoGallery;
import com.haoyu.sip.gallery.entity.PhotoGalleryPhoto;
import com.haoyu.sip.gallery.event.AddPhotosToGalleryEvent;
import com.haoyu.sip.gallery.service.IPhotoGalleryService;
import com.haoyu.sip.utils.Identities;

/**
 * @author lianghuahuang
 *
 */
@Service
public class PhotoGalleryServiceImpl implements IPhotoGalleryService {
	@Resource
	private IPhotoGalleryDao photoGalleryDao;
	
	@Resource
	private IPhotoDao photoDao;
	
	@Resource
	private IPhotoGalleryPhotoDao photoGalleryPhotoDao;
	
	@Resource
	private ApplicationContext applicationContext;
	
	@Resource
	private IFileService fileService;
	/* (non-Javadoc)
	 * @see com.haoyu.sip.gallery.service.IPhotoGalleryService#createPhotoGallery(com.haoyu.sip.gallery.entity.PhotoGallery)
	 */
	@Override
	public Response createPhotoGallery(PhotoGallery gallery) {
		if(gallery==null)
			return Response.failInstance().responseMsg("creat gallery is fail!gallery is null");
		if(gallery.getName()==null)
			return Response.failInstance().responseMsg("creat gallery is fail!gallery name is null");
		if(StringUtils.isEmpty(gallery.getId()))
			gallery.setId(Identities.uuid2());
		if(gallery.getFileInfo()!=null&&gallery.getFileInfo().getFileName()!=null){
			fileService.createFile(gallery.getFileInfo(), gallery.getId(), "photo-gallery");
			FileInfo fi = gallery.getFileInfo();
			gallery.setFrontCover(fi.getUrl());
		}
		int count = photoGalleryDao.insertPhotoGallery(gallery);
		if(count>0){
			return Response.successInstance().responseData(gallery).responseMsg("create gallery is success!");
		}
		return Response.failInstance().responseMsg("create gallery is fail!");
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.gallery.service.IPhotoGalleryService#updatePhotoGallery(com.haoyu.sip.gallery.entity.PhotoGallery)
	 */
	@Override
	public Response updatePhotoGallery(PhotoGallery gallery) {
		if(gallery==null||StringUtils.isEmpty(gallery.getId())){
			 return Response.failInstance().responseMsg("update gallery is fail!gallery id is null");
		}
		if(gallery.getFileInfo()!=null&&gallery.getFileInfo().getFileName()!=null){
			List<FileInfo> oldFileInfos = fileService.listFileInfoByRelationId(gallery.getId());
			fileService.updateFile(gallery.getFileInfo(), (oldFileInfos!=null&&oldFileInfos.size()>0)?oldFileInfos.get(0):null, gallery.getId(), "photo-gallery");
			//fileService.createFile(gallery.getFileInfo(), gallery.getId(), "photo-gallery");
			FileInfo fi = gallery.getFileInfo();
			gallery.setFrontCover(fi.getUrl());
		}
		int count = photoGalleryDao.updatePhotoGallery(gallery);
		if(count>0){
			return Response.successInstance().responseData(gallery).responseMsg("update gallery is success!");
		}
		return Response.failInstance().responseMsg("update gallery is fail!");
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.gallery.service.IPhotoGalleryService#updatePhotoNum(com.haoyu.sip.gallery.entity.PhotoGallery)
	 */
	@Override
	public void updatePhotoNum(PhotoGallery gallery) {
		if(gallery==null ||StringUtils.isEmpty(gallery.getId())){
			return;
		}
		PhotoGallery p = new PhotoGallery(gallery.getId());
		p.setPhotoNumber(1);
		updatePhotoGallery(p);
	}
	
	@Override
	public Response movePhotosToAnotherGallery(String from,String to,List<String> photoIds){
		if(CollectionUtils.isEmpty(photoIds)){
			return Response.failInstance().responseMsg("photoIds is null");
		}
		if(StringUtils.isAnyEmpty(from,to)){
			return Response.failInstance().responseMsg("from or to is null");
		}
		Map<String,Object> parameter = Maps.newHashMap();
		parameter.put("photoIds", photoIds);
		parameter.put("from",from);
		parameter.put("to",to);
		int count = photoGalleryPhotoDao.movePhotoGalleryPhotos(parameter);
		if(count>0){
			updatePhotoNum(new PhotoGallery(from));
			updatePhotoNum(new PhotoGallery(to));
			return Response.successInstance();
		}
		return Response.failInstance();
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.gallery.service.IPhotoGalleryService#deletePhotoGallery(com.haoyu.sip.gallery.entity.PhotoGallery)
	 */
	@Override
	public Response deletePhotoGallery(PhotoGallery gallery) {
		if(gallery==null||StringUtils.isEmpty(gallery.getId())){
			 return Response.failInstance().responseMsg("delete gallery is fail!gallery id is null");
		}
		int count = photoGalleryDao.deletePhotoGalleryByLogic(gallery);
		if(count>0){
			photoGalleryPhotoDao.deleteByGalleryId(gallery.getId());
			return Response.successInstance().responseData(gallery).responseMsg("delete gallery is success!");
		}
		return Response.failInstance().responseMsg("delete gallery is fail!");
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.gallery.service.IPhotoGalleryService#findPhotoGalleryById(java.lang.String)
	 */
	@Override
	public PhotoGallery findPhotoGalleryById(String id) {
		return photoGalleryDao.selectPhotoGalleryById(id);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.gallery.service.IPhotoGalleryService#findPhotoGalleries(com.haoyu.sip.gallery.entity.PhotoGallery, com.github.miemiedev.mybatis.paginator.domain.PageBounds)
	 */
	@Override
	public List<PhotoGallery> findPhotoGalleries(PhotoGallery gallery,
			PageBounds pageBounds) {
		Map<String, Object> parameter = Maps.newHashMap();
		if(gallery!=null){
			if(StringUtils.isNotEmpty(gallery.getState())){
				parameter.put("state", gallery.getState());
			}
			if(StringUtils.isNotEmpty(gallery.getName())){
				parameter.put("name", gallery.getName());
			}
			if(StringUtils.isNotEmpty(gallery.getId())){
				parameter.put("id", gallery.getId());
			}
		}
		return photoGalleryDao.findAll(parameter, pageBounds);
		
	}

	@Override
	public Response addPhotosToGallery(String id, List<Photo> photos) {
		if(StringUtils.isNotEmpty(id)&&photos!=null&&!photos.isEmpty()){
			List<Photo> newPhotos = Lists.newArrayList();
			for(Photo photo:photos){
				if(photo.getFileInfo()==null)
					continue;
				if(StringUtils.isEmpty(photo.getId())){
					photo.setId(Identities.uuid2());
				}
				if(photoDao.insertPhoto(photo)>0){
					Response response = fileService.createFile(photo.getFileInfo(), photo.getId(), "photo-gallery");
					if(response.isSuccess()){
						newPhotos.add(photo);
					}
				}
			}
			if(!newPhotos.isEmpty()){
				PhotoGallery photoGallery = photoGalleryDao.selectPhotoGalleryById(id);
				int photoNum = photoGallery.getPhotoNumber();
				for(int i=0;i<photos.size();i++){
					Photo photo = photos.get(i);
					photo.setOrderNo(photoNum+i);
					newPhotos.add(photo);
				}
				photoGallery.setPhotos(newPhotos);
				photoGalleryPhotoDao.insertPhotoGalleryPhoto(photoGallery);
				Map<String,Object> eventSource = Maps.newHashMap();
				eventSource.put("id", id);
				eventSource.put("photos",photos);
				applicationContext.publishEvent(new AddPhotosToGalleryEvent(eventSource));
				updatePhotoNum(new PhotoGallery(id));
				return Response.successInstance().responseMsg("addPhotosToGallery is success");
			}
		}
		return Response.failInstance();
	}

	@Override
	public Response removePhotosFromGallery(String id, List<Photo> photos) {
		if(StringUtils.isNotEmpty(id)&&photos!=null&&!photos.isEmpty()){
			List<String> deletedPhotoIds = Lists.newArrayList();
			for(Photo photo:photos){
				deletedPhotoIds.add(photo.getId());
			}
			if(!deletedPhotoIds.isEmpty()){
				int count = photoDao.deletePhotosByLogic(deletedPhotoIds);
				if(count>0){
					updatePhotoNum(new PhotoGallery(id));
					return photoGalleryPhotoDao.deleteByGalleryIdAndPhotoIds(id, deletedPhotoIds)>0?Response.successInstance():Response.failInstance();
				}
			}
		}
		return Response.failInstance();
	}

	@Override
	public List<PhotoGallery> findPhotoGalleries(Map<String, Object> parameter,
			PageBounds pageBounds) {
		return photoGalleryDao.findAll(parameter, pageBounds);
	}

	@Override
	public Response deletePhotosFromGallery(String galleryId, List<String> photoIds) {
		if(StringUtils.isNotEmpty(galleryId)&&(!CollectionUtils.isEmpty(photoIds))){
			int count = photoDao.deletePhotosByLogic(photoIds);
			if(count>0){
				updatePhotoNum(new PhotoGallery(galleryId));
				return photoGalleryPhotoDao.deleteByGalleryIdAndPhotoIds(galleryId, photoIds)>0?Response.successInstance():Response.failInstance();
			}
		}
		return Response.failInstance();
	}

	@Override
	public Response updatePhotosToGallery(String id, List<Photo> photos) {
		if(StringUtils.isNotEmpty(id)&&photos!=null&&!photos.isEmpty()){
			for(Photo photo:photos){
				photoDao.updatePhoto(photo);
			}
			return Response.successInstance().responseMsg("updatePhotosToGallery is success!");
		}
		return Response.failInstance().responseMsg("id is null or photos is null");
	}


}
