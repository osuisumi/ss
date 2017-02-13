package com.haoyu.sip.note.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.google.common.collect.Maps;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.utils.ThreadContext;
import com.haoyu.sip.note.dao.INoteDao;
import com.haoyu.sip.note.entity.Note;
import com.haoyu.sip.note.service.INoteService;
import com.haoyu.sip.utils.Identities;

@Service
public class NoteServiceImpl implements INoteService {

	@Resource
	private INoteDao noteDao;
	
	@Override
	public Response createNote(Note note) {
		if (note == null){
			return Response.failInstance().responseMsg("create Note fail,because note is null !");
		}
		if (StringUtils.isEmpty(note.getId())) {
			note.setId(Identities.uuid2());
		}
		int count = noteDao.insert(note);
		if (count > 0) {
			return Response.successInstance().responseData(note);
		}
		return Response.failInstance();
	}

	@Override
	public List<Note> listNote(Map<String, Object> param,PageBounds pageBounds) {
		return noteDao.select(param,pageBounds);
	}
	
	@Override
	public Response updateNote(Note note) {
		if (note == null||StringUtils.isEmpty(note.getId())){
			return Response.failInstance().responseMsg("update Note fail,because note is null or note.id is null!");
		}
		int count = noteDao.update(note);
		if (count > 0) {
			return Response.successInstance().responseData(note);
		}
		return Response.failInstance();
	}

	@Override
	public Response deleteNoteByLogic(Note note) {
		if (note == null||StringUtils.isEmpty(note.getId())){
			return Response.failInstance().responseMsg("delete Note fail,because note is null or note.id is null!");
		}
		return noteDao.deleteByLogic(note)>0?Response.successInstance():Response.failInstance();
	}
	
	@Override
	public Note findNoteById(String id) {
		return noteDao.selectByPrimaryKey(id);
	}

	@Override
	public Map<String, Integer> getNoteCountByRelationIds(List<String> ids) {
		Map<String, Object> param = Maps.newHashMap();
		param.put("ids", ids);
		param.put("creator", ThreadContext.getUser().getId());
		Map<String, Map<String, Integer>> map = noteDao.getNoteCountByRelationIds(param);
		Map<String, Integer> countMap = Maps.newHashMap();
		Number num = 0;
		for(String key : map.keySet()) {
			num = (Number)map.get(key).get("count");
			countMap.put(key,num.intValue());
		}
		return countMap;
	}

	@Override
	public List<Note> listNote(Note note, PageBounds pageBounds) {
		Map<String, Object> param = Maps.newHashMap();
		
		if((note.getRelation()) != null && note.getRelation().getId() != null){
			param.put("relationIds",Arrays.asList(note.getRelation().getId().split(",")));
		}
		param.put("content",note.getContent());
		if (note.getCreator() != null && StringUtils.isNotEmpty(note.getCreator().getId())) {
			param.put("creator",note.getCreator().getId());
		}
		return this.listNote(param, pageBounds);
	}
	
}
