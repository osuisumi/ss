package com.haoyu.sip.user.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.web.AbstractBaseController;
import com.haoyu.sip.user.entity.Department;
import com.haoyu.sip.user.service.IDepartmentService;

@Controller
@RequestMapping("/departments")
public class DepartmentController extends AbstractBaseController {

	@Resource
	private IDepartmentService departmentService;

	protected String getLogicalViewNamePrefix(){
		return "/department/";
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public String list(Department department,Model model){
		model.addAttribute("department",department);
		model.addAttribute("pageBounds", getPageBounds(10, true));
		return getLogicalViewNamePrefix() + "list_department";
	}
	
	@RequestMapping(value="create",method=RequestMethod.GET)
	public String create(Department department, Model model){
		model.addAttribute("department",department);
		return getLogicalViewNamePrefix() + "edit_department";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	@ResponseBody
	public Response save(Department department){
		return this.departmentService.createDepartment(department);
	}
	
	@RequestMapping(value="{id}/edit",method=RequestMethod.GET)
	public String edit(Department department,Model model){
		model.addAttribute("department",this.departmentService.findDepartmentById(department.getId()));
		return getLogicalViewNamePrefix() + "edit_department";
	}
	
	@RequestMapping(method=RequestMethod.PUT)
	@ResponseBody
	public Response update(Department department){
		return this.departmentService.updateDepartment(department);
	}
	
	@RequestMapping(value="batch/delete",method=RequestMethod.DELETE)
	@ResponseBody
	public Response batchDeleteByIds(@RequestParam("id")String ids){
		return this.departmentService.batchDeleteByIds(ids);
	}
	
	@RequestMapping(value="countForValidDeptNameIsExist",method = RequestMethod.GET)
	@ResponseBody
	public int validDeptNameIsExist(Department department){
		return this.departmentService.validDeptNameIsExist(department);
	}
	
	@RequestMapping(value="ValidDeptCodeIsExist",method = RequestMethod.GET)
	@ResponseBody
	public int validDeptCodeIsExist(Department department){
		return this.departmentService.validDeptCodeIsExist(department);
	}

}
