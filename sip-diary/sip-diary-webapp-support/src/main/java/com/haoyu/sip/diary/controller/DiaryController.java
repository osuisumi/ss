package com.haoyu.sip.diary.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.web.AbstractBaseController;
import com.haoyu.sip.diary.entity.Diary;
import com.haoyu.sip.diary.service.IDiaryService;
@Controller
@RequestMapping("**/diaris")
public class DiaryController extends AbstractBaseController{
	
	@Resource
	private IDiaryService diaryService;
	
	protected String getLogicalViewNamePrefix(){
		return "/diary/";
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public String list(Diary diary ,Model model){
		model.addAttribute("diary" ,diary);
		getPageBounds(10, true);
		return getLogicalViewNamePrefix() + "list_diary";
	}
	
	@RequestMapping(value = "create", method=RequestMethod.GET)
	public String create(Diary diary,Model model){
		model.addAttribute("diary",diary);
		return getLogicalViewNamePrefix() + "edit_diary"; 
	}
	
	@RequestMapping(method=RequestMethod.POST)
	@ResponseBody
	public Response create(Diary diary){
		return this.diaryService.createDiary(diary);
	}
	
	@RequestMapping(value="{id}/edit", method=RequestMethod.GET)
	public String edit(Diary diary,Model model){	
		model.addAttribute("diary",diaryService.findDiaryById(diary.getId()));
		return getLogicalViewNamePrefix() + "edit_diary";
	}
	
	@RequestMapping(method=RequestMethod.PUT)
	@ResponseBody
	public Response update(Diary diary){
		return this.diaryService.updateDiary(diary);
	}
	
	@RequestMapping(method=RequestMethod.DELETE)
	@ResponseBody
	public Response delete(Diary diary){		
		return this.diaryService.deleteDiary(diary);
	}
}
