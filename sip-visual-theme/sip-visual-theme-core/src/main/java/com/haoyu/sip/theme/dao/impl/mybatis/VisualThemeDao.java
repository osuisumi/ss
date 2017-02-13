/**
 * 
 */
package com.haoyu.sip.theme.dao.impl.mybatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.sip.core.jdbc.MybatisDao;
import com.haoyu.sip.theme.dao.IVisualThemeDao;
import com.haoyu.sip.theme.entity.VisualTheme;

/**
 * @author lianghuahuang
 *
 */
@Repository
public class VisualThemeDao extends MybatisDao implements IVisualThemeDao {

	/* (non-Javadoc)
	 * @see com.haoyu.sip.theme.dao.IVisualThemeDao#selectVisualThemeById(java.lang.String)
	 */
	@Override
	public VisualTheme selectVisualThemeById(String id) {
		return super.selectByPrimaryKey(id);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.theme.dao.IVisualThemeDao#insertVisualTheme(com.haoyu.sip.theme.entity.VisualTheme)
	 */
	@Override
	public int insertVisualTheme(VisualTheme visualTheme) {
		visualTheme.setDefaultValue();
		return super.insert(visualTheme);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.theme.dao.IVisualThemeDao#updateVisualTheme(com.haoyu.sip.theme.entity.VisualTheme)
	 */
	@Override
	public int updateVisualTheme(VisualTheme visualTheme) {
		visualTheme.setUpdateValue();
		return super.update(visualTheme);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.theme.dao.IVisualThemeDao#deleteVisualThemeByLogic(com.haoyu.sip.theme.entity.VisualTheme)
	 */
	@Override
	public int deleteVisualThemeByLogic(VisualTheme visualTheme) {
		visualTheme.setUpdateValue();
		return super.deleteByLogic(visualTheme);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.theme.dao.IVisualThemeDao#deleteVisualThemeByPhysics(java.lang.String)
	 */
	@Override
	public int deleteVisualThemeByPhysics(String id) {
		return super.deleteByPhysics(id);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.theme.dao.IVisualThemeDao#findAll(java.util.Map)
	 */
	@Override
	public List<VisualTheme> findAll(Map<String, Object> parameter) {
		return super.selectList("selectByParameter", parameter);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.theme.dao.IVisualThemeDao#findAll(java.util.Map, com.github.miemiedev.mybatis.paginator.domain.PageBounds)
	 */
	@Override
	public List<VisualTheme> findAll(Map<String, Object> parameter,
			PageBounds pageBounds) {
		return super.selectList("selectByParameter", parameter,pageBounds);
	}

}
