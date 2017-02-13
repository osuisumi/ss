package com.haoyu.sip.cms.siteinfo.controller;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.haoyu.sip.cms.siteinfo.entity.SiteInfo;
import com.haoyu.sip.cms.siteinfo.service.ISiteInfoService;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.web.AbstractBaseController;


@Controller
@RequestMapping("/cms_siteInfos")
public class SiteInfoController  extends AbstractBaseController{
	@Resource
	private ISiteInfoService siteInfoService;
	
	protected String getLogicalViewNamePrefix(){
		return "/admin/cms/siteinfo/";
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String getCreateSiteInfo(){
		return getLogicalViewNamePrefix()+"create_siteinfo";
	}
	
	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public String getEditSiteInfo(@PathVariable String id, Model model){
		model.addAttribute("siteInfo",siteInfoService.findSiteInfoById(id));
		return getLogicalViewNamePrefix()+"edit_siteinfo";
		
	}
	
	/**
	 * 保存新创的站点信息 
	 * @param siteInfo
	 * @return
	 */
	@RequestMapping(method=RequestMethod.POST)
	@ResponseBody
	public Response createSiteInfo(SiteInfo siteInfo){
		return siteInfoService.createSiteInfo(siteInfo);
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public List<SiteInfo> getSiteInfos(SiteInfo siteInfo, Model model){
		return siteInfoService.findSiteInfos(new HashMap<String, Object>(), getPageBounds(10, true));
	}
	
	@RequestMapping(value = "/list",method=RequestMethod.GET)
	public String siteInfoList(Model model){
		setParameterToModel(request, model);
		return getLogicalViewNamePrefix()+"list_siteinfo";
	}
	
	@RequestMapping(value = "/{id}",method=RequestMethod.PUT)
	@ResponseBody
	public Response updateSiteInfo(@PathVariable String id,SiteInfo siteInfo){
		siteInfo.setId(id);
		return siteInfoService.updateSiteInfo(siteInfo);
	}
	
	@RequestMapping(value = "/{id}",method=RequestMethod.GET)
	@ResponseBody
	public SiteInfo getSiteInfo(@PathVariable String id){
		return siteInfoService.findSiteInfoById(id);
	}
	
	@RequestMapping(value = "/{id}",method=RequestMethod.DELETE)
	@ResponseBody
	public Response deleteSiteInfo(@PathVariable String id){
		return siteInfoService.deleteSiteInfo(new SiteInfo(id));
	}
	
}
