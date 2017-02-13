/**
 * 
 */
package com.haoyu.sip.cms.banner.dao;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.sip.cms.banner.entity.Banner;

/**
 * @author lianghuahuang
 *
 */
public interface IBannerDao {
	
	Banner selectBannerById(String id);
	
	int insertBanner(Banner banner);
	
	int updateBanner(Banner banner);
	
	int deleteBannerByLogic(Banner banner);
	
	int deleteBannerByPhysics(String id);

	List<Banner> findAll(Map<String, Object> parameter);
	
	List<Banner> findAll(Map<String, Object> parameter, PageBounds pageBounds);
}
