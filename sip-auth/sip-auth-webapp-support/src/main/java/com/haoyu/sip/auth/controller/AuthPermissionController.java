package com.haoyu.sip.auth.controller;




import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.haoyu.sip.auth.entity.AuthPermission;
import com.haoyu.sip.auth.service.IAuthPermissionService;
import com.haoyu.sip.auth.service.IAuthResourceService;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.web.AbstractBaseController;

@RequestMapping("auth_permissions")
@Controller
public class AuthPermissionController extends AbstractBaseController {
	
	@Resource
	private IAuthResourceService authResourceService;
	@Resource
	private IAuthPermissionService permissionService;
	
	protected String getLogicalViewNamePrefix(){
		return "/admin/common/auth/permission/";
	}
	
	@RequestMapping(value="create/{resourceId}",method = RequestMethod.GET)
	public String getCreateAuthPermission(@PathVariable String resourceId,Model model){
		model.addAttribute("resourceId", resourceId);
		return getLogicalViewNamePrefix() + "create_permission";
	}
	
	@RequestMapping(value="resource_permissions",method = RequestMethod.GET)
	public String getAuthResourcePermission(Model model){
		this.setParameterToModel(request, model);
		return getLogicalViewNamePrefix() + "resource_permission";
	}
	
	@RequestMapping(value="resource_permission_tree",method = RequestMethod.GET)
	public String getAuthResourcePermissionTree(Model model){
		this.setParameterToModel(request, model);
		return getLogicalViewNamePrefix() + "tree_resource_permission";
	}
	
	@RequestMapping(value="resource_permissions/{resourceId}",method = RequestMethod.GET)
	public String getAuthResourcePermission(@PathVariable String resourceId,Model model){
		this.setParameterToModel(request, model);
		model.addAttribute("resourceId", resourceId);
		return getLogicalViewNamePrefix() + "resource_permission";
	}
	
	@RequestMapping(value="{id}/edit",method = RequestMethod.GET)
	public String getEditAuthPermission(@PathVariable String id,Model model){
		model.addAttribute("authPermission", permissionService.findPermissionById(id));
		return getLogicalViewNamePrefix() + "edit_permission";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public Response createAuthPermission(AuthPermission permission){
		return permissionService.createPermission(permission);
	}
	
	@RequestMapping(value="batch/delete")
	@ResponseBody
	public Response batchDeleteByNodes(@RequestParam("id")String ids){
		return permissionService.batchDeleteByIds(ids);
	}
	
	@RequestMapping(value="{id}",method=RequestMethod.PUT)
	@ResponseBody
	public Response updateAuthPermission(@PathVariable String id,AuthPermission permission){
		return this.permissionService.updatePermission(permission);
	}
	
	@RequestMapping(value="{id}",method=RequestMethod.DELETE)
	@ResponseBody
	public Response deleteAuthPermission(@PathVariable String id){
		AuthPermission ap = new AuthPermission();
		ap.setId(id);
		return this.permissionService.deletePermission(ap);
	}

	@RequestMapping(value="list",method=RequestMethod.GET)
	public String listAuthPermission(AuthPermission permission,Model model){
		model.addAttribute("permissions", permissionService.list(permission, getPageBounds(10, true)));
		return getLogicalViewNamePrefix() + "list_permission";
	}
	
}
