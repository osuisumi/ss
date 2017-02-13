package com.haoyu.sip.diary.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.web.AbstractBaseController;
import com.haoyu.sip.diary.entity.DiaryCategory;
import com.haoyu.sip.diary.service.IDiaryCategoryService;
@Controller
@RequestMapping("**/diaryCategory")
public class DiaryCategoryController extends AbstractBaseController {
	
	@Resource
	private IDiaryCategoryService diaryCategoryService;
	
	protected String getLogicalViewNamePrefix(){
		return "/diaryCategory/";
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public String list(DiaryCategory diaryCategory ,Model model){
		model.addAttribute("diaryCategory" ,diaryCategory);
		getPageBounds(10, true);
		return getLogicalViewNamePrefix() + "list_diaryCategory";
	}
	
	@RequestMapping(value = "create", method=RequestMethod.GET)
	public String create(DiaryCategory diaryCategory,Model model){
		model.addAttribute("diaryCategory",diaryCategory);
		return getLogicalViewNamePrefix() + "edit_diaryCategory"; 
	}
	
	@RequestMapping(method=RequestMethod.POST)
	@ResponseBody
	public Response create(DiaryCategory diaryCategory){
		return this.diaryCategoryService.createDiaryCategory(diaryCategory);
	}
	
	@RequestMapping(value="{id}/edit", method=RequestMethod.GET)
	public String edit(DiaryCategory diaryCategory,Model model){	
		model.addAttribute("diaryCategory",diaryCategoryService.findDiaryCategoryById(diaryCategory.getId()));
		return getLogicalViewNamePrefix() + "edit_diaryCategory";
	}
	
	@RequestMapping(method=RequestMethod.PUT)
	@ResponseBody
	public Response update(DiaryCategory diaryCategory){
		return this.diaryCategoryService.updateDiaryCategory(diaryCategory);
	}
	
	@RequestMapping(method=RequestMethod.DELETE)
	@ResponseBody
	public Response delete(DiaryCategory diaryCategory){		
		return this.diaryCategoryService.deleteDiaryCategory(diaryCategory);
	}
	

}
