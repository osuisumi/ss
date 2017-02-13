/**
 * 
 */
package com.haoyu.sip.theme.controller;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.web.AbstractBaseController;
import com.haoyu.sip.theme.entity.VisualTheme;
import com.haoyu.sip.theme.service.IVisualThemeService;

/**
 * @author lianghuahuang
 *
 */
@Controller
@RequestMapping("/theme/visual_themes")
public class VisualThemeController extends AbstractBaseController {
	@Resource
	private IVisualThemeService visualThemeService;
	
	protected String getLogicalViewNamePrefix(){
		return "/admin/common/theme/";
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String getCreateVisualTheme(){
		return getLogicalViewNamePrefix()+"create_visualTheme";
	}
	
	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public String getEditVisualTheme(@PathVariable String id, Model model){
		model.addAttribute("visualTheme",visualThemeService.findVisualThemeById(id));
		return getLogicalViewNamePrefix()+"edit_visualTheme";
		
	}
	
	/**
	 * 保存新创的栏目数据 
	 * @param visualTheme
	 * @return
	 */
	@RequestMapping(method=RequestMethod.POST)
	@ResponseBody
	public Response createVisualTheme(VisualTheme visualTheme){
		return visualThemeService.createVisualTheme(visualTheme);
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public String getVisualThemes(VisualTheme visualTheme, Model model){
		List<VisualTheme> visualThemes =  visualThemeService.findVisualThemes(new HashMap<String, Object>(), getPageBounds(10, true));
		model.addAttribute("visualThemes", visualThemes);
		return getLogicalViewNamePrefix()+"list_visualTheme";
	}
	
	@RequestMapping(value = "/list",method=RequestMethod.GET)
	public String visualThemeList(VisualTheme visualTheme, Model model){
		setParameterToModel(request, model);
		return getLogicalViewNamePrefix()+"list_visualTheme";
	}
	
	@RequestMapping(value = "/{id}",method=RequestMethod.PUT)
	@ResponseBody
	public Response updateVisualTheme(@PathVariable String id,VisualTheme visualTheme){
		visualTheme.setId(id);
		return visualThemeService.updateVisualTheme(visualTheme);
	}
	
	@RequestMapping(value = "/{id}",method=RequestMethod.GET)
	@ResponseBody
	public VisualTheme getVisualTheme(@PathVariable String id){
		return visualThemeService.findVisualThemeById(id);
	}
	
	@RequestMapping(value = "/{id}",method=RequestMethod.DELETE)
	@ResponseBody
	public Response deleteVisualTheme(@PathVariable String id){
		return visualThemeService.deleteVisualTheme(new VisualTheme(id));
	}
}
