/**
 * 
 */
package com.haoyu.sip.cms.links.service;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.sip.cms.links.entity.LinksType;
import com.haoyu.sip.core.service.Response;

/**
 * @author lianghuahuang
 *
 */
public interface ILinksTypeService{
	
	Response createLinksType(LinksType linksType);
	
	Response updateLinksType(LinksType linksType);
	
	Response deleteLinksType(LinksType linksType);
	
	LinksType findLinksTypeById(String id);
	
	List<LinksType> findLinksTypes(LinksType linksType);
	
	List<LinksType> findLinksTypes(Map<String,Object> parameter);
	
	List<LinksType> findLinksTypes(Map<String,Object> parameter,PageBounds pageBounds);
	
	List<LinksType> findLinksTypeAndLinks(String relationId);
}
