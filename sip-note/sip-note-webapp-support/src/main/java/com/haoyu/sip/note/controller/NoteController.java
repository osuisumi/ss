package com.haoyu.sip.note.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.web.AbstractBaseController;
import com.haoyu.sip.note.entity.Note;
import com.haoyu.sip.note.service.INoteService;

@Controller
@RequestMapping("/notes")
public class NoteController extends AbstractBaseController {
	
	@Resource
	private INoteService noteService;
	
	protected String getLogicalViewNamePrefix(){
		return "/study/note/";
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public String list(Note note ,Model model){
		model.addAttribute("note" ,note);
		getPageBounds(10, true);
		return getLogicalViewNamePrefix() + "list_note";
	}
	
	@RequestMapping(value = "create", method=RequestMethod.GET)
	public String create(Note note,Model model){
		model.addAttribute("note",note);
		return getLogicalViewNamePrefix() + "edit_note"; 
	}
	
	@RequestMapping(method=RequestMethod.POST)
	@ResponseBody
	public Response create(Note note){
		return this.noteService.createNote(note);
	}
	
	@RequestMapping(value="{id}/edit", method=RequestMethod.GET)
	public String edit(Note note,Model model){	
		model.addAttribute("note",noteService.findNoteById(note.getId()));
		return getLogicalViewNamePrefix() + "edit_note";
	}
	
	@RequestMapping(value="update",method=RequestMethod.PUT)
	@ResponseBody
	public Response update(Note note){
		return this.noteService.updateNote(note);
	}
	
	@RequestMapping(value="delete",method=RequestMethod.DELETE)
	@ResponseBody
	public Response delete(Note note){		
		return this.noteService.deleteNoteByLogic(note);
	}
	
}
