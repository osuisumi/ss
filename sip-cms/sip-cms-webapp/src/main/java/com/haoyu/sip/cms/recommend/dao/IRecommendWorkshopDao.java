/**
 * 
 */
package com.haoyu.sip.cms.recommend.dao;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.sip.cms.recommend.entity.RecommendWorkshop;

/**
 * @author lianghuahuang
 *
 */
public interface IRecommendWorkshopDao {
	
	List<RecommendWorkshop> selectRecommendWorkshops(Map<String,Object> parameter);
	
	List<RecommendWorkshop> selectRecommendWorkshops(Map<String,Object> parameter,PageBounds  pageBounds);
}
