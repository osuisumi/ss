/**
 * 
 */
package com.haoyu.sip.cms.siteinfo.service;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.sip.cms.siteinfo.entity.SiteInfo;
import com.haoyu.sip.core.service.Response;

/**
 * @author lianghuahuang
 *
 */
public interface ISiteInfoService{
	
	Response createSiteInfo(SiteInfo siteinfo);
	
	Response updateSiteInfo(SiteInfo siteinfo);
	
	Response deleteSiteInfo(SiteInfo siteinfo);
	
	SiteInfo findSiteInfoById(String id);
	
	SiteInfo findSiteInfoByDomain(String domain);
	
	String findMappingFolderByDomain(String domain);
	
	List<SiteInfo> findSiteInfos(SiteInfo siteInfo);
	
	List<SiteInfo> findSiteInfos(Map<String,Object> parameter);
	
	List<SiteInfo> findSiteInfos(Map<String,Object> parameter,PageBounds pageBounds);
}
