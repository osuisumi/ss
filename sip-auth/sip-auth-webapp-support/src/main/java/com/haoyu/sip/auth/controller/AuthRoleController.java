/**
 * 
 */
package com.haoyu.sip.auth.controller;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.google.common.collect.Lists;
import com.haoyu.sip.auth.entity.AuthMenu;
import com.haoyu.sip.auth.entity.AuthPermission;
import com.haoyu.sip.auth.entity.AuthRole;
import com.haoyu.sip.auth.entity.AuthUser;
import com.haoyu.sip.auth.service.IAuthMenuService;
import com.haoyu.sip.auth.service.IAuthPermissionService;
import com.haoyu.sip.auth.service.IAuthRoleService;
import com.haoyu.sip.auth.service.IAuthUserService;
import com.haoyu.sip.core.entity.User;
import com.haoyu.sip.core.mapper.JsonMapper;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.utils.ThreadContext;
import com.haoyu.sip.core.web.AbstractBaseController;

/**
 * @author lianghuahuang
 *
 */
@Controller
@RequestMapping("/auth_roles")
public class AuthRoleController extends AbstractBaseController {
	
	@Resource
	private IAuthRoleService authRoleService;
	
	@Resource
	private IAuthUserService authUserService;
	
	@Resource
	private IAuthMenuService authMenuService;
	
	@Resource
	private IAuthPermissionService authPermissionService;
	
	protected String getLogicalViewNamePrefix(){
		return "/admin/common/auth/role/";
	}
	
	@RequestMapping(method=RequestMethod.GET)
	@ResponseBody
	public List<AuthRole> getAuthUser(){
		return authRoleService.findRoleByNameAndRelation("", "");
	}
	
	@RequestMapping(value="/{roleId}/auth_users/{relationId}",method=RequestMethod.GET)
	@ResponseBody
	public List<AuthUser> getAuthUser(@PathVariable String roleId,@PathVariable String relationId){
		return authUserService.findAuthUserByRoleAndRelation(new AuthRole(roleId), relationId);
	}
	
	@RequestMapping(value="/{roleId}/auth_users/{relationId}",method={RequestMethod.PUT,RequestMethod.POST})
	@ResponseBody
	public Response updateAuthRoleUsers(@PathVariable String roleId,@PathVariable String relationId,HttpServletRequest request){
		String[] userIds = request.getParameterValues("userIds");
		return authRoleService.updateAuthRoleUsers(new AuthRole(roleId), Lists.newArrayList(userIds), relationId);
	}
	
	@RequestMapping(value="/{roleId}/auth_users/{relationId}",method=RequestMethod.DELETE)
	@ResponseBody
	public Response removeUsersFromRole(@PathVariable String roleId,@PathVariable String relationId,List<String> userIds){
		return authRoleService.removeUsersFromRole(new AuthRole(roleId), userIds, relationId);
	}
	
	@RequestMapping(value="create",method=RequestMethod.GET)
	public String getCreateAuthRole(String relationId,Model model){
		model.addAttribute("relationId", relationId);
		return getLogicalViewNamePrefix() + "create_role";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	@ResponseBody
	public Response createAuthRole(AuthRole authRole){
		return authRoleService.createRole(authRole);
	}
	
	
	@RequestMapping(value="save",method=RequestMethod.POST)
	@ResponseBody
	public Response save(AuthRole role){
		return this.authRoleService.createRole(role);
	}
	
	@RequestMapping(value="batch/delete")
	@ResponseBody
	public Response batchDeleteById(@RequestParam("id")String ids){
		return this.authRoleService.batchDeleteByIds(ids);
	}
	@RequestMapping(value="{id}/edit",method=RequestMethod.GET)
	public String edit(@PathVariable String id ,Model model){
		model.addAttribute("role",this.authRoleService.findRoleById(id));
		return getLogicalViewNamePrefix() + "edit_role";
	}
	
	@RequestMapping(value="{id}",method=RequestMethod.PUT)
	@ResponseBody
	public Response updateAuthRole(@PathVariable String id,AuthRole role){
		return this.authRoleService.updateRole(role);
	}
	
	@RequestMapping(value="{id}",method=RequestMethod.DELETE)
	@ResponseBody
	public Response deleteAuthRole(@PathVariable String id){
		return this.authRoleService.deleteRole(new AuthRole(id));
	}
	
	@RequestMapping(value="list",method=RequestMethod.GET)
	public String list(AuthRole authRole,Model model){
		setParameterToModel(request, model);
		model.addAttribute("authRole", authRole);
		return getLogicalViewNamePrefix() + "list_role";
	}
	//返回授权页面,带上已有menu的id
	@RequestMapping(value="{id}/editRoleMenu")
	public String editRoleMenu(@PathVariable String id,Model model){
		model.addAttribute("id", id);
		List<AuthRole> roles = Lists.newArrayList(new AuthRole(id));
		List<AuthMenu> authMenus = this.authMenuService.findMenuByRoles(roles, false);
		List<String> menuIds = new ArrayList<String>();
		for(AuthMenu am:authMenus){
			menuIds.add(am.getId());
		}
		model.addAttribute("menuIds", new JsonMapper().toJson(menuIds));
		return getLogicalViewNamePrefix() + "edit_role_menu";
	}
	
	//返回permission授权页面，带上已有的permissionid
	@RequestMapping(value="{id}/editRolePermission")
	public String editRolePermission(@PathVariable String id,Model model){
		model.addAttribute("id", id);
		List<AuthRole> roles = Lists.newArrayList(new AuthRole(id));
		List<AuthPermission> permissions = authPermissionService.findPermissionByRoles(roles);
		List<String> permissionIds = Lists.newArrayList();
		if(permissions!=null && permissions.size()>0){
			for(AuthPermission p:permissions){
				permissionIds.add(p.getId());
			}
		}
		model.addAttribute("permissionIds", new JsonMapper().toJson(permissionIds));
		return getLogicalViewNamePrefix() + "edit_role_permission";
	}
	
	@RequestMapping(value="{id}/grantMenuToRole",method = RequestMethod.PUT)
	@ResponseBody
	public Response grantMenuToRole(@PathVariable String id,AuthRole role){
		return authRoleService.grantMenuToRole(role);
	}
	
	@RequestMapping(value="{id}/grantPermissionToRole",method = RequestMethod.PUT)
	@ResponseBody
	public Response grantPermissionToRole(@PathVariable String id,AuthRole role){
		return authRoleService.grantPermissionToRole(role);
	}
}
