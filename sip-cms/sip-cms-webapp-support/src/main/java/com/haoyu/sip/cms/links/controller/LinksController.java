package com.haoyu.sip.cms.links.controller;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Maps;
import com.haoyu.sip.cms.links.entity.Links;
import com.haoyu.sip.cms.links.service.ILinksService;
import com.haoyu.sip.cms.links.service.ILinksTypeService;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.web.AbstractBaseController;


@Controller("CmsLinksController")
@RequestMapping("/links")
public class LinksController  extends AbstractBaseController{
	@Resource
	private ILinksService linksService;
	
	@Resource
	private ILinksTypeService linksTypeService;
	
	protected String getLogicalViewNamePrefix(){
		return "/cms/backstage/links/";
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String getCreateLinks(Model model){
		model.addAttribute("linksTypes",linksTypeService.findLinksTypes(Maps.newHashMap()));
		return getLogicalViewNamePrefix()+"create_links";
	}
	
	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public String getEditLinks(@PathVariable String id, Model model){
		model.addAttribute("links",linksService.findLinksById(id));
		model.addAttribute("linksTypes",linksTypeService.findLinksTypes(Maps.newHashMap()));
		return getLogicalViewNamePrefix()+"edit_links";
		
	}
	
	/**
	 * 保存新创的栏目数据 
	 * @param links
	 * @return
	 */
	@RequestMapping(method=RequestMethod.POST)
	@ResponseBody
	public Response createLinks(Links links){
		return linksService.createLinks(links);
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public String getLinks(Links links, Model model){
		List<Links> linkss =  linksService.findLinks(new HashMap<String, Object>(), getPageBounds(10, true));
		model.addAttribute("linkss", linkss);
		return getLogicalViewNamePrefix()+"list_links";
	}
	
	@RequestMapping(value = "/{id}",method=RequestMethod.PUT)
	@ResponseBody
	public Response updateLinks(@PathVariable String id,Links links){
		links.setId(id);
		return linksService.updateLinks(links);
	}
	
	@RequestMapping(value = "/{id}",method=RequestMethod.GET)
	@ResponseBody
	public Links getLinks(@PathVariable String id){
		return linksService.findLinksById(id);
	}
	
	@RequestMapping(value = "/{id}",method=RequestMethod.DELETE)
	@ResponseBody
	public Response deleteLinks(@PathVariable String id){
		return linksService.deleteLinks(new Links(id));
	}
	
}
