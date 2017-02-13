/**
 * 
 */
package com.haoyu.sip.cms.links.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.google.common.collect.Maps;
import com.haoyu.sip.cms.links.dao.ILinksDao;
import com.haoyu.sip.cms.links.entity.Links;
import com.haoyu.sip.cms.links.service.ILinksService;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.utils.Identities;

/**
 * @author lianghuahuang
 *
 */
@Service
public class LinksServiceImpl implements ILinksService {
	@Resource
	private ILinksDao linksDao;
	/* (non-Javadoc)
	 * @see com.haoyu.sip.cms.links.service.ILinksService#findAll(java.util.Map)
	 */
	@Override
	public List<Links> findLinks(Map<String, Object> parameter) {
		return linksDao.findAll(parameter);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.cms.links.service.ILinksService#findAll(java.util.Map, com.github.miemiedev.mybatis.paginator.domain.PageBounds)
	 */
	@Override
	public List<Links> findLinks(Map<String, Object> parameter,
			PageBounds pageBounds) {
		return linksDao.findAll(parameter, pageBounds);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.cms.links.service.ILinksService#createLinks(com.haoyu.sip.cms.links.entity.Links)
	 */
	@Override
	public Response createLinks(Links links) {
		if(links==null){
			return Response.failInstance().responseMsg("createLinks fail!links is null!");
		}
		if(StringUtils.isEmpty(links.getId())){
			links.setId(Identities.uuid2());
		}
		int count = linksDao.insertLinks(links);
		return count>0?Response.successInstance().responseData(links):Response.failInstance().responseMsg("createLinks fail!");
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.cms.links.service.ILinksService#updateLinks(com.haoyu.sip.cms.links.entity.Links)
	 */
	@Override
	public Response updateLinks(Links links) {
		if(links==null||StringUtils.isEmpty(links.getId())){
			return Response.failInstance().responseMsg("updateLinks is fail!links is null or links's id is null");
		}
		int count = linksDao.updateLinks(links);
		return count>0?Response.successInstance().responseData(links):Response.failInstance().responseMsg("updateLinks fail!");
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.cms.links.service.ILinksService#deleteLinks(com.haoyu.sip.cms.links.entity.Links)
	 */
	@Override
	public Response deleteLinks(Links links) {
		if(links==null||StringUtils.isEmpty(links.getId())){
			return Response.failInstance().responseMsg("deleteLinks is fail!links is null or links's id is null");
		}
		int count = linksDao.deleteLinksByLogic(links);
		return count>0?Response.successInstance():Response.failInstance().responseMsg("deleteLinks fail!");
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.cms.links.service.ILinksService#findLinksById(java.lang.String)
	 */
	@Override
	public Links findLinksById(String id) {
		if(StringUtils.isEmpty(id)){
			return null;
		}
		return linksDao.selectLinksById(id);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.cms.links.service.ILinksService#findLinkss(com.haoyu.sip.cms.links.entity.Links)
	 */
	@Override
	public List<Links> findLinks(Links links) {
		Map<String,Object> parameter = Maps.newHashMap();
		if(links!=null){
			if(links.getLinksType()!=null&&links.getLinksType().getId()!=null){
				parameter.put("linksTypeId", links.getLinksType().getId());
			}
			if(StringUtils.isNotEmpty(links.getTitle())){
				parameter.put("title", links.getTitle());
			}
		}
		return linksDao.findAll(parameter); 
	}

}
