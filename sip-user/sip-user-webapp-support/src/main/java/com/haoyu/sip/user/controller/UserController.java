package com.haoyu.sip.user.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.web.AbstractBaseController;
import com.haoyu.sip.core.web.SearchParam;
import com.haoyu.sip.user.entity.Account;
import com.haoyu.sip.user.entity.UserInfo;
import com.haoyu.sip.user.service.IUserInfoService;

@Controller
@RequestMapping("**/users")
public class UserController extends AbstractBaseController {

	@Resource
	private IUserInfoService userService;
	
	protected String getLogicalViewNamePrefix(){
		return "/user/";
	}
	
	@RequestMapping(value="{id}/edit", method=RequestMethod.GET)
	public String edit(UserInfo user,Model model){	
		model.addAttribute("user",userService.selectUserInfoById(user.getId()));
		return getLogicalViewNamePrefix() + "edit_user";
	}
	
	@RequestMapping(method=RequestMethod.PUT)
	@ResponseBody
	public Response update(UserInfo user){
		return this.userService.updateUser(user);
	}
	
	@RequestMapping("entities")
	@ResponseBody
	public List<UserInfo> entities(SearchParam searchParam){
		return userService.list(searchParam, getPageBounds(10, true));
	}
	
	@RequestMapping(value="countForValidpaperworkNoIsExist",method = RequestMethod.GET)
	@ResponseBody
	public int countForValidpaperworkNoIsExist(UserInfo userInfo){
		return this.userService.countForValidpaperworkNoIsExist(userInfo);
	}
	
}
