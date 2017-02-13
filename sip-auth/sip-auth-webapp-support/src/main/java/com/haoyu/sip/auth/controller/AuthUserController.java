/**
 * 
 */
package com.haoyu.sip.auth.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Lists;
import com.haoyu.sip.auth.entity.AuthMenu;
import com.haoyu.sip.auth.entity.AuthRole;
import com.haoyu.sip.auth.entity.AuthUser;
import com.haoyu.sip.auth.service.IAuthRoleService;
import com.haoyu.sip.auth.service.IAuthUserService;
import com.haoyu.sip.core.mapper.JsonMapper;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.utils.ThreadContext;
import com.haoyu.sip.core.web.AbstractBaseController;

/**
 * @author lianghuahuang
 *
 */
@Controller
@RequestMapping("/auth_users")
public class AuthUserController extends AbstractBaseController {
	@Resource
	private IAuthUserService authUserService;
	
	@Resource
	private IAuthRoleService authRoleService;
	
	protected String getLogicalViewNamePrefix(){
		return "/admin/common/auth/user/";
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String getCreateAuthUser(Model model){
		return getLogicalViewNamePrefix()+"create_user";
	}
	
	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public String getEditAuthUser(@PathVariable String id, Model model){
		model.addAttribute("authUser",authUserService.findAuthUserById(id));
		return getLogicalViewNamePrefix()+"edit_user";
		
	}
	
	
	@RequestMapping(method=RequestMethod.POST)
	@ResponseBody
	public Response createAuthUser(AuthUser user){
		return authUserService.createAuthUser(user);
	}
	
	@RequestMapping(method=RequestMethod.GET)
	@ResponseBody
	public List<AuthUser> getAuthUsers(AuthUser user){
		return authUserService.findAuthUsers(user, this.getPageBounds(10, true));
	}
	
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public String getAuthUserList(AuthUser user, Model model){
		setParameterToModel(request,model);
		model.addAttribute("authUser", user);
		return getLogicalViewNamePrefix()+"list_user";
	}
	
	@RequestMapping(value = "/{id}",method=RequestMethod.PUT)
	@ResponseBody
	public Response updateAuthUser(@PathVariable String id,AuthUser user){
		user.setId(id);
		return authUserService.updateAuthUser(user);
	}
	
	@RequestMapping(value = "/{id}",method=RequestMethod.GET)
	@ResponseBody
	public AuthUser getAuthUser(@PathVariable String id){
		return authUserService.findAuthUserById(id);
	}
	
	@RequestMapping(value = "/{id}",method=RequestMethod.DELETE)
	@ResponseBody
	public Response deleteAuthUser(@PathVariable String id){
		return authUserService.deleteAuthUser(new AuthUser(id));
	}
	
	@RequestMapping(value="{id}/editUserRole")
	public String editAuthUserRole(@PathVariable String id,Model model){
		model.addAttribute("id", id);
		List<AuthRole> authRoles = authRoleService.findRoleByAuthUser(new AuthUser(id));
		List<String> roleIds = new ArrayList<String>();
		for(AuthRole ar:authRoles){
			roleIds.add(ar.getId());
		}
		model.addAttribute("roleIds", new JsonMapper().toJson(roleIds));
		return getLogicalViewNamePrefix() + "edit_user_role";
	}
	
	@RequestMapping(value = "/{id}/grantRoleToUser",method=RequestMethod.PUT)
	@ResponseBody
	public Response  grantAuthUserRole(@PathVariable String id,AuthUser authUser){
		return authUserService.grantRoleToUser(authUser);
	}
	
	@RequestMapping(value = "/editPersonalPassword",method=RequestMethod.GET)
	public String getEditPersonalPassword(){
		return getLogicalViewNamePrefix()+"edit_personal_password";
	}
	
	@RequestMapping(value = "/updatePersonalPassword",method=RequestMethod.PUT)
	@ResponseBody
	public Response updatePersonalPassword(String sourcePassword,String newPassword){
		return authUserService.updatePassword(ThreadContext.getUser().getId(), sourcePassword, newPassword);
	}
}
