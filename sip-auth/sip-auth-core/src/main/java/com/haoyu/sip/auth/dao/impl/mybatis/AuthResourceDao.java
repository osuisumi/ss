/**
 * 
 */
package com.haoyu.sip.auth.dao.impl.mybatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.sip.auth.dao.IAuthResourceDao;
import com.haoyu.sip.auth.entity.AuthResource;
import com.haoyu.sip.core.jdbc.MybatisDao;

/**
 * @author lianghuahuang
 *
 */
@Repository
public class AuthResourceDao extends MybatisDao implements IAuthResourceDao {

	/* (non-Javadoc)
	 * @see com.haoyu.sip.auth.dao.IResourceDao#selectResourceById(java.lang.String)
	 */
	@Override
	public AuthResource selectResourceById(String id) {
		return super.selectByPrimaryKey(id);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.auth.dao.IResourceDao#selectResourceForList(com.haoyu.sip.auth.entity.Resource, com.github.miemiedev.mybatis.paginator.domain.PageBounds)
	 */
	@Override
	public List<AuthResource> selectResourceForList(AuthResource resource,
			PageBounds pageBounds) {
		return super.selectList("selectByResource", resource, pageBounds);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.auth.dao.IResourceDao#selectResourceForMap(com.haoyu.sip.auth.entity.Resource, com.github.miemiedev.mybatis.paginator.domain.PageBounds)
	 */
	@Override
	public Map<String, AuthResource> selectResourceForMap(AuthResource resource,
			PageBounds pageBounds) {
		return super.selectMap("selectByResource", resource, "id", pageBounds);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.auth.dao.IResourceDao#insertResource(com.haoyu.sip.auth.entity.Resource)
	 */
	@Override
	public int insertResource(AuthResource resource) {
		resource.setDefaultValue();
		return super.insert(resource);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.auth.dao.IResourceDao#updateResource(com.haoyu.sip.auth.entity.Resource)
	 */
	@Override
	public int updateResource(AuthResource resource) {
		resource.setUpdateTime(System.currentTimeMillis());
		return super.update(resource);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.auth.dao.IResourceDao#deleteResourceByLogic(com.haoyu.sip.auth.entity.Resource)
	 */
	@Override
	public int deleteResourceByLogic(AuthResource resource) {
		resource.setUpdateTime(System.currentTimeMillis());
		return super.deleteByLogic(resource);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.auth.dao.IResourceDao#deleteResourceByPhysics(java.lang.String)
	 */
	@Override
	public int deleteResourceByPhysics(String id) {
		return super.deleteByPhysics(id);
	}

	@Override
	public int batchDeleteResourceByIds(List<String> ids) {
		return super.delete("batchDeleteResourceByIds", ids);
	}

}
