package com.haoyu.sip.note.mobile.service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.note.entity.Note;

public interface IMNoteService {

	Response listNote(Note note, PageBounds pageBounds);

	Response createNote(Note note);

	Response getNote(Note note);

	Response updateNote(Note note);

}
