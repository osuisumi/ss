/**
 * 
 */
package com.haoyu.sip.auth.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.haoyu.sip.auth.entity.AuthMenu;
import com.haoyu.sip.auth.service.IAuthMenuService;
import com.haoyu.sip.auth.service.IAuthUserService;
import com.haoyu.sip.core.entity.User;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.utils.ThreadContext;
import com.haoyu.sip.core.web.AbstractBaseController;

/**
 * @author lianghuahuang
 *
 */
@Controller
@RequestMapping("/auth_menus")
public class AuthMenuController extends AbstractBaseController {
	@Autowired
	private IAuthMenuService authMenuService;
	
	@Resource
	private IAuthUserService authUserService;
	
	protected String getLogicalViewNamePrefix(){
		return "/admin/common/auth/menu/";
	}

	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public Response createMenu(AuthMenu menu) {
		return authMenuService.createMenu(menu);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public Response updateMenu(@PathVariable String id, AuthMenu menu) {
		return authMenuService.updateMenu(menu);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public Response deleteMenu(@PathVariable String id) {
		AuthMenu menu = new AuthMenu(id);
		return authMenuService.deleteMenu(menu);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public AuthMenu getMenu(@PathVariable String id) {
		return authMenuService.findMenuById(id);
	}

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<AuthMenu> getMenus(AuthMenu authMenu) {
		return authMenuService.findMenuByNameAndRelation(authMenu.getName(), authMenu.getRelationId(), getPageBounds(10, false));
	}
	
	@RequestMapping(value="tree",method = RequestMethod.GET)
	public String menuTree(){
		return getLogicalViewNamePrefix()+"tree_menu";
	}
	
	@RequestMapping(value = "/user_menus",method = RequestMethod.GET)
	@ResponseBody
	public List<AuthMenu> getUserMenus() {
		User user = ThreadContext.getUser();
		return authUserService.findAuthMenuByUser(user!=null?user.getId():null);
	}

	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String create(Model model) {
		return getLogicalViewNamePrefix() + "create_menu";
	}
	
	@RequestMapping(value="save" ,method = RequestMethod.POST)
	@ResponseBody
	public Response save(AuthMenu menu){
		return authMenuService.createMenu(menu);
	}
	
	@RequestMapping(value="batch/delete",method = RequestMethod.DELETE)
	@ResponseBody
	public Response batchDeleteByIds(@RequestParam("id")String ids){
		return authMenuService.batchDeleteByIds(ids);
	}
	@RequestMapping(value="{id}/edit",method = RequestMethod.GET)
	public String edit(@PathVariable String id,Model model){
		model.addAttribute("authMenu", authMenuService.findMenuById(id));
		return getLogicalViewNamePrefix() + "edit_menu";
	}
	
	@RequestMapping(value="update",method = RequestMethod.PUT)
	@ResponseBody
	public Response update(AuthMenu menu){
		return authMenuService.updateMenu(menu);
	}
	
	
	@RequestMapping(value="list",method = RequestMethod.GET)
	public String list(){
		return getLogicalViewNamePrefix() + "list_menu";
	}
	
/*	@RequestMapping(value="tree",method = RequestMethod.GET)
	@ResponseBody
	public List<AuthMenu> allMenuTree(AuthMenu authMenu){
		return authMenuService.findMenu(authMenu, true);
	}*/
	
	@RequestMapping(value="init",method = RequestMethod.GET)
	public Response initMenuManager(){
		return null;
	}
	
}
