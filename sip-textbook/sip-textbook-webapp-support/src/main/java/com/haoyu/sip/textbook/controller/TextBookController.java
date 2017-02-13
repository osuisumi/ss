package com.haoyu.sip.textbook.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.haoyu.sip.core.mapper.JsonMapper;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.textbook.entity.TextBookEntry;
import com.haoyu.sip.textbook.service.ITextBookRelationService;
import com.haoyu.sip.textbook.utils.TextBookParam;
import com.haoyu.sip.textbook.utils.TextBookUtils;

@Controller
@RequestMapping(value = "textBook")
public class TextBookController {
	@Resource
	private ITextBookRelationService textBookRelationService;

	@RequestMapping(value = "getEntryOptions")
	@ResponseBody
	public String getEntryOptions(String textBookTypeCode) {
		return TextBookUtils.getEntryOptions(textBookTypeCode);
	}

	@RequestMapping(value = "getEntryOptionsByEntity")
	@ResponseBody
	public String getEntryOptions(TextBookParam textBookParam) {
		return TextBookUtils.getOptionsString(TextBookUtils.getEntryList(textBookParam));
	}
	
	@RequestMapping(value="getEntryName")
	@ResponseBody
	public String getEntryName(String textBookTypeCode,String textBookValue){
		return TextBookUtils.getEntryName(textBookTypeCode, textBookValue);
	}

	@RequestMapping(value="getEntryList")
	@ResponseBody
	public List<TextBookEntry> listTextBookEntry(String textBookTypeCode){
		return TextBookUtils.getEntryList(textBookTypeCode);
	}
	

	@RequestMapping(value="getEntryListByEntity")
	@ResponseBody
	public List<TextBookEntry> listTextBookEntry(TextBookParam textBookParam){
		return TextBookUtils.getEntryList(textBookParam);
	}
	
	@RequestMapping(value="getEntryListJson")
	@ResponseBody
	public String getEntryListJson(String textBookTypeCode){
		return new JsonMapper().toJson(TextBookUtils.getEntryList(textBookTypeCode));
	}

	@RequestMapping(value="getEntryListJsonByEntity")   
	@ResponseBody
	public String getEntryListJsonByEntity(TextBookParam textBookParam){
		return new JsonMapper().toJson(TextBookUtils.getEntryList(textBookParam));
	}
	
	@RequestMapping(value="goImport")  
	public String goImport(){
		return "textbook/import_textBook";
	}
	
	@RequestMapping(value="importTextBook")
	public String importTextbook(String url,Model model){
		model.addAttribute("resultMap",textBookRelationService.importUtil(url));
		return "textbook/import_result"; 
	}
	
	@RequestMapping(value="flushCache")
	@ResponseBody
	public Response flushCache(){
		TextBookUtils.initAll();
		return Response.successInstance();
	}
	
}
