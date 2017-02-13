/**
 * 
 */
package com.haoyu.sip.cms.banner.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource; 

import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.google.common.collect.Maps;
import com.haoyu.sip.cms.banner.dao.IBannerDao;
import com.haoyu.sip.cms.banner.entity.Banner;
import com.haoyu.sip.cms.banner.service.IBannerService;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.file.entity.FileInfo;
import com.haoyu.sip.file.service.IFileService;
import com.haoyu.sip.utils.Identities;

/**
 * @author lianghuahuang
 *
 */
@Service
@CacheConfig(cacheNames="banners")  
public class BannerServiceImpl implements IBannerService {
	@Resource
	private IBannerDao bannerDao;
	
	@Resource
	private IFileService fileService;
	/* (non-Javadoc)
	 * @see com.haoyu.sip.cms.banner.service.IBannerService#findAll(java.util.Map)
	 */
	@Override
	@Cacheable(key="'rid_'+#parameter['relationId']",value="banners")
	public List<Banner> findBanners(Map<String, Object> parameter) {
		return bannerDao.findAll(parameter);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.cms.banner.service.IBannerService#findAll(java.util.Map, com.github.miemiedev.mybatis.paginator.domain.PageBounds)
	 */
	@Override
	public List<Banner> findBanners(Map<String, Object> parameter,
			PageBounds pageBounds) {
		return bannerDao.findAll(parameter, pageBounds);
	}


	/* (non-Javadoc)
	 * @see com.haoyu.sip.cms.banner.service.IBannerService#createBanner(com.haoyu.sip.cms.banner.entity.Banner)
	 */
	@Override
	@CacheEvict(key="'rid_'+#banner.relationId",value="banners")
	public Response createBanner(Banner banner) {
		if(banner==null){
			return Response.failInstance().responseMsg("createBanner fail!banner is null!");
		}
		if(StringUtils.isEmpty(banner.getId())){
			banner.setId(Identities.uuid2());
		}
		if(banner.getFileInfo()!=null&&StringUtils.isNotEmpty(banner.getFileInfo().getFileName())){
			fileService.createFile(banner.getFileInfo(), banner.getId(), "cms-banner");
			FileInfo fi = banner.getFileInfo();
			banner.setImageUrl(fi.getUrl());
		}
		int count = bannerDao.insertBanner(banner);
		if(count>0){
			
		}
		return count>0?Response.successInstance().responseData(banner):Response.failInstance().responseMsg("createBanner fail!");
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.cms.banner.service.IBannerService#updateBanner(com.haoyu.sip.cms.banner.entity.Banner)
	 */
	@Override
	@CacheEvict(key="'rid_'+#banner.relationId",value="banners")
	public Response updateBanner(Banner banner) {
		if(banner==null||StringUtils.isEmpty(banner.getId())){
			return Response.failInstance().responseMsg("updateBanner is fail!banner is null or banner's id is null");
		}
		if(banner.getFileInfo()!=null&&StringUtils.isNotEmpty(banner.getFileInfo().getFileName())){
			List<FileInfo> oldFileInfos = fileService.listFileInfoByRelationId(banner.getId());
			fileService.updateFile(banner.getFileInfo(), oldFileInfos!=null&&oldFileInfos.size()>0?oldFileInfos.get(0):null, banner.getId(), "cms-banner");
			FileInfo fi = banner.getFileInfo();
			banner.setImageUrl(fi.getUrl());
		}
		int count = bannerDao.updateBanner(banner);
		return count>0?Response.successInstance().responseData(banner):Response.failInstance().responseMsg("updateBanner fail!");
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.cms.banner.service.IBannerService#deleteBanner(com.haoyu.sip.cms.banner.entity.Banner)
	 */
	@Override
	@CacheEvict(key="'rid_'+#banner.relationId",value="banners")
	public Response deleteBanner(Banner banner) {
		if(banner==null||StringUtils.isEmpty(banner.getId())){
			return Response.failInstance().responseMsg("deleteBanner is fail!banner is null or banner's id is null");
		}
		int count = bannerDao.deleteBannerByLogic(banner);
		return count>0?Response.successInstance():Response.failInstance().responseMsg("deleteBanner fail!");
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.cms.banner.service.IBannerService#findBannerById(java.lang.String)
	 */
	@Override
	public Banner findBannerById(String id) {
		if(StringUtils.isEmpty(id)){
			return null;
		}
		return bannerDao.selectBannerById(id);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.cms.banner.service.IBannerService#findBanners(com.haoyu.sip.cms.banner.entity.Banner)
	 */
	@Override
	public List<Banner> findBanners(Banner banner) {
		Map<String,Object> parameter = Maps.newHashMap();
		if(banner!=null){
			if(StringUtils.isNotEmpty(banner.getRelationId())){
				parameter.put("relationId", banner.getRelationId());
			}
			if(StringUtils.isNotEmpty(banner.getTitle())){
				parameter.put("title", banner.getTitle());
			}
			if(StringUtils.isNotEmpty(banner.getArticleLink())){
				parameter.put("articleLink", banner.getArticleLink());
			}
		}
		return bannerDao.findAll(parameter);
	}

	@Override
	public Response createBanners(List<Banner> banners) {
		if(banners==null||banners.isEmpty()){
			return Response.failInstance().responseMsg("createBanners fail!banners is null!");
		}
		int count = 0;
		Map<String,Object> parameter = Maps.newHashMap();
		parameter.put("relationId", banners.get(0).getRelationId());
		List<Banner> bannerList  =bannerDao.findAll(parameter);
		int orderNo = bannerList.size();
		for(Banner banner:banners){
			if(StringUtils.isEmpty(banner.getId())){
				banner.setId(Identities.uuid2());
			}
			if(banner.getFileInfo()!=null&&StringUtils.isNotEmpty(banner.getFileInfo().getFileName())){
				fileService.createFile(banner.getFileInfo(), banner.getId(), "cms-banner");
				FileInfo fi = banner.getFileInfo();
				banner.setImageUrl(fi.getUrl());
			}
			banner.setOrderNo(orderNo);
			count+= bannerDao.insertBanner(banner);
			orderNo++;
		}
		return count>0?Response.successInstance().responseData(banners):Response.failInstance().responseMsg("createBanners fail!");
	}

	@Override
	public Response updateBanners(List<Banner> banners) {
		if(banners==null||banners.isEmpty()){
			return Response.failInstance().responseMsg("updateBanners fail!banners is null!");
		}
		int count = 0;
		for(Banner banner:banners){
			if(StringUtils.isEmpty(banner.getId())){
				continue;
			}
			count+= bannerDao.updateBanner(banner);
		}
		return count>0?Response.successInstance().responseData(banners):Response.failInstance().responseMsg("updateBanners fail!");
	}

}
