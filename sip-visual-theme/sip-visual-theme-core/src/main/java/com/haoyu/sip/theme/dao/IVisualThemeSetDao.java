/**
 * 
 */
package com.haoyu.sip.theme.dao;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.sip.theme.entity.VisualThemeSet;

/**
 * @author lianghuahuang
 *
 */
public interface IVisualThemeSetDao {
	
	VisualThemeSet selectVisualThemeSetById(String id);
	
	int insertVisualThemeSet(VisualThemeSet visualThemeSet);
	
	int updateVisualThemeSet(VisualThemeSet visualThemeSet);
	
	int deleteVisualThemeSetByLogic(VisualThemeSet visualThemeSet);
	
	int deleteVisualThemeSetByPhysics(String id);

	List<VisualThemeSet> findAll(Map<String, Object> parameter);
	
	List<VisualThemeSet> findAll(Map<String, Object> parameter, PageBounds pageBounds);
}
