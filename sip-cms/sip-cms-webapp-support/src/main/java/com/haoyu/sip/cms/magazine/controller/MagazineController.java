/**
 * 
 */
package com.haoyu.sip.cms.magazine.controller;


import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.haoyu.sip.cms.magazine.entity.Magazine;
import com.haoyu.sip.cms.magazine.service.IMagazineService;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.web.AbstractBaseController;

/**
 * @author lianghuahuang
 *
 */
@Controller
@RequestMapping("/magazines")
public class MagazineController extends AbstractBaseController {
	
	@Resource
	private IMagazineService magazineService;
	
	protected String getLogicalViewNamePrefix(){
		return "/gallery/photoGallery/";
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String getCreateMagazine(Magazine magazine,Model model){
		if(magazine.getType()!=null&&magazine.getType().equals("pdf")){
			model.addAttribute("type",magazine.getType());
			return "/resource/create_magazine";
		}
		return getLogicalViewNamePrefix()+"create_magazine";
	}
	
	/**
	 * 保存新创的相册数据 
	 * @param photoGallery
	 * @return
	 */
	@RequestMapping(method=RequestMethod.POST)
	@ResponseBody
	public Response createMagazine(Magazine magazine){
		return magazineService.createMagazine(magazine);
	}
	
}
