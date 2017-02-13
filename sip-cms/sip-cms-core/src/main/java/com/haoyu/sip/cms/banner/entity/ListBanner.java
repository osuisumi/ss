/**
 * 
 */
package com.haoyu.sip.cms.banner.entity;

import java.util.List;

import com.google.common.collect.Lists;
import com.haoyu.sip.file.entity.FileInfo;

/**
 * @author lianghuahuang
 *
 */
public class ListBanner {
	private List<Banner> banners;
	
	private List<FileInfo> fileInfos;

	/**
	 * @return the banners
	 */
	public List<Banner> getBanners() {
		return banners;
	}

	/**
	 * @param banners the banners to set
	 */
	public void setBanners(List<Banner> banners) {
		this.banners = banners;
	}

	/**
	 * @return the fileInfos
	 */
	public List<FileInfo> getFileInfos() {
		return fileInfos;
	}

	/**
	 * @param fileInfos the fileInfos to set
	 */
	public void setFileInfos(List<FileInfo> fileInfos) {
		this.fileInfos = fileInfos;
	}
	
	public List<Banner> fileInfosToBanners(String relationId){
		if(fileInfos!=null&&!fileInfos.isEmpty()){
			banners = Lists.newArrayList();
			for(FileInfo fileInfo:fileInfos){
				Banner banner = new Banner();
				banner.setRelationId(relationId);
				banner.setFileInfo(fileInfo);
				banners.add(banner);
			}
		}
		return banners;
	}
	
}
