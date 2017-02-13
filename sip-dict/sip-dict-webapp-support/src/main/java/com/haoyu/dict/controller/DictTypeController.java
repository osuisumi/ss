package com.haoyu.dict.controller;



import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.haoyu.dict.entity.DictType;
import com.haoyu.dict.service.IDictTypeService;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.web.AbstractBaseController;
import com.haoyu.sip.core.web.SearchParam;
/**
 * @author hqy
 */
@Controller
@RequestMapping("dictType")
public class DictTypeController extends AbstractBaseController{ 
	@Resource
	 private IDictTypeService dictTypeService;
	
	/** 查看  */
	@RequestMapping("list")
	public String getDictTypeList(SearchParam searchParam,Model model){
		List<DictType> dictTypes = dictTypeService.list(searchParam, getPageBounds(10, true));
		model.addAttribute("dictTypes", dictTypes);
		return "dictType/list_dictType";
	}
	/** 创建 */
	@RequestMapping("create")
	public String create(Model model){
		model.addAttribute("state", "create");
		return "dictType/edit_dictType";
	}
	
	/** 更新 */
	@RequestMapping(value = "{dictTypeCode}/edit", method = RequestMethod.GET)
	public String edit(DictType dictType,Model model){
		dictType = dictTypeService.get(dictType.getDictTypeCode());
		model.addAttribute("state", "update");
		model.addAttribute("dictType", dictType);
		return "dictType/edit_dictType";
	}
	
	/** 保存 */
	@RequestMapping(value = "save", method = RequestMethod.POST)
	@ResponseBody
	public Response save(DictType dictType, String state, Model model) {
		if (state.equals("create")) {
			return dictTypeService.create(dictType);
		}else{
			return dictTypeService.update(dictType);
		}
	}
	
	/** 验证dictTypeCode是否唯一  */
	@RequestMapping(value = "{dictTypeCode}/check", method = RequestMethod.GET)
	@ResponseBody
	public Response checkLogical(DictType dictType,Model model){
		return dictTypeService.checkLogical(dictType);
	}
	
	/** 选择父索引  */
	@RequestMapping("listForChooseParentCode")
	public String listForChooseParentCode(SearchParam searchParam,Model model){
		List<DictType> dictTypes = dictTypeService.list(searchParam, getPageBounds(10, true));
		model.addAttribute("dictTypes", dictTypes);
		return "dictType/list_dictTypeForChooseParentCode";
	}
	
	/** 供dictEntry选择DictType  */
	@RequestMapping("listForChoose")
	public String listForChoose(SearchParam searchParam,Model model){
		List<DictType> dictTypes = dictTypeService.list(searchParam, getPageBounds(10, true));
		model.addAttribute("dictTypes", dictTypes);
		return "dictType/list_dictTypeForChoose";
	}
	
	/**
	 * 删除版块，可删除多个版块
	 * 多个id用逗号连接成一个字符串，放入到board.id中
	 * @param board
	 */
	@RequestMapping(method=RequestMethod.DELETE)
	@ResponseBody
	public Response delete(DictType dictType, Model model){
		return dictTypeService.delete(dictType);
	}
	
	
	@RequestMapping("index")	
	public String index(Model model){
		return "index";
	}
	
	
	
}