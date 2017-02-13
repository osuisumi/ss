package com.haoyu.sip.note.dao.impl.mybatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.sip.core.jdbc.MybatisDao;
import com.haoyu.sip.core.utils.ThreadContext;
import com.haoyu.sip.note.dao.INoteDao;
import com.haoyu.sip.note.entity.Note;

@Repository
public class NoteDao extends MybatisDao implements INoteDao {

	@Override
	public int insert(Note note) {
		note.setDefaultValue();
		return super.insert(note);
	}

	@Override
	public int update(Note note) {
		note.setUpdateTime(System.currentTimeMillis());
		note.setUpdatedby(ThreadContext.getUser());
		return super.update(note);
	}

	@Override
	public int deleteByLogic(Note note) {
		note.setUpdateTime(System.currentTimeMillis());
		note.setUpdatedby(ThreadContext.getUser());
		return super.deleteByLogic(note);
	}

	@Override
	public Note selectByPrimaryKey(String id) {
		return super.selectByPrimaryKey(id);
	}

	@Override
	public List<Note> select(Map<String, Object> param,PageBounds pageBounds) {
		return super.selectList("select", param, pageBounds);
	}

	@Override
	public Map<String, Map<String, Integer>> getNoteCountByRelationIds(Map<String, Object> param) {
		return super.selectMap("getNoteCountByRelationIds", param,"relationId");
	}

}
