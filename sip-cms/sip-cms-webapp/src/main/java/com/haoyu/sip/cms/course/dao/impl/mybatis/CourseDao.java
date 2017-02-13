/**
 * 
 */
package com.haoyu.sip.cms.course.dao.impl.mybatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.sip.cms.course.dao.ICourseDao;
import com.haoyu.sip.cms.course.entity.Course;
import com.haoyu.sip.core.jdbc.MybatisDao;

/**
 * @author lianghuahuang
 *
 */
@Repository
public class CourseDao extends MybatisDao implements ICourseDao {

	/* (non-Javadoc)
	 * @see com.haoyu.sip.cms.course.dao.ICourseRelationDao#selectCourseRelations(java.util.Map)
	 */
	@Override
	public List<Course> selectCourses(
			Map<String, Object> parameter,PageBounds pageBounds) {		
		return super.selectList("selectByParameter", parameter,pageBounds);
	}

	@Override
	public Course selectCourseByCRid(String courseRelationId) {
		return super.selectOne("selectCourseByCRid", courseRelationId);
	}

}
