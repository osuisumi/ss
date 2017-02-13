/**
 * 
 */
package com.haoyu.sip.cms.magazine.entity;

import org.apache.commons.lang3.StringUtils;

import com.haoyu.sip.cms.resource.entity.Resource;
import com.haoyu.sip.core.entity.BaseEntity;
import com.haoyu.sip.file.entity.FileInfo;
import com.haoyu.sip.gallery.entity.PhotoGallery;
import com.haoyu.sip.utils.Identities;

/**
 * @author lianghuahuang
 *
 */
public class Magazine extends BaseEntity {
	
	private String id;
	
	private String name;
	
	private String description;
	
	private String type;
	
	/**
	 * 包含封面图片和杂志内容图片的压缩文件：解压后结构为 frontCover图片和photos图片文件夹，其中photos图片文件夹下的图片按照数字0开始编号
	 */
	private FileInfo fileInfo;
	
	private PhotoGallery photoGallery;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public FileInfo getFileInfo() {
		return fileInfo;
	}

	public void setFileInfo(FileInfo fileInfo) {
		this.fileInfo = fileInfo;
	}
	
	public PhotoGallery getPhotoGallery(){
		if(photoGallery==null){
			photoGallery = new PhotoGallery();
			if(StringUtils.isEmpty(id))
				this.id = Identities.uuid2();		
			photoGallery.setId(id);
			photoGallery.setName(name);
			photoGallery.setDescription(description);
			return photoGallery;
		}
		return photoGallery;
	}
	
	public Resource getResource(){
		Resource rs = new Resource();
		if(StringUtils.isEmpty(id))
			this.id = Identities.uuid2();	
		rs.setId(id);
		rs.setName(name);
		rs.setSummary(description);
		return rs;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
