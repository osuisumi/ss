/**
 * 
 */
package com.haoyu.sip.cms.recommend.dao;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.sip.cms.recommend.entity.RecommendCourse;

/**
 * @author lianghuahuang
 *
 */
public interface IRecommendCourseDao {
	
	List<RecommendCourse> selectRecommendCourses(Map<String,Object> parameter);
	
	List<RecommendCourse> selectRecommendCourses(Map<String,Object> parameter,PageBounds  pageBounds);
}
