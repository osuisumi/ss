package com.haoyu.sip.note.mobile.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.github.miemiedev.mybatis.paginator.domain.Paginator;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.utils.BeanUtils;
import com.haoyu.sip.core.utils.ThreadContext;
import com.haoyu.sip.note.entity.Note;
import com.haoyu.sip.note.mobile.entity.MNote;
import com.haoyu.sip.note.mobile.service.IMNoteService;
import com.haoyu.sip.note.service.INoteService;
import com.haoyu.sip.utils.Collections3;

@Service
public class MNoteServiceImpl implements IMNoteService{

	@Resource
	private INoteService noteService;
	
	@Override
	public Response listNote(Note note,PageBounds pageBounds) {
		List<MNote> mNotes = Lists.newArrayList();
		Map<String, Object> param = Maps.newHashMap();
		if (note.getRelation() != null) {
			if (StringUtils.isNotEmpty(note.getRelation().getId())) {
				param.put("relationIds",Arrays.asList(note.getRelation().getId().split(",")));
			}
		}
		param.put("creator",ThreadContext.getUser().getId());
		List<Note> notes = noteService.listNote(param, pageBounds);
		
		PageList pageList = (PageList)notes;
		Paginator paginator = pageList.getPaginator();
		
		if (Collections3.isNotEmpty(notes)) {
			mNotes = BeanUtils.getCopyList(notes, MNote.class);
		}
		
		Map<String, Object> resultMap = Maps.newHashMap();
		resultMap.put("notes",mNotes);
		resultMap.put("paginator",paginator);
		return Response.successInstance().responseData(resultMap);
	}

	@Override
	public Response getNote(Note note) {
		if (StringUtils.isNotEmpty(note.getId())) {
			MNote mNote = new MNote();
			note = noteService.findNoteById(note.getId());
			BeanUtils.copyProperties(note,mNote);
			return Response.successInstance().responseData(mNote);
		}
		return Response.failInstance();
	}
	
	@Override
	public Response createNote(Note note) {
		Response response = noteService.createNote(note);
		if (response.isSuccess()) {
			MNote mNote = new MNote();
			if (response.getResponseData() != null) {					
				BeanUtils.copyProperties(response.getResponseData(),mNote);
			}
			return Response.successInstance().responseData(mNote);
		}
		return Response.failInstance();
	}

	@Override
	public Response updateNote(Note note) {
		Response response = noteService.updateNote(note);
		if (response.isSuccess()) {
			MNote mNote = new MNote();
			if (response.getResponseData() != null) {
				BeanUtils.copyProperties(response.getResponseData(),mNote);
			}
			return Response.successInstance().responseData(mNote);
		}
		return Response.failInstance();
	}

}
