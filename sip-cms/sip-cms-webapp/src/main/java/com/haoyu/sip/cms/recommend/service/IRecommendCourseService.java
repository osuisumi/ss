/**
 * 
 */
package com.haoyu.sip.cms.recommend.service;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.sip.cms.channel.entity.Channel;
import com.haoyu.sip.cms.recommend.entity.RecommendCourse;
import com.haoyu.sip.core.service.Response;

/**
 * @author lianghuahuang
 *
 */
public interface IRecommendCourseService {
	
	List<RecommendCourse> findRecommendCourses(RecommendCourse recommendCourse);
	
	List<RecommendCourse> findRecommendCourses(RecommendCourse recommendCourse,PageBounds pageBounds);
	
	List<RecommendCourse> findRecommendCourses(Map<String,Object> parameter);
	
	List<RecommendCourse> findRecommendCourses(Map<String,Object> parameter,PageBounds pageBounds);
	
	Response addCourseToRecommend(RecommendCourse recommendCourse);
	
	Response addCourseToRecommend(List<String> courseIds,Channel channel);
	
	Response removeCourseFromRecommend(RecommendCourse recommendCourse);
	
	Response removeCourseFromRecommend(List<String> courseIds,Channel channel);
}
