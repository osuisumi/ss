/**
 * 
 */
package com.haoyu.sip.cms.teacher.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.google.common.collect.Maps;
import com.haoyu.sip.cms.teacher.dao.ITeacherDao;
import com.haoyu.sip.cms.teacher.entity.Teacher;
import com.haoyu.sip.cms.teacher.service.ITeacherService;
import com.haoyu.sip.core.entity.Relation;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.file.entity.FileInfo;
import com.haoyu.sip.file.service.IFileService;
import com.haoyu.sip.utils.Identities;

/**
 * @author lianghuahuang
 *
 */
@Service
public class TeacherServiceImpl implements ITeacherService {
	@Resource 
	private ITeacherDao teacherDao;
	@Resource
	private IFileService fileService;
	/* (non-Javadoc)
	 * @see com.haoyu.sip.cms.service.ICmsContentService#findAll(java.util.Map)
	 */
	@Override
	public List<Teacher> findTeachers(Map<String, Object> parameter) {
		return teacherDao.findAll(parameter);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.cms.service.ICmsContentService#findAll(java.util.Map, com.github.miemiedev.mybatis.paginator.domain.PageBounds)
	 */
	@Override
	public List<Teacher> findTeachers(Map<String, Object> parameter,
			PageBounds pageBounds) {
		return teacherDao.findAll(parameter, pageBounds);
	}


	/* (non-Javadoc)
	 * @see com.haoyu.sip.cms.teacher.service.ITeacherService#createTeacher(com.haoyu.sip.cms.teacher.entity.Teacher)
	 */
	@Override
	public Response createTeacher(Teacher teacher) {
		if(teacher==null){
			return Response.failInstance().responseMsg("createTeacher fail!teacher is null!");
		}
		if(StringUtils.isEmpty(teacher.getId())){
			teacher.setId(Identities.uuid2());
		}
		if(teacher.getFileInfo()!=null){
			fileService.createFile(teacher.getFileInfo(), teacher.getId(), "cms-teacher-avatar");
			FileInfo fi = teacher.getFileInfo();
			teacher.setAvatar(fi.getUrl());
		}
		int count = teacherDao.insertTeacher(teacher);
		return count>0?Response.successInstance().responseData(teacher):Response.failInstance().responseMsg("createTeacher fail!");
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.cms.teacher.service.ITeacherService#updateTeacher(com.haoyu.sip.cms.teacher.entity.Teacher)
	 */
	@Override
	public Response updateTeacher(Teacher teacher) {
		if(teacher==null||StringUtils.isEmpty(teacher.getId())){
			return Response.failInstance().responseMsg("updateTeacher is fail!teacher is null or teacher's id is null");
		}
		if(teacher.getFileInfo()!=null){
			List<FileInfo> oldFileInfos = fileService.listFileInfoByRelationId(teacher.getId());
			fileService.updateFile(teacher.getFileInfo(), oldFileInfos!=null&&!oldFileInfos.isEmpty()?oldFileInfos.get(0):null, teacher.getId(), "cms-teacher-avatar");
			fileService.createFile(teacher.getFileInfo(), teacher.getId(), "cms-teacher-avatar");
			FileInfo fi = teacher.getFileInfo();
			teacher.setAvatar(fi.getUrl());
		}else{
			List<FileInfo> oldImageFileInfos = fileService.listFileInfoByRelation(new Relation(teacher.getId(),"cms-teacher-avatar"));
			if(oldImageFileInfos!=null&&!oldImageFileInfos.isEmpty()){
				fileService.updateFile(null, oldImageFileInfos.get(0), teacher.getId(), "cms-teacher-avatar");
			}
			teacher.setAvatar("");
		}
		int count = teacherDao.updateTeacher(teacher);
		return count>0?Response.successInstance().responseData(teacher):Response.failInstance().responseMsg("updateTeacher fail!");
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.cms.teacher.service.ITeacherService#deleteTeacher(com.haoyu.sip.cms.teacher.entity.Teacher)
	 */
	@Override
	@CacheEvict(value="teachers",allEntries=true)
	public Response deleteTeacher(Teacher teacher) {
		if(teacher==null||StringUtils.isEmpty(teacher.getId())){
			return Response.failInstance().responseMsg("deleteTeacher is fail!teacher is null or teacher's id is null");
		}
		int count = teacherDao.deleteTeacherByLogic(teacher);
		return count>0?Response.successInstance():Response.failInstance().responseMsg("deleteTeacher fail!");
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.cms.teacher.service.ITeacherService#findTeacherById(java.lang.String)
	 */
	@Override
	public Teacher findTeacherById(String id) {
		if(StringUtils.isEmpty(id)){
			return null;
		}
		return teacherDao.selectTeacherById(id);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.cms.teacher.service.ITeacherService#findTeachers(com.haoyu.sip.cms.teacher.entity.Teacher)
	 */
	@Override
	public List<Teacher> findTeachers(Teacher teacher) {
		return findTeachers(teacher,null);
	}

	@Override
	public List<Teacher> findTeachers(Teacher teacher, PageBounds pageBounds) {
		Map<String,Object> parameter = Maps.newHashMap();
		if(teacher!=null){
			if(StringUtils.isNotEmpty(teacher.getFullName())){
				parameter.put("fullName", teacher.getFullName());
			}
			if(StringUtils.isNotEmpty(teacher.getIdNo())){
				parameter.put("idNo", teacher.getIdNo());
			}
			if(teacher.getRelationIds()!=null&&!teacher.getRelationIds().isEmpty()){
				parameter.put("relationIds", teacher.getRelationIds());
			}
		}
		return teacherDao.findAll(parameter,pageBounds);
	}

}
