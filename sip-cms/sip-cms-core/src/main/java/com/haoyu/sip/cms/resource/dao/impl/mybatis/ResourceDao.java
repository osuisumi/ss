/**
 * 
 */
package com.haoyu.sip.cms.resource.dao.impl.mybatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.sip.cms.resource.dao.IResourceDao;
import com.haoyu.sip.cms.resource.entity.Resource;
import com.haoyu.sip.core.jdbc.MybatisDao;

/**
 * @author lianghuahuang
 *
 */
@Repository
public class ResourceDao extends MybatisDao implements IResourceDao {

	/* (non-Javadoc)
	 * @see com.haoyu.sip.cms.resource.dao.IResourceDao#selectResourceById(java.lang.String)
	 */
	@Override
	public Resource selectResourceById(String id) {
		return super.selectByPrimaryKey(id);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.cms.resource.dao.IResourceDao#insertResource(com.haoyu.sip.cms.resource.entity.Resource)
	 */
	@Override
	public int insertResource(Resource resource) {
		resource.setDefaultValue();
		return super.insert(resource);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.cms.resource.dao.IResourceDao#updateResource(com.haoyu.sip.cms.resource.entity.Resource)
	 */
	@Override
	public int updateResource(Resource resource) {
		resource.setUpdateValue();
		return super.update(resource);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.cms.resource.dao.IResourceDao#deleteResourceByLogic(com.haoyu.sip.cms.resource.entity.Resource)
	 */
	@Override
	public int deleteResourceByLogic(Resource resource) {
		resource.setUpdateValue();
		return super.deleteByLogic(resource);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.cms.resource.dao.IResourceDao#deleteResourceByPhysics(java.lang.String)
	 */
	@Override
	public int deleteResourceByPhysics(String id) {
		return super.deleteByPhysics(id);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.cms.resource.dao.IResourceDao#findAll(java.util.Map)
	 */
	@Override
	public List<Resource> findAll(Map<String, Object> parameter) {
		return super.selectList("selectByParameter", parameter);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.cms.resource.dao.IResourceDao#findAll(java.util.Map, com.github.miemiedev.mybatis.paginator.domain.PageBounds)
	 */
	@Override
	public List<Resource> findAll(Map<String, Object> parameter,
			PageBounds pageBounds) {
		return super.selectList("selectByParameter", parameter,pageBounds);
	}

}
