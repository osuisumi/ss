/**
 * 
 */
package com.haoyu.sip.theme.dao.impl.mybatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.sip.core.jdbc.MybatisDao;
import com.haoyu.sip.theme.dao.IVisualThemeSetDao;
import com.haoyu.sip.theme.entity.VisualThemeSet;

/**
 * @author lianghuahuang
 *
 */
@Repository
public class VisualThemeSetDao extends MybatisDao implements IVisualThemeSetDao {

	/* (non-Javadoc)
	 * @see com.haoyu.sip.theme.dao.IVisualThemeSetDao#selectVisualThemeSetById(java.lang.String)
	 */
	@Override
	public VisualThemeSet selectVisualThemeSetById(String id) {
		return super.selectByPrimaryKey(id);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.theme.dao.IVisualThemeSetDao#insertVisualThemeSet(com.haoyu.sip.theme.entity.VisualThemeSet)
	 */
	@Override
	public int insertVisualThemeSet(VisualThemeSet visualThemeSet) {
		visualThemeSet.setDefaultValue();
		return super.insert(visualThemeSet);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.theme.dao.IVisualThemeSetDao#updateVisualThemeSet(com.haoyu.sip.theme.entity.VisualThemeSet)
	 */
	@Override
	public int updateVisualThemeSet(VisualThemeSet visualThemeSet) {
		visualThemeSet.setUpdateValue();
		return super.update(visualThemeSet);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.theme.dao.IVisualThemeSetDao#deleteVisualThemeSetByLogic(com.haoyu.sip.theme.entity.VisualThemeSet)
	 */
	@Override
	public int deleteVisualThemeSetByLogic(VisualThemeSet visualThemeSet) {
		visualThemeSet.setUpdateValue();
		return super.deleteByLogic(visualThemeSet);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.theme.dao.IVisualThemeSetDao#deleteVisualThemeSetByPhysics(java.lang.String)
	 */
	@Override
	public int deleteVisualThemeSetByPhysics(String id) {
		return super.deleteByPhysics(id);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.theme.dao.IVisualThemeSetDao#findAll(java.util.Map)
	 */
	@Override
	public List<VisualThemeSet> findAll(Map<String, Object> parameter) {
		return super.selectList("selectByParameter", parameter);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.theme.dao.IVisualThemeSetDao#findAll(java.util.Map, com.github.miemiedev.mybatis.paginator.domain.PageBounds)
	 */
	@Override
	public List<VisualThemeSet> findAll(Map<String, Object> parameter,
			PageBounds pageBounds) {
		return super.selectList("selectByParameter", parameter,pageBounds);
	}

}
