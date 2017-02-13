package com.haoyu.sip.note.mobile.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.web.AbstractBaseMobileController;
import com.haoyu.sip.note.entity.Note;
import com.haoyu.sip.note.mobile.service.IMNoteService;
import com.haoyu.sip.note.service.INoteService;

@Controller
@RequestMapping("**/m/note")
public class MNoteController extends AbstractBaseMobileController{
	
	@Resource
	private INoteService noteService;
	@Resource
	private IMNoteService noteMobileService;

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public Response list(Note note){
		return noteMobileService.listNote(note,getPageBounds(10, true));
	}
	
	@RequestMapping(value="{id}",method=RequestMethod.GET)
	@ResponseBody
	public Response get(Note note){
		return noteMobileService.getNote(note);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	@ResponseBody
	public Response create(Note note){
		return noteMobileService.createNote(note);
	}
	
	@RequestMapping(value="{id}",method=RequestMethod.PUT)
	@ResponseBody
	public Response update(Note note){
		return noteMobileService.updateNote(note);
	}
	
	@RequestMapping(value="{id}",method=RequestMethod.DELETE)
	@ResponseBody
	public Response delete(Note note){
		return noteService.deleteNoteByLogic(note);
	}
	
}
