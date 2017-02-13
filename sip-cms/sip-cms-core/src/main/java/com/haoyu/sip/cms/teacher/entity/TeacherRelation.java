/**
 * 
 */
package com.haoyu.sip.cms.teacher.entity;

import com.haoyu.sip.core.entity.BaseEntity;
import com.haoyu.sip.core.entity.Relation;

/**
 * @author lianghuahuang
 *
 */
public class TeacherRelation extends BaseEntity {
	
	private String id;
	
	private Teacher teacher;
	
	private Relation relation;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public Relation getRelation() {
		return relation;
	}

	public void setRelation(Relation relation) {
		this.relation = relation;
	}
}
