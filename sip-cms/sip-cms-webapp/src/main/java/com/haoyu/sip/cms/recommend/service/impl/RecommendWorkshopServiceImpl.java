/**
 * 
 */
package com.haoyu.sip.cms.recommend.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.google.common.collect.Maps;
import com.haoyu.sip.cms.channel.entity.Channel;
import com.haoyu.sip.cms.recommend.dao.IRecommendWorkshopDao;
import com.haoyu.sip.cms.recommend.entity.Recommend;
import com.haoyu.sip.cms.recommend.entity.RecommendWorkshop;
import com.haoyu.sip.cms.recommend.service.IRecommendService;
import com.haoyu.sip.cms.recommend.service.IRecommendWorkshopService;
import com.haoyu.sip.core.entity.Relation;
import com.haoyu.sip.core.service.Response;

/**
 * @author lianghuahuang
 *
 */
@Service
public class RecommendWorkshopServiceImpl implements IRecommendWorkshopService {

	@Resource
	private IRecommendWorkshopDao recommendWorkshopDao;
	
	@Resource
	private IRecommendService recommendService;
	/* (non-Javadoc)
	 * @see com.haoyu.sip.cms.recommend.service.IRecommendWorkshopService#findRecommendWorkshops(com.haoyu.sip.cms.recommend.entity.RecommendWorkshop)
	 */
	@Override
	public List<RecommendWorkshop> findRecommendWorkshops(
			RecommendWorkshop recommendWorkshop) {
		return findRecommendWorkshops(recommendWorkshop,null);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.cms.recommend.service.IRecommendWorkshopService#findRecommendWorkshops(com.haoyu.sip.cms.recommend.entity.RecommendWorkshop, com.github.miemiedev.mybatis.paginator.domain.PageBounds)
	 */
	@Override
	public List<RecommendWorkshop> findRecommendWorkshops(
			RecommendWorkshop recommendWorkshop, PageBounds pageBounds) {
		Map<String,Object> parameter = Maps.newHashMap();
		if(StringUtils.isNotEmpty(recommendWorkshop.getWorkshopName())){
			parameter.put("workshopName", recommendWorkshop.getWorkshopName());
		}
		if(recommendWorkshop.getRecommend()!=null&&recommendWorkshop.getRecommend().getChannel()!=null&&StringUtils.isNotEmpty(recommendWorkshop.getRecommend().getChannel().getId())){
			parameter.put("channel", recommendWorkshop.getRecommend().getChannel());
		}
		parameter.put("relationType","workshop");
		return recommendWorkshopDao.selectRecommendWorkshops(parameter,pageBounds);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.cms.recommend.service.IRecommendWorkshopService#findRecommendWorkshops(java.util.Map)
	 */
	@Override
	public List<RecommendWorkshop> findRecommendWorkshops(
			Map<String, Object> parameter) {
		return recommendWorkshopDao.selectRecommendWorkshops(parameter);
	}
	
	@Override
	@Cacheable(key="'rc_'+#parameter['cacheKey']",value="recommendWorkshops")
	public List<RecommendWorkshop> findRecommendWorkshops(
			Map<String, Object> parameter, PageBounds pageBounds) {
		if(parameter.containsKey("recommendWorkshop")&&parameter.get("recommendWorkshop")!=null){
			RecommendWorkshop recommendWorkshop = (RecommendWorkshop)parameter.get("recommendWorkshop");
			if(StringUtils.isNotEmpty(recommendWorkshop.getWorkshopName())){
				parameter.put("courseName", recommendWorkshop.getWorkshopName());
			}
			if(recommendWorkshop.getRecommend()!=null&&recommendWorkshop.getRecommend().getChannel()!=null&&StringUtils.isNotEmpty(recommendWorkshop.getRecommend().getChannel().getId())){
				parameter.put("channel", recommendWorkshop.getRecommend().getChannel());
			}
		}
		parameter.put("relationType","workshop");
		return recommendWorkshopDao.selectRecommendWorkshops(parameter,pageBounds);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.cms.recommend.service.IRecommendWorkshopService#addWorkshopToRecommend(com.haoyu.sip.cms.recommend.entity.RecommendWorkshop)
	 */
	@Override
	@CacheEvict(value="recommendWorkshops",allEntries=true)
	public Response addWorkshopToRecommend(RecommendWorkshop recommendWorkshop) {
		Recommend recommend = recommendWorkshop.getRecommend();
		recommend.setRelation(new Relation(recommendWorkshop.getId(),"workshop"));
		return recommendService.createRecommend(recommend);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.cms.recommend.service.IRecommendWorkshopService#addWorkshopToRecommend(java.util.List, com.haoyu.sip.cms.channel.entity.Channel)
	 */
	@Override
	@CacheEvict(value="recommendWorkshops",allEntries=true)
	public Response addWorkshopToRecommend(List<String> workshopIds, Channel channel) {
		if(workshopIds!=null&&!workshopIds.isEmpty()&&channel!=null&&StringUtils.isNotEmpty(channel.getId())){
			for(String workshopId:workshopIds){
				Recommend recommend = new Recommend(channel);
				recommend.setRelation(new Relation(workshopId,"workshop"));
				recommendService.createRecommend(recommend);
			}
		}
		return Response.successInstance();
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.cms.recommend.service.IRecommendWorkshopService#removeWorkshopFromRecommend(com.haoyu.sip.cms.recommend.entity.RecommendWorkshop)
	 */
	@Override
	@CacheEvict(value="recommendWorkshops",allEntries=true)
	public Response removeWorkshopFromRecommend(RecommendWorkshop recommendWorkshop) {
		Recommend recommend = recommendWorkshop.getRecommend();
		recommend.setRelation(new Relation(recommendWorkshop.getId(),"workshop"));
		return recommendService.deleteRecommend(recommend);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.cms.recommend.service.IRecommendWorkshopService#removeWorkshopFromRecommend(java.util.List, com.haoyu.sip.cms.channel.entity.Channel)
	 */
	@Override
	@CacheEvict(value="recommendWorkshops",allEntries=true)
	public Response removeWorkshopFromRecommend(List<String> workshopIds,
			Channel channel) {
		if(workshopIds!=null&&!workshopIds.isEmpty()&&channel!=null&&StringUtils.isNotEmpty(channel.getId())){
			for(String workshopId:workshopIds){
				Recommend recommend = new Recommend(channel);
				recommend.setRelation(new Relation(workshopId,"workshop"));
				recommendService.deleteRecommend(recommend);
			}
		}
		return Response.successInstance();
	}



}
