package com.haoyu.sip.note.service;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.note.entity.Note;

public interface INoteService {

	List<Note> listNote(Map<String, Object> param,PageBounds pageBounds);
	
	List<Note> listNote(Note note,PageBounds pageBounds);

	Response createNote(Note note);

	Response updateNote(Note note);
	
	Response deleteNoteByLogic(Note note);

	public Map<String, Integer> getNoteCountByRelationIds(List<String> ids);
	
	Note findNoteById(String id);

}
