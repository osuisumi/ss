/**
 * 
 */
package com.haoyu.sip.cms.teacher.service;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.sip.cms.teacher.entity.Teacher;
import com.haoyu.sip.core.service.Response;

/**
 * @author lianghuahuang
 *
 */
public interface ITeacherService{
	
	Response createTeacher(Teacher teacher);
	
	Response updateTeacher(Teacher teacher);
	
	Response deleteTeacher(Teacher teacher);
	
	Teacher findTeacherById(String id);
	
	List<Teacher> findTeachers(Teacher teacher);
	
	List<Teacher> findTeachers(Teacher teacher,PageBounds pageBounds);
	
	List<Teacher> findTeachers(Map<String,Object> parameter);
	
	List<Teacher> findTeachers(Map<String,Object> parameter,PageBounds pageBounds);
}
