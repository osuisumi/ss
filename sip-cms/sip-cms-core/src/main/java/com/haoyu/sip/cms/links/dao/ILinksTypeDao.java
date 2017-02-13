/**
 * 
 */
package com.haoyu.sip.cms.links.dao;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.sip.cms.links.entity.Links;
import com.haoyu.sip.cms.links.entity.LinksType;

/**
 * @author lianghuahuang
 *
 */
public interface ILinksTypeDao {
	LinksType selectLinksTypeById(String id);
	
	int insertLinksType(LinksType linksType);
	
	int updateLinksType(LinksType linksType);
	
	int deleteLinksTypeByLogic(LinksType linksType);
	
	int deleteLinksTypeByPhysics(String id);

	List<LinksType> findAll(Map<String, Object> parameter);
	
	List<LinksType> findAll(Map<String, Object> parameter, PageBounds pageBounds);
	
	
	List<LinksType> findLinksTypeAndLinks(Map<String, Object> parameter);
}
