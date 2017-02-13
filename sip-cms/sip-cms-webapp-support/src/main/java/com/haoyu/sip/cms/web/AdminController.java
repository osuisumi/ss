/**
 * 
 */
package com.haoyu.sip.cms.web;

import java.io.File;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.haoyu.sip.auth.entity.AuthUser;
import com.haoyu.sip.cms.channel.service.IChannelService;
import com.haoyu.sip.core.utils.PropertiesLoader;
import com.haoyu.sip.core.web.AbstractBaseController;

/**
 * @author lianghuahuang
 *
 */
@Controller
@RequestMapping(value={"/admin"})
public class AdminController extends AbstractBaseController {
	@Resource
	private IChannelService channelService;
	
	protected String getLogicalViewNamePrefix() {
		return "/admin/";
	}
	
	@RequestMapping("logout")
	public String logout(){
		Subject currentUser = SecurityUtils.getSubject();
		currentUser.logout();
		request.getSession().removeAttribute("loginer");
		return "redirect:"+PropertiesLoader.get("cms.http.domain")+"/logout";
	}
	
	@RequestMapping("login")
	public String login(AuthUser authUser){
		if(authUser==null||(StringUtils.isEmpty(authUser.getUsername())&&StringUtils.isEmpty(authUser.getPassword()))){
			/*StringBuffer path = new StringBuffer(getLogicalViewNamePrefix()+projectName);
			File file = new File(templatePath+path.toString()+"/login.ftl");
			if(file.exists()){
				return path.toString()+"/login";
			}*/
			return getLogicalViewNamePrefix()+"login";
		}
		try {
			Subject currentUser = SecurityUtils.getSubject();
			if (currentUser!=null&&!currentUser.isAuthenticated()) {
				currentUser.logout();
			}
			UsernamePasswordToken upt = new UsernamePasswordToken(authUser.getUsername(), authUser.getPassword());
			upt.setRememberMe(false);
			currentUser.login(upt);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMsg", "用户名或密码错误");
			return getLogicalViewNamePrefix()+"login";
		}
		return "redirect:/admin/index";
			
	}
	@RequestMapping(value="/index")
	public String index(){
		return "/admin/index";
	}
	

}
