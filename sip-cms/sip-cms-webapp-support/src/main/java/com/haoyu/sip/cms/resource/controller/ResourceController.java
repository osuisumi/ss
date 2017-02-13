/**
 * 
 */
package com.haoyu.sip.cms.resource.controller;

import java.util.List;



import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.web.AbstractBaseController;
import com.haoyu.sip.cms.resource.entity.Resource;
import com.haoyu.sip.cms.resource.service.IResourceService;

/**
 * @author lianghuahuang
 *
 */
@Controller
@RequestMapping("/cms_resources")
public class ResourceController extends AbstractBaseController {
	
	@javax.annotation.Resource
	private IResourceService resourceService;
	
	protected String getLogicalViewNamePrefix(){
		return "/admin/cms/resource/";
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String getCreateResource(Resource resource, Model model){
		if(resource!=null){
			model.addAttribute("resource", resource);
		}
		return getLogicalViewNamePrefix()+"create_resource";
	}
	
	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public String getEditResource(@PathVariable String id, Model model){
		model.addAttribute("resource",resourceService.findResourceById(id));
		return getLogicalViewNamePrefix()+"edit_resource";		
	}
	
	
	/**
	 * 保存新创的相册数据 
	 * @param resource
	 * @return
	 */
	@RequestMapping(method=RequestMethod.POST)
	@ResponseBody
	public Response createResource(Resource resource){
		return resourceService.createResource(resource);
	}
	
	@RequestMapping(value="list",method=RequestMethod.GET)
	public String getResourceList(Resource resource, Model model){
		List<Resource> resources =  resourceService.findResources(resource, getPageBounds(10, true));
		model.addAttribute("resources", resources);
		return getLogicalViewNamePrefix()+"list_resource";
	}
	
	
	@RequestMapping(method=RequestMethod.GET)
	@ResponseBody
	public List<Resource> getResources(Resource resource){
		return resourceService.findResources(resource, getPageBounds(10, false));
	}
	
	@RequestMapping(value = "/{id}",method=RequestMethod.PUT)
	@ResponseBody
	public Response updateResource(@PathVariable String id,Resource resource){
		resource.setId(id);
		return resourceService.updateResource(resource);
	}
	
	@RequestMapping(value = "/{id}",method=RequestMethod.POST)
	@ResponseBody
	public Response createResource(@PathVariable String id,Resource resource){
		resource.setId(id);
		return resourceService.createResource(resource);
	}
	
	@RequestMapping(value = "/{id}",method=RequestMethod.GET)
	@ResponseBody
	public Resource getResource(@PathVariable String id){
		return resourceService.findResourceById(id);
	}
	@RequestMapping(value = "/{id}",method=RequestMethod.DELETE)
	@ResponseBody
	public Response deleteResource(@PathVariable String id){
		return resourceService.deleteResource(new Resource(id));
	}
}
