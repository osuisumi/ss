/**
 * 
 */
package com.haoyu.sip.cms.links.dao;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.sip.cms.links.entity.Links;

/**
 * @author lianghuahuang
 *
 */
public interface ILinksDao {
	Links selectLinksById(String id);
	
	int insertLinks(Links links);
	
	int updateLinks(Links links);
	
	int deleteLinksByLogic(Links links);
	
	int deleteLinksByPhysics(String id);

	List<Links> findAll(Map<String, Object> parameter);
	
	List<Links> findAll(Map<String, Object> parameter, PageBounds pageBounds);

}
