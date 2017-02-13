/**
 * 
 */
package com.haoyu.sip.cms.siteinfo.service.impl;

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
import com.haoyu.sip.cms.siteinfo.dao.ISiteInfoDao;
import com.haoyu.sip.cms.siteinfo.entity.SiteInfo;
import com.haoyu.sip.cms.siteinfo.service.ISiteInfoService;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.file.entity.FileInfo;
import com.haoyu.sip.file.service.IFileService;
import com.haoyu.sip.utils.Identities;

/**
 * @author lianghuahuang
 *
 */
@Service
public class SiteInfoServiceImpl implements ISiteInfoService {
	@Resource 
	private ISiteInfoDao siteInfoDao;
	@Resource
	private IFileService fileService;
	/* (non-Javadoc)
	 * @see com.haoyu.sip.cms.service.ICmsContentService#findAll(java.util.Map)
	 */
	@Override
	@Cacheable(key="#parameter['domain']",value="siteInfos")
	public List<SiteInfo> findSiteInfos(Map<String, Object> parameter) {
		return siteInfoDao.findAll(parameter);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.cms.service.ICmsContentService#findAll(java.util.Map, com.github.miemiedev.mybatis.paginator.domain.PageBounds)
	 */
	@Override
	public List<SiteInfo> findSiteInfos(Map<String, Object> parameter,
			PageBounds pageBounds) {
		return siteInfoDao.findAll(parameter, pageBounds);
	}


	/* (non-Javadoc)
	 * @see com.haoyu.sip.cms.siteinfo.service.ISiteInfoService#createSiteInfo(com.haoyu.sip.cms.siteinfo.entity.SiteInfo)
	 */
	@Override
	@CacheEvict(value="siteInfos",allEntries=true)
	public Response createSiteInfo(SiteInfo siteInfo) {
		if(siteInfo==null){
			return Response.failInstance().responseMsg("createSiteInfo fail!siteInfo is null!");
		}
		if(StringUtils.isEmpty(siteInfo.getId())){
			siteInfo.setId(Identities.uuid2());
		}
		if(siteInfo.getFileInfo()!=null){
			fileService.createFile(siteInfo.getFileInfo(), siteInfo.getId(), "cms-siteinfo-weixinqrcode");
			FileInfo fi = siteInfo.getFileInfo();
			siteInfo.setWeixinQrcode(fi.getUrl());
		}
		int count = siteInfoDao.insertSiteInfo(siteInfo);
		return count>0?Response.successInstance().responseData(siteInfo):Response.failInstance().responseMsg("createSiteInfo fail!");
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.cms.siteinfo.service.ISiteInfoService#updateSiteInfo(com.haoyu.sip.cms.siteinfo.entity.SiteInfo)
	 */
	@Override
	@CacheEvict(value="siteInfos",allEntries=true)
	public Response updateSiteInfo(SiteInfo siteInfo) {
		if(siteInfo==null||StringUtils.isEmpty(siteInfo.getId())){
			return Response.failInstance().responseMsg("updateSiteInfo is fail!siteInfo is null or siteInfo's id is null");
		}
		if(siteInfo.getFileInfo()!=null){
			List<FileInfo> oldFileInfos = fileService.listFileInfoByRelationId(siteInfo.getId());
			fileService.updateFile(siteInfo.getFileInfo(), oldFileInfos!=null&&!oldFileInfos.isEmpty()?oldFileInfos.get(0):null, siteInfo.getId(), "cms-siteinfo-weixinqrcode");
			fileService.createFile(siteInfo.getFileInfo(), siteInfo.getId(), "cms-siteinfo-weixinqrcode");
			FileInfo fi = siteInfo.getFileInfo();
			siteInfo.setWeixinQrcode(fi.getUrl());
		}
		int count = siteInfoDao.updateSiteInfo(siteInfo);
		return count>0?Response.successInstance().responseData(siteInfo):Response.failInstance().responseMsg("updateSiteInfo fail!");
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.cms.siteinfo.service.ISiteInfoService#deleteSiteInfo(com.haoyu.sip.cms.siteinfo.entity.SiteInfo)
	 */
	@Override
	@CacheEvict(value="siteInfos",allEntries=true)
	public Response deleteSiteInfo(SiteInfo siteInfo) {
		if(siteInfo==null||StringUtils.isEmpty(siteInfo.getId())){
			return Response.failInstance().responseMsg("deleteSiteInfo is fail!siteInfo is null or siteInfo's id is null");
		}
		int count = siteInfoDao.deleteSiteInfoByLogic(siteInfo);
		return count>0?Response.successInstance():Response.failInstance().responseMsg("deleteSiteInfo fail!");
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.cms.siteinfo.service.ISiteInfoService#findSiteInfoById(java.lang.String)
	 */
	@Override
	public SiteInfo findSiteInfoById(String id) {
		if(StringUtils.isEmpty(id)){
			return null;
		}
		return siteInfoDao.selectSiteInfoById(id);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.cms.siteinfo.service.ISiteInfoService#findSiteInfos(com.haoyu.sip.cms.siteinfo.entity.SiteInfo)
	 */
	@Override
	public List<SiteInfo> findSiteInfos(SiteInfo siteInfo) {
		Map<String,Object> parameter = Maps.newHashMap();
		if(siteInfo!=null){
			if(StringUtils.isNotEmpty(siteInfo.getDomain())){
				parameter.put("domain", siteInfo.getDomain());
			}
			if(StringUtils.isNotEmpty(siteInfo.getIcp())){
				parameter.put("title", siteInfo.getIcp());
			}
		}
		return siteInfoDao.findAll(parameter);
	}

	@Override
	@Cacheable(key="#domain",value="siteInfos")
	public SiteInfo findSiteInfoByDomain(String domain) {
		return siteInfoDao.selectSiteInfoByDomain(domain);
	}

	@Override
	@Cacheable(key="'mf_'+#domain",value="siteInfos")
	public String findMappingFolderByDomain(String domain) {
		if(StringUtils.isNotEmpty(domain)){
			Map<String,Object> parameter = Maps.newHashMap();
			parameter.put("domain", domain);
			List<SiteInfo> sis = siteInfoDao.findAll(parameter);
			if(sis!=null&&!sis.isEmpty()){
				String mappingFolder = sis.get(0).getMappingFolder();
				return mappingFolder!=null?mappingFolder:"defaultHost";
			}
		}
		return "defaultHost";
	}

}
