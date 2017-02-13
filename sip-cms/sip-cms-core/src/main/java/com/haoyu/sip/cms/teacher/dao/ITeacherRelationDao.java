/**
 * 
 */
package com.haoyu.sip.cms.teacher.dao;

import java.util.List;

import com.haoyu.sip.cms.teacher.entity.Teacher;
import com.haoyu.sip.cms.teacher.entity.TeacherRelation;

/**
 * @author lianghuahuang
 *
 */
public interface ITeacherRelationDao {
	
	int insertTeacherRelation(Teacher teacher);
	
	int insertTeacherRelation(List<Teacher> teachers,String relationId);
	
	int deleteByLogic(List<Teacher> teachers,String relationId);
}
