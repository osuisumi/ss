/**
 * 
 */
package com.haoyu.sip.cms.recommend.service;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.sip.cms.channel.entity.Channel;
import com.haoyu.sip.cms.recommend.entity.RecommendWorkshop;
import com.haoyu.sip.core.service.Response;

/**
 * @author lianghuahuang
 *
 */
public interface IRecommendWorkshopService {
	
	List<RecommendWorkshop> findRecommendWorkshops(RecommendWorkshop recommendWorkshop);
	
	List<RecommendWorkshop> findRecommendWorkshops(RecommendWorkshop recommendWorkshop,PageBounds pageBounds);
	
	List<RecommendWorkshop> findRecommendWorkshops(Map<String,Object> parameter);
	
	List<RecommendWorkshop> findRecommendWorkshops(Map<String,Object> parameter,PageBounds pageBounds);
	
	Response addWorkshopToRecommend(RecommendWorkshop recommendWorkshop);
	
	Response addWorkshopToRecommend(List<String> workshopIds,Channel channel);
	
	Response removeWorkshopFromRecommend(RecommendWorkshop recommendWorkshop);
	
	Response removeWorkshopFromRecommend(List<String> workshopIds,Channel channel);
}
