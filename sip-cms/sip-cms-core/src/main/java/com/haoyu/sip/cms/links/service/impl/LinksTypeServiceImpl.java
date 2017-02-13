/**
 * 
 */
package com.haoyu.sip.cms.links.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.google.common.collect.Maps;
import com.haoyu.sip.cms.links.dao.ILinksTypeDao;
import com.haoyu.sip.cms.links.entity.LinksType;
import com.haoyu.sip.cms.links.service.ILinksTypeService;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.utils.Identities;

/**
 * @author lianghuahuang
 *
 */
@Service
public class LinksTypeServiceImpl implements ILinksTypeService {
	@Resource
	private ILinksTypeDao linksTypeDao; 
	/* (non-Javadoc)
	 * @see com.haoyu.sip.cms.service.ICmsContentService#findAll(java.util.Map)
	 */
	@Override
	public List<LinksType> findLinksTypes(Map<String, Object> parameter) {
		return linksTypeDao.findAll(parameter);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.cms.service.ICmsContentService#findAll(java.util.Map, com.github.miemiedev.mybatis.paginator.domain.PageBounds)
	 */
	@Override
	public List<LinksType> findLinksTypes(Map<String, Object> parameter,
			PageBounds pageBounds) {
		return linksTypeDao.findAll(parameter, pageBounds);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.cms.links.service.ILinksTypeService#createLinksType(com.haoyu.sip.cms.links.entity.LinksType)
	 */
	@Override
	public Response createLinksType(LinksType linksType) {
		if(linksType==null){
			return Response.failInstance().responseMsg("createLinksType fail!linksType is null!");
		}
		if(StringUtils.isEmpty(linksType.getId())){
			linksType.setId(Identities.uuid2());
		}
		int count = linksTypeDao.insertLinksType(linksType);
		return count>0?Response.successInstance().responseData(linksType):Response.failInstance().responseMsg("createLinksType fail!");
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.cms.links.service.ILinksTypeService#updateLinksType(com.haoyu.sip.cms.links.entity.LinksType)
	 */
	@Override
	public Response updateLinksType(LinksType linksType) {
		if(linksType==null||StringUtils.isEmpty(linksType.getId())){
			return Response.failInstance().responseMsg("updateLinksType is fail!linksType is null or linksType's id is null");
		}
		int count = linksTypeDao.updateLinksType(linksType);
		return count>0?Response.successInstance().responseData(linksType):Response.failInstance().responseMsg("updateLinksType fail!");
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.cms.links.service.ILinksTypeService#deleteLinksType(com.haoyu.sip.cms.links.entity.LinksType)
	 */
	@Override
	public Response deleteLinksType(LinksType linksType) {
		if(linksType==null||StringUtils.isEmpty(linksType.getId())){
			return Response.failInstance().responseMsg("deleteLinksType is fail!linksType is null or linksType's id is null");
		}
		int count = linksTypeDao.deleteLinksTypeByLogic(linksType);
		return count>0?Response.successInstance():Response.failInstance().responseMsg("deleteLinksType fail!");
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.cms.links.service.ILinksTypeService#findLinksTypeById(java.lang.String)
	 */
	@Override
	public LinksType findLinksTypeById(String id) {
		if(StringUtils.isEmpty(id)){
			return null;
		}
		return linksTypeDao.selectLinksTypeById(id);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.cms.links.service.ILinksTypeService#findLinksTypes(com.haoyu.sip.cms.links.entity.LinksType)
	 */
	@Override
	public List<LinksType> findLinksTypes(LinksType linksType) {
		Map<String,Object> parameter = Maps.newHashMap();
		if(linksType!=null){
			if(StringUtils.isNotEmpty(linksType.getRelationId())){
				parameter.put("relationId", linksType.getRelationId());
			}
			if(StringUtils.isNotEmpty(linksType.getName())){
				parameter.put("name", linksType.getName());
			}
		}
		return linksTypeDao.findAll(parameter);
	}

	@Override
	@Cacheable(key="'rid_'+(#relationId==null?'':#relationId)",value="linksTypeAndLinks")
	public List<LinksType> findLinksTypeAndLinks(String relationId) {
		Map<String, Object> parameter = Maps.newHashMap();
		parameter.put("relationId", relationId);
		return linksTypeDao.findLinksTypeAndLinks(parameter);
	}

}
