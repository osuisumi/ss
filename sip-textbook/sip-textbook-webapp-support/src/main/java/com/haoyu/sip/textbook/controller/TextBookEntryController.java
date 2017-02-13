package com.haoyu.sip.textbook.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.web.AbstractBaseController;
import com.haoyu.sip.textbook.entity.TextBookEntry;
import com.haoyu.sip.textbook.service.ITextBookEntryService;


@Controller
@RequestMapping("**/text_book_entry")
public class TextBookEntryController extends AbstractBaseController{
	@Resource
	private ITextBookEntryService textBookEntryService;
	
	@RequestMapping(value="create", method=RequestMethod.GET)
	public String create(TextBookEntry textBookEntry,Model model){
		model.addAttribute("textBookEntry", textBookEntry);
		return "common/textbook/edit_textbook_entry";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	@ResponseBody
	public Response save(TextBookEntry textBookEntry){
		return textBookEntryService.create(textBookEntry);
	}

}
