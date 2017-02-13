/**
 * 
 */
package com.haoyu.sip.theme.service;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.theme.entity.VisualTheme;

/**
 * @author lianghuahuang
 *
 */
public interface IVisualThemeService {
	
	Response createVisualTheme(VisualTheme visualTheme);
	
	Response updateVisualTheme(VisualTheme visualTheme);
	
	Response deleteVisualTheme(VisualTheme visualTheme);
	
	VisualTheme findVisualThemeById(String id);
	
	List<VisualTheme> findVisualThemes(VisualTheme visualTheme);
	
	List<VisualTheme> findVisualThemes(Map<String,Object> parameter);
	
	List<VisualTheme> findVisualThemes(Map<String,Object> parameter,PageBounds pageBounds);
}
