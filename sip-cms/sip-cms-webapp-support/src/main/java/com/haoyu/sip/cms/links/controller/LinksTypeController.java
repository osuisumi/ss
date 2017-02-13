package com.haoyu.sip.cms.links.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.haoyu.sip.cms.links.entity.LinksType;
import com.haoyu.sip.cms.links.service.ILinksTypeService;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.web.AbstractBaseController;


@Controller("CmsLinksTypeController")
@RequestMapping("/linksTypes")
public class LinksTypeController  extends AbstractBaseController{
	@Autowired
	private ILinksTypeService linksTypeService;
	
	protected String getLogicalViewNamePrefix(){
		return "/cms/backstage/links/";
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String getCreateLinksType(){
		return getLogicalViewNamePrefix()+"create_linksType";
	}
	
	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public String getEditLinksType(@PathVariable String id, Model model){
		model.addAttribute("linksType",linksTypeService.findLinksTypeById(id));
		return getLogicalViewNamePrefix()+"edit_linksType";
		
	}
	
	/**
	 * 保存新创的栏目数据 
	 * @param linksType
	 * @return
	 */
	@RequestMapping(method=RequestMethod.POST)
	@ResponseBody
	public Response createLinksType(LinksType linksType){
		return linksTypeService.createLinksType(linksType);
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public String getLinksTypes(LinksType linksType, Model model){
		List<LinksType> linksTypes =  linksTypeService.findLinksTypes(new HashMap<String, Object>(), getPageBounds(10, true));
		model.addAttribute("linksTypes", linksTypes);
		return getLogicalViewNamePrefix()+"list_linksType";
	}
	
	@RequestMapping(value = "/{id}",method=RequestMethod.PUT)
	@ResponseBody
	public Response updateLinksType(@PathVariable String id,LinksType linksType){
		linksType.setId(id);
		return linksTypeService.updateLinksType(linksType);
	}
	
	@RequestMapping(value = "/{id}",method=RequestMethod.GET)
	@ResponseBody
	public LinksType getLinksType(@PathVariable String id){
		return linksTypeService.findLinksTypeById(id);
	}
	
	@RequestMapping(value = "/{id}",method=RequestMethod.DELETE)
	@ResponseBody
	public Response deleteLinksType(@PathVariable String id){
		return linksTypeService.deleteLinksType(new LinksType(id));
	}
	
}
