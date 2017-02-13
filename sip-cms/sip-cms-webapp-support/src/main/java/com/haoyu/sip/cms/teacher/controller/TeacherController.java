package com.haoyu.sip.cms.teacher.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Maps;
import com.haoyu.sip.cms.teacher.entity.Teacher;
import com.haoyu.sip.cms.teacher.service.ITeacherService;
import com.haoyu.sip.cms.channel.service.IChannelService;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.web.AbstractBaseController;


@Controller
@RequestMapping("/cms_teachers")
public class TeacherController  extends AbstractBaseController{
	@Autowired
	private ITeacherService teacherService;
	
	@Autowired
	private IChannelService channelService;
	
	protected String getLogicalViewNamePrefix(){
		return "/admin/cms/teacher/";
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String getCreateTeacher(String channelId,Model model){
		if(channelId!=null){
			model.addAttribute("channel", channelService.findChannelById(channelId));
		}
		return getLogicalViewNamePrefix()+"create_teacher";
	}
	
	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public String getEditTeacher(@PathVariable String id,String channelId, Model model){
		if(channelId!=null){
			model.addAttribute("channel", channelService.findChannelById(channelId));
		}
		model.addAttribute("teacher",teacherService.findTeacherById(id));
		return getLogicalViewNamePrefix()+"edit_teacher";
		
	}
	
	@RequestMapping(value="/{id}/view",method = RequestMethod.GET)
	public String viewTeacher(Teacher teacher,Model model){
		model.addAttribute("teacher",teacherService.findTeacherById(teacher.getId()));
		return getLogicalViewNamePrefix()+"view_teacher";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	@ResponseBody
	public Response createTeacher(Teacher teacher){
		return teacherService.createTeacher(teacher);
	}
	
	@RequestMapping(method=RequestMethod.GET)
	@ResponseBody
	public List<Teacher> getTeachers(Teacher teacher){
		return teacherService.findTeachers(teacher, this.getPageBounds(10, true));
	}
	
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public String getTeacherList(Teacher teacher, Model model){
		setParameterToModel(request,model);
		model.addAttribute("teacher", teacher);
		return getLogicalViewNamePrefix()+"list_teacher";
	}
	
	@RequestMapping(value = "/{id}",method=RequestMethod.PUT)
	@ResponseBody
	public Response updateTeacher(@PathVariable String id,Teacher teacher){
		teacher.setId(id);
		return teacherService.updateTeacher(teacher);
	}
	
	@RequestMapping(value = "/{id}",method=RequestMethod.GET)
	@ResponseBody
	public Teacher getTeacher(@PathVariable String id){
		return teacherService.findTeacherById(id);
	}
	
	@RequestMapping(value = "/{id}",method=RequestMethod.DELETE)
	@ResponseBody
	public Response deleteTeacher(@PathVariable String id){
		return teacherService.deleteTeacher(new Teacher(id));
	}
	
}
