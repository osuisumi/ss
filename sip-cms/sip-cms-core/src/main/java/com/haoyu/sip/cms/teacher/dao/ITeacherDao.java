/**
 * 
 */
package com.haoyu.sip.cms.teacher.dao;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.sip.cms.teacher.entity.Teacher;

/**
 * @author lianghuahuang
 *
 */
public interface ITeacherDao {
	Teacher selectTeacherById(String id);
	
	int insertTeacher(Teacher teacher);
	
	int updateTeacher(Teacher teacher);
	
	int deleteTeacherByLogic(Teacher teacher);
	
	int deleteTeacherByPhysics(String id);

	List<Teacher> findAll(Map<String, Object> parameter);
	
	List<Teacher> findAll(Map<String, Object> parameter, PageBounds pageBounds);
}
