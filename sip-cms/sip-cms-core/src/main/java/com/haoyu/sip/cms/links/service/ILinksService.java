/**
 * 
 */
package com.haoyu.sip.cms.links.service;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.sip.cms.links.entity.Links;
import com.haoyu.sip.cms.links.entity.LinksType;
import com.haoyu.sip.core.service.Response;

/**
 * @author lianghuahuang
 *
 */
public interface ILinksService{
	
	Response createLinks(Links links);
	
	Response updateLinks(Links links);
	
	Response deleteLinks(Links links);
	
	Links findLinksById(String id);
	
	List<Links> findLinks(Links links);
	
	List<Links> findLinks(Map<String,Object> parameter);
	
	List<Links> findLinks(Map<String,Object> parameter,PageBounds pageBounds);
}
