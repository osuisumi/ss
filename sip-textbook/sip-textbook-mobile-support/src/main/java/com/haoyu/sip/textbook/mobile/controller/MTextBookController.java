package com.haoyu.sip.textbook.mobile.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.web.AbstractBaseMobileController;
import com.haoyu.sip.textbook.mobile.service.IMTextBookService;
import com.haoyu.sip.textbook.utils.TextBookParam;

@Controller
@RequestMapping("**/m/textBook")
public class MTextBookController extends AbstractBaseMobileController{

	@Resource
	private IMTextBookService textBookService;
	
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public Response list(TextBookParam textBookParam){
		return textBookService.listTextBook(textBookParam);
	}
}
