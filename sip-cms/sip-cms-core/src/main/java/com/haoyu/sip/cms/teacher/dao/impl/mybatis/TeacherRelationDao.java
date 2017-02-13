/**
 * 
 */
package com.haoyu.sip.cms.teacher.dao.impl.mybatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.haoyu.sip.cms.teacher.dao.ITeacherRelationDao;
import com.haoyu.sip.cms.teacher.entity.Teacher;
import com.haoyu.sip.cms.teacher.entity.TeacherRelation;
import com.haoyu.sip.core.jdbc.MybatisDao;
import com.haoyu.sip.core.utils.ThreadContext;

/**
 * @author lianghuahuang
 *
 */
@Repository
public class TeacherRelationDao extends MybatisDao implements
		ITeacherRelationDao {

	/* (non-Javadoc)
	 * @see com.haoyu.sip.cms.teacher.dao.ITeacherRelationDao#insertTeacherRelation(com.haoyu.sip.cms.teacher.entity.TeacherRelation)
	 */
	@Override
	public int insertTeacherRelation(Teacher teacher) {
		Map<String,Object> parameter = Maps.newHashMap();
		parameter.put("teachers", Lists.newArrayList(teacher));
		parameter.put("uuid",super.getDbUuid());
		parameter.put("createTime",System.currentTimeMillis());
		parameter.put("creator",ThreadContext.getUser());
		List<String> relationIds = teacher.getRelationIds();
		int count = 0;
		if(relationIds!=null&&!relationIds.isEmpty()){
			count+=super.insert(parameter);
		}
		return count;
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.cms.teacher.dao.ITeacherRelationDao#insertTeacherRelation(java.util.List, java.lang.String)
	 */
	@Override
	public int insertTeacherRelation(List<Teacher> teachers, String relationId) {
		Map<String,Object> parameter = Maps.newHashMap();
		parameter.put("uuid",super.getDbUuid());
		parameter.put("createTime",System.currentTimeMillis());
		parameter.put("creator",ThreadContext.getUser());
		parameter.put("teachers", teachers);
		return super.insert(parameter);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.cms.teacher.dao.ITeacherRelationDao#deleteByLogic(com.haoyu.sip.cms.teacher.entity.Teacher)
	 */
	@Override
	public int deleteByLogic(List<Teacher> teachers,String relationId) {
		Map<String,Object> parameter = Maps.newHashMap();
		parameter.put("relationId",relationId);
		parameter.put("udpateTime",System.currentTimeMillis());
		parameter.put("updatedby",ThreadContext.getUser());
		parameter.put("teachers", teachers);
		return super.deleteByLogic(parameter);
	}

}
