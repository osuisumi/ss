/**
 * 
 */
package com.haoyu.sip.cms.course.dao;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.sip.cms.course.entity.Course;

/**
 * @author lianghuahuang
 *
 */
public interface ICourseDao {
	List<Course> selectCourses(Map<String,Object> parameter,PageBounds pageBounds);
	
	Course selectCourseByCRid(String courseRelationId);
}
