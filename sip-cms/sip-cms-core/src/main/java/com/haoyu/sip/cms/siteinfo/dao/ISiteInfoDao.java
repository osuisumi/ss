/**
 * 
 */
package com.haoyu.sip.cms.siteinfo.dao;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.sip.cms.siteinfo.entity.SiteInfo;

/**
 * @author lianghuahuang
 *
 */
public interface ISiteInfoDao {
	SiteInfo selectSiteInfoById(String id);
	
	SiteInfo selectSiteInfoByDomain(String domain);
	
	int insertSiteInfo(SiteInfo siteInfo);
	
	int updateSiteInfo(SiteInfo siteInfo);
	
	int deleteSiteInfoByLogic(SiteInfo siteInfo);
	
	int deleteSiteInfoByPhysics(String id);

	List<SiteInfo> findAll(Map<String, Object> parameter);
	
	List<SiteInfo> findAll(Map<String, Object> parameter, PageBounds pageBounds);
}
