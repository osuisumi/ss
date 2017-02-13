/**
 * 
 */
package com.haoyu.sip.cms.template.controller;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.haoyu.sip.cms.siteinfo.entity.SiteInfo;
import com.haoyu.sip.cms.template.entity.TemplateFile;
import com.haoyu.sip.cms.template.service.ITemplateFileService;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.web.AbstractBaseController;

/**
 * @author lianghuahuang
 *
 */
@Controller
@RequestMapping("/cms_templates")
public class TemplateFileController extends AbstractBaseController{
	/*@Resource
	private ITemplateService templateService;*/
	@Resource
	private ITemplateFileService templateFileService;
	
	
	protected String getLogicalViewNamePrefix(){
		return "/admin/cms/template/";
	}
	
	@RequestMapping(value="/channels/{type}",method=RequestMethod.GET)
	public String getChannelTemplates(@PathVariable String type,Model model){
		model.addAttribute("type",type);
		return getLogicalViewNamePrefix()+"channel_template";
	}
	
	@RequestMapping(value="/channels/{type}/{channelId}",method=RequestMethod.GET)
	public String getChannelTemplate(@PathVariable String type,@PathVariable String channelId,Model model){
		model.addAttribute("type",type);
		model.addAttribute("id", channelId);
		return getLogicalViewNamePrefix()+"edit_template";
	}
	
	@RequestMapping(value = "/channels/{type}/{channelId}",method=RequestMethod.POST)
	@ResponseBody
	public Response createTemplateFile(@PathVariable String type,@PathVariable String channelId,TemplateFile templateFile){
		return templateFileService.createTemplateFile(type,channelId,templateFile);
	}
	
	@RequestMapping(value = "/channels/{type}/{channelId}",method=RequestMethod.PUT)
	@ResponseBody
	public Response updateTemplateFile(@PathVariable String type,@PathVariable String channelId,TemplateFile templateFile){
		return templateFileService.updateTemplateFile(type,channelId,templateFile);
	}
	
	@RequestMapping(value = "/channels/{type}/{id}",method=RequestMethod.DELETE)
	@ResponseBody
	public Response deleteTemplateFile(@PathVariable String type,@PathVariable String id){
		return templateFileService.deleteTemplateFile(type,id);
	}

	
	 
	
}
