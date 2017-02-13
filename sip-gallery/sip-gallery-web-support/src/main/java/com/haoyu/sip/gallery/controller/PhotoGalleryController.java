/**
 * 
 */
package com.haoyu.sip.gallery.controller;

import java.util.List;

import javax.annotation.Resource;



import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.haoyu.sip.gallery.entity.PhotoGallery;
import com.haoyu.sip.gallery.service.IPhotoGalleryService;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.web.AbstractBaseController;

/**
 * @author lianghuahuang
 *
 */
@Controller
@RequestMapping("/gallery/photoGalleries")
public class PhotoGalleryController extends AbstractBaseController{
	@Resource
	private IPhotoGalleryService photoGalleryService;
	
	protected String getLogicalViewNamePrefix(){
		return "/admin/common/gallery/photoGallery/";
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String getCreatePhotoGallery(String relationId,Model model){
		model.addAttribute("relationId", relationId);
		return getLogicalViewNamePrefix()+"create_photoGallery";
	}
	
	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public String getEditPhotoGallery(@PathVariable String id, Model model){
		model.addAttribute("photoGallery",photoGalleryService.findPhotoGalleryById(id));
		return getLogicalViewNamePrefix()+"edit_photoGallery";		
	}
	
	@RequestMapping(value = "/{id}/galleryPhotos", method = RequestMethod.GET)
	public String getGalleryPhotos(@PathVariable String id, Model model){
		String returnUri = request.getParameter("returnUri");
		if(StringUtils.isNotEmpty(returnUri)){
			model.addAttribute("returnUri", returnUri);
		}
		model.addAttribute("photoGallery",photoGalleryService.findPhotoGalleryById(id));
		return getLogicalViewNamePrefix()+"galleryPhotos";
	}
	
	@RequestMapping(value = "/{id}/addPhotos", method = RequestMethod.GET)
	public String getAddPhotosToGallery(@PathVariable String id, Model model){
		model.addAttribute("photoGallery",photoGalleryService.findPhotoGalleryById(id));
		return getLogicalViewNamePrefix()+"add_photosToGallery";
	}
	
	@RequestMapping(value = "/{id}/addPhotos", method = RequestMethod.POST)
	@ResponseBody
	public Response addPhotosToGallery(@PathVariable String id, PhotoGallery photoGallery){
		Response response = photoGalleryService.addPhotosToGallery(id, photoGallery.getPhotos());
		return response;
	}
	
	@RequestMapping(value = "/{id}/removePhotos", method = RequestMethod.DELETE)
	@ResponseBody
	public Response removePhotosToGallery(@PathVariable String id, PhotoGallery photoGallery){
		Response response = photoGalleryService.addPhotosToGallery(id, photoGallery.getPhotos());
		return response;
	}
	
	@RequestMapping(value = "/{id}/updatePhotos", method = RequestMethod.PUT)
	@ResponseBody
	public Response updatePhotosToGallery(@PathVariable String id, PhotoGallery photoGallery){
		Response response = photoGalleryService.updatePhotosToGallery(id, photoGallery.getPhotos());
		return response;
	}
	
	/**
	 * 保存新创的相册数据 
	 * @param photoGallery
	 * @return
	 */
	@RequestMapping(method=RequestMethod.POST)
	@ResponseBody
	public Response createPhotoGallery(PhotoGallery photoGallery){
		return photoGalleryService.createPhotoGallery(photoGallery);
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public String getPhotoGalleries(PhotoGallery photoGallery, Model model){
		List<PhotoGallery> photoGalleries =  photoGalleryService.findPhotoGalleries(photoGallery, getPageBounds(10, true));
		model.addAttribute("photoGalleries", photoGalleries);
		return getLogicalViewNamePrefix()+"list_photoGallery";
	}
	
	
	@RequestMapping(value = "/api",method=RequestMethod.GET)
	@ResponseBody
	public List<PhotoGallery> getPhotoGalleries(PhotoGallery photoGallery){
		return photoGalleryService.findPhotoGalleries(photoGallery, getPageBounds(10, false));
	}
	
	@RequestMapping(value = "/{id}",method=RequestMethod.PUT)
	@ResponseBody
	public Response updatePhotoGallery(@PathVariable String id,PhotoGallery photoGallery){
		photoGallery.setId(id);
		return photoGalleryService.updatePhotoGallery(photoGallery);
	}
	
	@RequestMapping(value = "/{id}",method=RequestMethod.GET)
	@ResponseBody
	public PhotoGallery getPhotoGallery(@PathVariable String id){
		return photoGalleryService.findPhotoGalleryById(id);
	}
	@RequestMapping(value = "/{id}",method=RequestMethod.DELETE)
	@ResponseBody
	public Response deletePhotoGallery(@PathVariable String id){
		return photoGalleryService.deletePhotoGallery(new PhotoGallery(id));
	}
}
