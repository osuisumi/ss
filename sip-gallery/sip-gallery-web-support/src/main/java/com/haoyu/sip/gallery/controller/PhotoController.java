/**
 * 
 */
package com.haoyu.sip.gallery.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.web.AbstractBaseController;
import com.haoyu.sip.gallery.entity.Photo;
import com.haoyu.sip.gallery.service.IPhotoGalleryService;
import com.haoyu.sip.gallery.service.IPhotoService;

/**
 * @author lianghuahuang
 *
 */
@Controller
@RequestMapping("/gallery/photos")
public class PhotoController extends AbstractBaseController {
	@Resource
	private IPhotoService photoService;
	
	
	protected String getLogicalViewNamePrefix(){
		return "/admin/common/gallery/photo/";
	}
	
	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public String getEditPhoto(@PathVariable String id, Model model){
		model.addAttribute("photo",photoService.findPhotoById(id));
		return getLogicalViewNamePrefix()+"edit_photo";		
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public Response updatePhoto(@PathVariable String id,Photo photo){
		return photoService.updatePhoto(photo);
	}
}
