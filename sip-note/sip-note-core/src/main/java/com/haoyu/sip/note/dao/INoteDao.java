package com.haoyu.sip.note.dao;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.sip.note.entity.Note;

public interface INoteDao {
	
	int insert(Note entity);
	
	int update(Note entity);
	
	int deleteByLogic(Note entity);

	Note selectByPrimaryKey(String id);

	List<Note> select(Map<String, Object> param,PageBounds pageBounds);
	
	public Map<String, Map<String, Integer>> getNoteCountByRelationIds(Map<String, Object> param);

}
