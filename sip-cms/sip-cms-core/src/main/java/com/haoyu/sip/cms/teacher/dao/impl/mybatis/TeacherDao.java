/**
 * 
 */
package com.haoyu.sip.cms.teacher.dao.impl.mybatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.sip.cms.teacher.dao.ITeacherDao;
import com.haoyu.sip.cms.teacher.entity.Teacher;
import com.haoyu.sip.core.jdbc.MybatisDao;

/**
 * @author lianghuahuang
 *
 */
@Repository
public class TeacherDao extends MybatisDao implements ITeacherDao {

	/* (non-Javadoc)
	 * @see com.haoyu.sip.cms.teacher.dao.ITeacherDao#selectTeacherById(java.lang.String)
	 */
	@Override
	public Teacher selectTeacherById(String id) {
		return super.selectByPrimaryKey(id);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.cms.teacher.dao.ITeacherDao#insertTeacher(com.haoyu.sip.cms.teacher.entity.Teacher)
	 */
	@Override
	public int insertTeacher(Teacher teacher) {
		teacher.setDefaultValue();
		return super.insert(teacher);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.cms.teacher.dao.ITeacherDao#updateTeacher(com.haoyu.sip.cms.teacher.entity.Teacher)
	 */
	@Override
	public int updateTeacher(Teacher teacher) {
		teacher.setUpdateValue();
		return super.update(teacher);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.cms.teacher.dao.ITeacherDao#deleteTeacherByLogic(com.haoyu.sip.cms.teacher.entity.Teacher)
	 */
	@Override
	public int deleteTeacherByLogic(Teacher teacher) {
		teacher.setUpdateValue();
		return super.deleteByLogic(teacher);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.cms.teacher.dao.ITeacherDao#deleteTeacherByPhysics(java.lang.String)
	 */
	@Override
	public int deleteTeacherByPhysics(String id) {
		return super.deleteByPhysics(id);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.cms.teacher.dao.ITeacherDao#findAll(java.util.Map)
	 */
	@Override
	public List<Teacher> findAll(Map<String, Object> parameter) {
		return super.selectList("selectByParameter", parameter);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.cms.teacher.dao.ITeacherDao#findAll(java.util.Map, com.github.miemiedev.mybatis.paginator.domain.PageBounds)
	 */
	@Override
	public List<Teacher> findAll(Map<String, Object> parameter,
			PageBounds pageBounds) {
		return super.selectList("selectByParameter", parameter,pageBounds);
	}

}
