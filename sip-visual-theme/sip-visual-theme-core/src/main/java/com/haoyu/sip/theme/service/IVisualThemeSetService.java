/**
 * 
 */
package com.haoyu.sip.theme.service;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.theme.entity.VisualThemeSet;

/**
 * @author lianghuahuang
 *
 */
public interface IVisualThemeSetService {
	
	Response createVisualThemeSet(VisualThemeSet visualTheme);
	
	Response updateVisualThemeSet(VisualThemeSet visualTheme);
	
	Response deleteVisualThemeSet(VisualThemeSet visualTheme);
	
	VisualThemeSet findVisualThemeSetById(String id);
	
	List<VisualThemeSet> findVisualThemeSets(VisualThemeSet visualTheme);
	
	List<VisualThemeSet> findVisualThemeSets(Map<String,Object> parameter);
	
	List<VisualThemeSet> findVisualThemeSets(Map<String,Object> parameter,PageBounds pageBounds);
}
