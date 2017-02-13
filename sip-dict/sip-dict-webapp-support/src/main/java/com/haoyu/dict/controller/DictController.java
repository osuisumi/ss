package com.haoyu.dict.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.haoyu.dict.entity.DictEntry;
import com.haoyu.dict.service.IDictEntryService;
import com.haoyu.dict.utils.DictionaryUtils;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.web.AbstractBaseController;

@Controller
@RequestMapping("**/dict")
public class DictController extends AbstractBaseController{
	
	@Resource
	private IDictEntryService dictEntryService;
	
	@RequestMapping("getEntryList")
	@ResponseBody
	public List<DictEntry> getEntryList(DictEntry dictEntry){
		return DictionaryUtils.getEntryList(dictEntry.getDictTypeCode());
	}
	
	@RequestMapping("flushCache")
	@ResponseBody
	public Response flushCache(){
		DictionaryUtils.initAll();
		return Response.successInstance();
	}
	
	@RequestMapping(value="create", method=RequestMethod.GET)
	public String create(DictEntry dictEntry, Model model){
		model.addAttribute("dictEntry", dictEntry);
		return "common/dict/edit_dict_entry";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	@ResponseBody
	public Response createDictEntry(DictEntry dictEntry){
		return dictEntryService.create(dictEntry);
	}

}
