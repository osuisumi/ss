/**
 * 
 */
package com.haoyu.sip.theme.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.google.common.collect.Maps;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.theme.dao.IVisualThemeDao;
import com.haoyu.sip.theme.entity.VisualTheme;
import com.haoyu.sip.theme.service.IVisualThemeService;
import com.haoyu.sip.utils.Identities;

/**
 * @author lianghuahuang
 *
 */
@Service
public class VisualThemeServiceImpl implements IVisualThemeService {
	@Resource
	private IVisualThemeDao visualThemeDao;
	/* (non-Javadoc)
	 * @see com.haoyu.sip.theme.service.IVisualThemeService#createVisualTheme(com.haoyu.sip.theme.entity.VisualTheme)
	 */
	@Override
	public Response createVisualTheme(VisualTheme visualTheme) {
		if(visualTheme==null){
			return Response.failInstance().responseMsg("createVisualTheme fail!visualTheme is null!");
		}
		if(StringUtils.isEmpty(visualTheme.getId())){
			visualTheme.setId(Identities.uuid2());
		}
		int count = visualThemeDao.insertVisualTheme(visualTheme);
		return count>0?Response.successInstance().responseData(visualTheme):Response.failInstance().responseMsg("createVisualTheme fail!");
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.theme.service.IVisualThemeService#updateVisualTheme(com.haoyu.sip.theme.entity.VisualTheme)
	 */
	@Override
	public Response updateVisualTheme(VisualTheme visualTheme) {
		if(visualTheme==null||StringUtils.isEmpty(visualTheme.getId())){
			return Response.failInstance().responseMsg("updateVisualTheme is fail!visualTheme is null or visualTheme's id is null");
		}
		int count = visualThemeDao.updateVisualTheme(visualTheme);
		return count>0?Response.successInstance().responseData(visualTheme):Response.failInstance().responseMsg("updateVisualTheme fail!");
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.theme.service.IVisualThemeService#deleteVisualTheme(com.haoyu.sip.theme.entity.VisualTheme)
	 */
	@Override
	public Response deleteVisualTheme(VisualTheme visualTheme) {
		if(visualTheme==null||StringUtils.isEmpty(visualTheme.getId())){
			return Response.failInstance().responseMsg("deleteVisualTheme is fail!visualTheme is null or visualTheme's id is null");
		}
		int count = visualThemeDao.deleteVisualThemeByLogic(visualTheme);
		return count>0?Response.successInstance():Response.failInstance().responseMsg("deleteVisualTheme fail!");
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.theme.service.IVisualThemeService#findVisualThemeById(java.lang.String)
	 */
	@Override
	public VisualTheme findVisualThemeById(String id) {
		if(StringUtils.isEmpty(id)){
			return null;
		}
		return visualThemeDao.selectVisualThemeById(id);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.theme.service.IVisualThemeService#findVisualThemes(com.haoyu.sip.theme.entity.VisualTheme)
	 */
	@Override
	public List<VisualTheme> findVisualThemes(VisualTheme visualTheme) {
		Map<String,Object> parameter = Maps.newHashMap();
		if(visualTheme!=null){
			if(StringUtils.isNotEmpty(visualTheme.getName())){
				parameter.put("name", visualTheme.getName());
			}
			if(visualTheme.getVisualThemeSet()!=null&&StringUtils.isNotEmpty(visualTheme.getVisualThemeSet().getId())){
				parameter.put("visualThemeSetId", visualTheme.getVisualThemeSet().getId());
			}
		}
		return visualThemeDao.findAll(parameter);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.theme.service.IVisualThemeService#findVisualThemes(java.util.Map)
	 */
	@Override
	public List<VisualTheme> findVisualThemes(Map<String, Object> parameter) {
		return visualThemeDao.findAll(parameter);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.theme.service.IVisualThemeService#findVisualThemes(java.util.Map, com.github.miemiedev.mybatis.paginator.domain.PageBounds)
	 */
	@Override
	public List<VisualTheme> findVisualThemes(Map<String, Object> parameter,
			PageBounds pageBounds) {
		return visualThemeDao.findAll(parameter,pageBounds);
	}

}
