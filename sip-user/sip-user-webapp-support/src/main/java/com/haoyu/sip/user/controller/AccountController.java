package com.haoyu.sip.user.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.utils.ThreadContext;
import com.haoyu.sip.core.web.AbstractBaseController;
import com.haoyu.sip.user.entity.Account;
import com.haoyu.sip.user.service.IAccountService;
import com.haoyu.sip.user.service.IUserInfoService;

@Controller
@RequestMapping("**/accounts")
public class AccountController extends AbstractBaseController{

	@Resource
	private IAccountService accountService;
	@Resource
	private IUserInfoService userService;
	
//	protected String getLogicalViewNamePrefix(){
//		return "/account/";
//	}
//	
//	@RequestMapping(method=RequestMethod.GET)
//	public String list(Account account,Model model){
//		model.addAttribute("account",account);
//		model.addAttribute("pageBounds", getPageBounds(10, true));
//		return getLogicalViewNamePrefix() + "list_account";
//	}
//	
//	@RequestMapping(value="create",method=RequestMethod.GET)
//	public String create(Account account, Model model){
//		model.addAttribute("account",account);
//		return getLogicalViewNamePrefix() + "edit_account";
//	}
	
	@RequestMapping(method=RequestMethod.POST)
	@ResponseBody
	public Response save(Account account){
		return this.accountService.createAccount(account);
	}
	
//	@RequestMapping(value="{id}/edit",method=RequestMethod.GET)
//	public String edit(Account account,Model model){
//		model.addAttribute("account",this.accountService.findAccountById(account.getId()));
//		return getLogicalViewNamePrefix() + "edit_account";
//	}
	
	@RequestMapping(method=RequestMethod.PUT)
	@ResponseBody
	public Response update(Account account){
		return this.accountService.updateAccount(account);
	}
	

	@RequestMapping(value = "/updatePersonalPassword",method=RequestMethod.PUT)
	@ResponseBody
	public Response updatePersonalPassword(String sourcePassword,String newPassword){
		return accountService.updatePassword( sourcePassword, newPassword,ThreadContext.getUser().getId());
	}
	
	@RequestMapping(value="batch/delete",method=RequestMethod.DELETE)
	@ResponseBody
	public Response batchDeleteByIds(@RequestParam("id")String ids){
		return this.accountService.batchDeleteByIds(ids);
	}
	
	@RequestMapping(value="batch/resetPassword",method=RequestMethod.PUT)
	@ResponseBody
	public Response batchResetPasswordByIds(Account account){
		return this.accountService.batchResetPasswordByIds(account);
	}
	
	@RequestMapping(value="countForValidUserNameIsExist",method = RequestMethod.GET)
	@ResponseBody
	public int getAccountForValidUserNameIsExist(Account account){
		return this.accountService.getAccountCountForValidUserNameIsExist(account);
	}
	
//	@RequestMapping(value="/editAuthAccountRoleAuthorize",method=RequestMethod.GET)
//	public String editAccountRoleAuthorize(Account account,Model model) {
//		model.addAttribute("account",account);
//		return getLogicalViewNamePrefix() + "edit_account_role_authorize";
//	}
}
