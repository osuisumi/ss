package com.haoyu.sip.dynamic.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.web.AbstractBaseController;
import com.haoyu.sip.dynamic.entity.Dynamic;
import com.haoyu.sip.dynamic.service.IDynamicService;

@Controller
@RequestMapping(value = "dynamic")
public class DynamicController extends AbstractBaseController {
	@Resource
	private IDynamicService dynamicService;

	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String create() {
		return "dynamic/edit_dynamic";
	}

	@RequestMapping(value = "save", method = RequestMethod.POST)
	public @ResponseBody Response save(Dynamic dynamic) {
		if (StringUtils.isEmpty(dynamic.getId())) {
			return this.dynamicService.create(dynamic);
		} else {
			return this.dynamicService.update(dynamic);
		}

	}

	@RequestMapping(value = "{id}/view", method = RequestMethod.GET)
	public String view(Dynamic dynamic, Model model, HttpServletRequest request) {
		dynamic = dynamicService.get(dynamic.getId());
		model.addAttribute("dynamic", dynamic);
		return "dynamic/view_dynamic";
	}

	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public Response delete(Dynamic dynamic) {
		return dynamicService.delete(dynamic);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public String list(Dynamic dynamic,Model model){
		model.addAttribute("dynamics", dynamicService.list(dynamic,getPageBounds(5,true)));
		return "dynamic/list_dynamic";
	}
	
	

}
