/**
 * 
 */
package com.haoyu.sip.cms.banner.service;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.sip.cms.banner.entity.Banner;
import com.haoyu.sip.core.service.Response;

/**
 * @author lianghuahuang
 *
 */
public interface IBannerService{
	
	Response createBanner(Banner banner);
	
	Response createBanners(List<Banner> banners);
	
	Response updateBanner(Banner banner);
	
	Response deleteBanner(Banner banner);
	
	Banner findBannerById(String id);
	
	List<Banner> findBanners(Banner banner);
	
	List<Banner> findBanners(Map<String,Object> parameter);
	
	List<Banner> findBanners(Map<String,Object> parameter,PageBounds pageBounds);
	
	Response updateBanners(List<Banner> banners);
}
