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
import com.haoyu.sip.theme.dao.IVisualThemeSetDao;
import com.haoyu.sip.theme.entity.VisualThemeSet;
import com.haoyu.sip.theme.service.IVisualThemeSetService;
import com.haoyu.sip.utils.Identities;

/**
 * @author lianghuahuang
 *
 */
@Service
public class VisualThemeSetServiceImpl implements IVisualThemeSetService {

	@Resource
	private IVisualThemeSetDao visualThemeSetDao;
	/* (non-Javadoc)
	 * @see com.haoyu.sip.theme.service.IVisualThemeSetService#createVisualThemeSet(com.haoyu.sip.theme.entity.VisualThemeSet)
	 */
	@Override
	public Response createVisualThemeSet(VisualThemeSet visualThemeSet) {
		if(visualThemeSet==null){
			return Response.failInstance().responseMsg("createVisualThemeSet fail!visualThemeSet is null!");
		}
		if(StringUtils.isEmpty(visualThemeSet.getId())){
			visualThemeSet.setId(Identities.uuid2());
		}
		int count = visualThemeSetDao.insertVisualThemeSet(visualThemeSet);
		return count>0?Response.successInstance().responseData(visualThemeSet):Response.failInstance().responseMsg("createVisualThemeSet fail!");
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.theme.service.IVisualThemeSetService#updateVisualThemeSet(com.haoyu.sip.theme.entity.VisualThemeSet)
	 */
	@Override
	public Response updateVisualThemeSet(VisualThemeSet visualThemeSet) {
		if(visualThemeSet==null||StringUtils.isEmpty(visualThemeSet.getId())){
			return Response.failInstance().responseMsg("updateVisualThemeSet is fail!visualThemeSet is null or visualThemeSet's id is null");
		}
		int count = visualThemeSetDao.updateVisualThemeSet(visualThemeSet);
		return count>0?Response.successInstance().responseData(visualThemeSet):Response.failInstance().responseMsg("updateVisualThemeSet fail!");
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.theme.service.IVisualThemeSetService#deleteVisualThemeSet(com.haoyu.sip.theme.entity.VisualThemeSet)
	 */
	@Override
	public Response deleteVisualThemeSet(VisualThemeSet visualThemeSet) {
		if(visualThemeSet==null||StringUtils.isEmpty(visualThemeSet.getId())){
			return Response.failInstance().responseMsg("deleteVisualThemeSet is fail!visualThemeSet is null or visualThemeSet's id is null");
		}
		int count = visualThemeSetDao.deleteVisualThemeSetByLogic(visualThemeSet);
		return count>0?Response.successInstance():Response.failInstance().responseMsg("deleteVisualThemeSet fail!");
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.theme.service.IVisualThemeSetService#findVisualThemeSetById(java.lang.String)
	 */
	@Override
	public VisualThemeSet findVisualThemeSetById(String id) {
		if(StringUtils.isEmpty(id)){
			return null;
		}
		return visualThemeSetDao.selectVisualThemeSetById(id);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.theme.service.IVisualThemeSetService#findVisualThemeSets(com.haoyu.sip.theme.entity.VisualThemeSet)
	 */
	@Override
	public List<VisualThemeSet> findVisualThemeSets(VisualThemeSet visualThemeSet) {
		Map<String,Object> parameter = Maps.newHashMap();
		if(visualThemeSet!=null){
			if(StringUtils.isNotEmpty(visualThemeSet.getName())){
				parameter.put("name", visualThemeSet.getName());
			}
		}
		return visualThemeSetDao.findAll(parameter);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.theme.service.IVisualThemeSetService#findVisualThemeSets(java.util.Map)
	 */
	@Override
	public List<VisualThemeSet> findVisualThemeSets(Map<String, Object> parameter) {
		return visualThemeSetDao.findAll(parameter);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.theme.service.IVisualThemeSetService#findVisualThemeSets(java.util.Map, com.github.miemiedev.mybatis.paginator.domain.PageBounds)
	 */
	@Override
	public List<VisualThemeSet> findVisualThemeSets(Map<String, Object> parameter,
			PageBounds pageBounds) {
		return visualThemeSetDao.findAll(parameter,pageBounds);
	}

}
