package com.haoyu.sip.auth.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.haoyu.sip.auth.entity.AuthResource;
import com.haoyu.sip.auth.service.IAuthResourceService;
import com.haoyu.sip.core.entity.User;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.utils.ThreadContext;
import com.haoyu.sip.core.web.AbstractBaseController;
@RequestMapping("auth_resources")
@Controller
public class AuthResourceController extends AbstractBaseController{
	
	@Resource
	private IAuthResourceService authResourceService;
	
	protected String getLogicalViewNamePrefix(){
		return "/admin/common/auth/resource/";
	}
	
	@RequestMapping(value="create",method = RequestMethod.GET)
	public String getCreate(){
		return getLogicalViewNamePrefix() + "create_resource";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public Response create(AuthResource resource){
		return authResourceService.createResource(resource);
	}
	
	@RequestMapping(value="{id}/edit",method = RequestMethod.GET)
	public String edit(@PathVariable String id,Model model){
		model.addAttribute("authResource",authResourceService.findResourceById(id));
		return getLogicalViewNamePrefix() + "edit_resource";
	}
	
	@RequestMapping(value="{id}",method = RequestMethod.PUT)
	@ResponseBody
	public Response updateAuthResource(@PathVariable String id,AuthResource resource){
		return authResourceService.updateResource(resource);
	}
	
	@RequestMapping(value="{id}",method = RequestMethod.DELETE)
	@ResponseBody
	public Response delete(@PathVariable String id){
		return authResourceService.deleteResource(new AuthResource(id));
	}
	
	@RequestMapping(value="batch/delete",method = RequestMethod.DELETE)
	@ResponseBody
	public Response batchDelete(String ids){
		return authResourceService.batchDeleteByIds(ids);
	}
	
	
	@RequestMapping(method=RequestMethod.GET)
	@ResponseBody
	public List<AuthResource> getResources(AuthResource resource){
		return authResourceService.findResource(resource, getPageBounds(10, false),false);
	}
	
	@RequestMapping(value="list",method = RequestMethod.GET)
	public String getResourcelist(AuthResource resource){
		return getLogicalViewNamePrefix() + "list_resource";
	}
	
	@RequestMapping(value="tree",method = RequestMethod.GET)
	public String getResourceTree(AuthResource resource){
		return getLogicalViewNamePrefix() + "tree_resource";
	}
	
	

}
