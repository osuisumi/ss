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
import org.springframework.stereotype.Repository;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.google.common.collect.Maps;
import com.haoyu.sip.cms.channel.entity.Channel;
import com.haoyu.sip.cms.recommend.dao.IRecommendCourseDao;
import com.haoyu.sip.cms.recommend.entity.Recommend;
import com.haoyu.sip.cms.recommend.entity.RecommendCourse;
import com.haoyu.sip.cms.recommend.service.IRecommendCourseService;
import com.haoyu.sip.cms.recommend.service.IRecommendService;
import com.haoyu.sip.core.entity.Relation;
import com.haoyu.sip.core.service.Response;

/**
 * @author lianghuahuang
 *
 */
@Repository
public class RecommendCourseServiceImpl implements IRecommendCourseService {
	@Resource
	private IRecommendCourseDao recommendCourseDao;
	
	@Resource
	private IRecommendService recommendService;
	/* (non-Javadoc)
	 * @see com.haoyu.sip.cms.recommend.service.IRecommendCourseService#findRecommendCourses(com.haoyu.sip.cms.recommend.entity.RecommendCourse)
	 */
	@Override
	public List<RecommendCourse> findRecommendCourses(
			RecommendCourse recommendCourse) {
		return findRecommendCourses(recommendCourse,null);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.cms.recommend.service.IRecommendCourseService#findRecommendCourses(com.haoyu.sip.cms.recommend.entity.RecommendCourse, com.github.miemiedev.mybatis.paginator.domain.PageBounds)
	 */
	@Override
	public List<RecommendCourse> findRecommendCourses(
			RecommendCourse recommendCourse, PageBounds pageBounds) {
		Map<String,Object> parameter = Maps.newHashMap();
		if(StringUtils.isNotEmpty(recommendCourse.getCourseName())){
			parameter.put("courseName", recommendCourse.getCourseName());
		}
		if(recommendCourse.getRecommend()!=null&&recommendCourse.getRecommend().getChannel()!=null&&StringUtils.isNotEmpty(recommendCourse.getRecommend().getChannel().getId())){
			parameter.put("channel", recommendCourse.getRecommend().getChannel());
		}
		parameter.put("relationType","course");
		return recommendCourseDao.selectRecommendCourses(parameter,pageBounds);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.cms.recommend.service.IRecommendCourseService#findRecommendCourses(java.util.Map)
	 */
	@Override
	public List<RecommendCourse> findRecommendCourses(
			Map<String, Object> parameter) {
		return recommendCourseDao.selectRecommendCourses(parameter);
	}
	

	@Override
	@Cacheable(key="'rc_'+#parameter['cacheKey']",value="recommendCourses")
	public List<RecommendCourse> findRecommendCourses(
			Map<String, Object> parameter, PageBounds pageBounds) {
		if(parameter.containsKey("recommendCourse")&&parameter.get("recommendCourse")!=null){
			RecommendCourse recommendCourse = (RecommendCourse)parameter.get("recommendCourse");
			if(StringUtils.isNotEmpty(recommendCourse.getCourseName())){
				parameter.put("courseName", recommendCourse.getCourseName());
			}
			if(recommendCourse.getRecommend()!=null&&recommendCourse.getRecommend().getChannel()!=null&&StringUtils.isNotEmpty(recommendCourse.getRecommend().getChannel().getId())){
				parameter.put("channel", recommendCourse.getRecommend().getChannel());
			}
		}
		parameter.put("relationType","course");
		return recommendCourseDao.selectRecommendCourses(parameter,pageBounds);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.cms.recommend.service.IRecommendCourseService#addCourseToRecommend(com.haoyu.sip.cms.recommend.entity.RecommendCourse)
	 */
	@Override
	@CacheEvict(value="recommendCourses",allEntries=true)
	public Response addCourseToRecommend(RecommendCourse recommendCourse) {
		Recommend recommend = recommendCourse.getRecommend();
		recommend.setRelation(new Relation(recommendCourse.getId(),"course"));
		return recommendService.createRecommend(recommend);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.cms.recommend.service.IRecommendCourseService#addCourseToRecommend(java.util.List, com.haoyu.sip.cms.channel.entity.Channel)
	 */
	@Override
	@CacheEvict(value="recommendCourses",allEntries=true)
	public Response addCourseToRecommend(List<String> courseIds, Channel channel) {
		if(courseIds!=null&&!courseIds.isEmpty()&&channel!=null&&StringUtils.isNotEmpty(channel.getId())){
			for(String courseId:courseIds){
				Recommend recommend = new Recommend(channel);
				recommend.setRelation(new Relation(courseId,"course"));
				recommendService.createRecommend(recommend);
			}
		}
		return Response.successInstance();
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.cms.recommend.service.IRecommendCourseService#removeCourseFromRecommend(com.haoyu.sip.cms.recommend.entity.RecommendCourse)
	 */
	@Override
	@CacheEvict(value="recommendCourses",allEntries=true)
	public Response removeCourseFromRecommend(RecommendCourse recommendCourse) {
		Recommend recommend = recommendCourse.getRecommend();
		recommend.setRelation(new Relation(recommendCourse.getId(),"course"));
		return recommendService.deleteRecommend(recommend);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.cms.recommend.service.IRecommendCourseService#removeCourseFromRecommend(java.util.List, com.haoyu.sip.cms.channel.entity.Channel)
	 */
	@Override
	@CacheEvict(value="recommendCourses",allEntries=true)
	public Response removeCourseFromRecommend(List<String> courseIds,
			Channel channel) {
		if(courseIds!=null&&!courseIds.isEmpty()&&channel!=null&&StringUtils.isNotEmpty(channel.getId())){
			for(String courseId:courseIds){
				Recommend recommend = new Recommend(channel);
				recommend.setRelation(new Relation(courseId,"course"));
				recommendService.deleteRecommend(recommend);
			}
		}
		return Response.successInstance();
	}


}
