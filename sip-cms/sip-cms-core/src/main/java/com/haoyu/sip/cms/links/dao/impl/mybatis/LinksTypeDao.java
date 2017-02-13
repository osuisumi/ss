/**
 * 
 */
package com.haoyu.sip.cms.links.dao.impl.mybatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.sip.cms.links.dao.ILinksTypeDao;
import com.haoyu.sip.cms.links.entity.Links;
import com.haoyu.sip.cms.links.entity.LinksType;
import com.haoyu.sip.core.jdbc.MybatisDao;

/**
 * @author Administrator
 *
 */
@Repository
public class LinksTypeDao extends MybatisDao implements ILinksTypeDao {

	/* (non-Javadoc)
	 * @see com.haoyu.sip.cms.links.dao.ILinksTypeDao#selectLinksTypeById(java.lang.String)
	 */
	@Override
	public LinksType selectLinksTypeById(String id) {
		return super.selectByPrimaryKey(id);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.cms.links.dao.ILinksTypeDao#insertLinksType(com.haoyu.sip.cms.links.entity.LinksType)
	 */
	@Override
	public int insertLinksType(LinksType linksType) {
		linksType.setDefaultValue();
		return super.insert(linksType);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.cms.links.dao.ILinksTypeDao#updateLinksType(com.haoyu.sip.cms.links.entity.LinksType)
	 */
	@Override
	public int updateLinksType(LinksType linksType) {
		linksType.setUpdateValue();
		return super.update(linksType);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.cms.links.dao.ILinksTypeDao#deleteLinksTypeByLogic(com.haoyu.sip.cms.links.entity.LinksType)
	 */
	@Override
	public int deleteLinksTypeByLogic(LinksType linksType) {
		linksType.setUpdateValue();
		return super.deleteByLogic(linksType);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.cms.links.dao.ILinksTypeDao#deleteLinksTypeByPhysics(java.lang.String)
	 */
	@Override
	public int deleteLinksTypeByPhysics(String id) {
		return super.deleteByPhysics(id);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.cms.links.dao.ILinksTypeDao#findAll(java.util.Map)
	 */
	@Override
	public List<LinksType> findAll(Map<String, Object> parameter) {
		return super.selectList("selectByParameter", parameter);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.cms.links.dao.ILinksTypeDao#findAll(java.util.Map, com.github.miemiedev.mybatis.paginator.domain.PageBounds)
	 */
	@Override
	public List<LinksType> findAll(Map<String, Object> parameter,
			PageBounds pageBounds) {
		return super.selectList("selectByParameter", parameter,pageBounds);
	}
	
	@Override
	public List<LinksType> findLinksTypeAndLinks(Map<String, Object> parameter) {
		return super.selectList("selectLinksTypeAndLinksByParameter", parameter);
	}

}
