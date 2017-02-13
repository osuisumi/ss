package com.haoyu.sip.cms.banner.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.haoyu.sip.cms.banner.entity.Banner;
import com.haoyu.sip.cms.banner.entity.ListBanner;
import com.haoyu.sip.cms.banner.service.IBannerService;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.web.AbstractBaseController;


@Controller("CmsBannerController")
@RequestMapping("cms_banners")
public class BannerController  extends AbstractBaseController{
	@Autowired
	private IBannerService bannerService;
	
	protected String getLogicalViewNamePrefix(){
		return "/admin/cms/banner/";
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String getCreateBanner(){
		return getLogicalViewNamePrefix()+"create_banner";
	}
	
	@RequestMapping(value = "/batch_create/{relationId}", method = RequestMethod.GET)
	public String getCreateBanners(@PathVariable String relationId,Model model){
		model.addAttribute("relationId", relationId);
		return getLogicalViewNamePrefix()+"create_banners";
	}
	
	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public String getEditBanner(@PathVariable String id, Model model){
		model.addAttribute("banner",bannerService.findBannerById(id));
		return getLogicalViewNamePrefix()+"edit_banner";
		
	}
	
	/**
	 * 保存新创的栏目数据 
	 * @param banner
	 * @return
	 */
	@RequestMapping(method=RequestMethod.POST)
	@ResponseBody
	public Response createBanner(Banner banner){
		return bannerService.createBanner(banner);
	}
	
	/**
	 * 保存新创的栏目数据 
	 * @param banner
	 * @return
	 */
	@RequestMapping(value="/batch_create/{relationId}",method=RequestMethod.POST)
	@ResponseBody
	public Response createBanners(@PathVariable String relationId,ListBanner listBanner){
		return bannerService.createBanners(listBanner.fileInfosToBanners(relationId));
	}
	
	/**
	 * 保存新创的栏目数据 
	 * @param banner
	 * @return
	 */
	@RequestMapping(value="/batch_update",method=RequestMethod.PUT)
	@ResponseBody
	public Response updateBanners(ListBanner listBanner){
		return bannerService.updateBanners(listBanner.getBanners());
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public String getBanners(Banner banner, Model model){
		List<Banner> banners =  bannerService.findBanners(banner);
		model.addAttribute("banners", banners);
		return getLogicalViewNamePrefix()+"list_banner";
	}
	
	@RequestMapping(value = "/{id}",method=RequestMethod.PUT)
	@ResponseBody
	public Response updateBanner(@PathVariable String id,Banner banner){
		banner.setId(id);
		return bannerService.updateBanner(banner);
	}
	
	@RequestMapping(value = "/{id}",method=RequestMethod.GET)
	@ResponseBody
	public Banner getBanner(@PathVariable String id){
		return bannerService.findBannerById(id);
	}
	
	@RequestMapping(value = "/{id}",method=RequestMethod.DELETE)
	@ResponseBody
	public Response deleteBanner(@PathVariable String id){
		return bannerService.deleteBanner(new Banner(id));
	}
	
}
