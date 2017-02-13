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
import com.haoyu.sip.theme.entity.VisualThemeSet;
import com.haoyu.sip.theme.service.IVisualThemeSetService;

/**
 * @author lianghuahuang
 *
 */
@Controller
@RequestMapping("/theme/visual_theme_sets")
public class VisualThemeSetController extends AbstractBaseController {
	@Resource
	private IVisualThemeSetService visualThemeSetService;
	
	protected String getLogicalViewNamePrefix(){
		return "/admin/common/theme/";
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String getCreateVisualThemeSet(){
		return getLogicalViewNamePrefix()+"create_visualThemeSet";
	}
	
	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public String getEditVisualThemeSet(@PathVariable String id, Model model){
		model.addAttribute("visualThemeSet",visualThemeSetService.findVisualThemeSetById(id));
		return getLogicalViewNamePrefix()+"edit_visualThemeSet";
		
	}
	
	/**
	 * 保存新创的栏目数据 
	 * @param visualThemeSet
	 * @return
	 */
	@RequestMapping(method=RequestMethod.POST)
	@ResponseBody
	public Response createVisualThemeSet(VisualThemeSet visualThemeSet){
		return visualThemeSetService.createVisualThemeSet(visualThemeSet);
	}
	
	@RequestMapping(method=RequestMethod.GET)
	@ResponseBody
	public List<VisualThemeSet> getVisualThemeSets(VisualThemeSet visualThemeSet, Model model){
		List<VisualThemeSet> visualThemeSets =  visualThemeSetService.findVisualThemeSets(new HashMap<String, Object>(), getPageBounds(10, true));
		return visualThemeSets;
	}
	
	@RequestMapping(value="list",method=RequestMethod.GET)
	public String visualThemeSetList(VisualThemeSet visualThemeSet, Model model){
		setParameterToModel(request, model);
		return getLogicalViewNamePrefix()+"list_visualThemeSet";
	}
	
	@RequestMapping(value = "/{id}",method=RequestMethod.PUT)
	@ResponseBody
	public Response updateVisualThemeSet(@PathVariable String id,VisualThemeSet visualThemeSet){
		visualThemeSet.setId(id);
		return visualThemeSetService.updateVisualThemeSet(visualThemeSet);
	}
	
	@RequestMapping(value = "/{id}",method=RequestMethod.GET)
	@ResponseBody
	public VisualThemeSet getVisualThemeSet(@PathVariable String id){
		return visualThemeSetService.findVisualThemeSetById(id);
	}
	
	@RequestMapping(value = "/{id}",method=RequestMethod.DELETE)
	@ResponseBody
	public Response deleteVisualThemeSet(@PathVariable String id){
		return visualThemeSetService.deleteVisualThemeSet(new VisualThemeSet(id));
	}
}
