/**
 * 
 */
package com.haoyu.sip.theme.dao;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.sip.theme.entity.VisualTheme;

/**
 * @author lianghuahuang
 *
 */
public interface IVisualThemeDao {
	
	VisualTheme selectVisualThemeById(String id);
	
	int insertVisualTheme(VisualTheme visualTheme);
	
	int updateVisualTheme(VisualTheme	visualTheme);
	
	int deleteVisualThemeByLogic(VisualTheme visualTheme);
	
	int deleteVisualThemeByPhysics(String id);

	List<VisualTheme> findAll(Map<String, Object> parameter);
	
	List<VisualTheme> findAll(Map<String, Object> parameter, PageBounds pageBounds);
}
