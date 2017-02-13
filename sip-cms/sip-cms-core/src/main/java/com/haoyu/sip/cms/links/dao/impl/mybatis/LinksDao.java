/**
 * 
 */
package com.haoyu.sip.cms.links.dao.impl.mybatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.sip.cms.links.dao.ILinksDao;
import com.haoyu.sip.cms.links.entity.Links;
import com.haoyu.sip.core.jdbc.MybatisDao;

/**
 * @author lianghuahuang
 *
 */
@Repository
public class LinksDao extends MybatisDao implements ILinksDao {

	/* (non-Javadoc)
	 * @see com.haoyu.sip.cms.links.dao.ILinksDao#selectLinksById(java.lang.String)
	 */
	@Override
	public Links selectLinksById(String id) {
		return super.selectByPrimaryKey(id);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.cms.links.dao.ILinksDao#insertLinks(com.haoyu.sip.cms.links.entity.Links)
	 */
	@Override
	public int insertLinks(Links links) {
		links.setDefaultValue();
		return super.insert(links);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.cms.links.dao.ILinksDao#updateLinks(com.haoyu.sip.cms.links.entity.Links)
	 */
	@Override
	public int updateLinks(Links links) {
		links.setUpdateValue();
		return super.update(links);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.cms.links.dao.ILinksDao#deleteLinksByLogic(com.haoyu.sip.cms.links.entity.Links)
	 */
	@Override
	public int deleteLinksByLogic(Links links) {
		links.setUpdateValue();
		return super.deleteByLogic(links);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.cms.links.dao.ILinksDao#deleteLinksByPhysics(java.lang.String)
	 */
	@Override
	public int deleteLinksByPhysics(String id) {
		return super.deleteByPhysics(id);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.cms.links.dao.ILinksDao#findAll(java.util.Map)
	 */
	@Override
	public List<Links> findAll(Map<String, Object> parameter) {
		return super.selectList("selectByParameter", parameter);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.cms.links.dao.ILinksDao#findAll(java.util.Map, com.github.miemiedev.mybatis.paginator.domain.PageBounds)
	 */
	@Override
	public List<Links> findAll(Map<String, Object> parameter,
			PageBounds pageBounds) {
		return super.selectList("selectByParameter", parameter,pageBounds);
	}



}
