package com.haoyu.dict.controller;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.haoyu.dict.entity.DictEntry;
import com.haoyu.dict.entity.DictType;
import com.haoyu.dict.service.IDictEntryService;
import com.haoyu.dict.utils.DictionaryUtils;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.web.AbstractBaseController;
import com.haoyu.sip.core.web.SearchParam;
/**
 * @author hqy
 */
@Controller
@RequestMapping("dictEntry")
public class DictEntryController extends AbstractBaseController{ 
	@Resource
	 private IDictEntryService dictEntryService;
	
	/** 查看  */
	@RequestMapping("list")
	public String getDictEntryList(SearchParam searchParam,Model model){
		List<DictEntry> dictEntrys = dictEntryService.list(searchParam, getPageBounds(10, true));
		model.addAttribute("dictEntrys", dictEntrys);
		return "dictEntry/list_dictEntry";
	}
	
	/** 创建 */
	@RequestMapping("create")
	public String create(Model model){
		return "dictEntry/edit_dictEntry";
	}
	
	/** 更新 */
	@RequestMapping(value = "{id}/edit", method = RequestMethod.GET)
	public String edit(DictEntry dictEntry,Model model){
		dictEntry = dictEntryService.get(dictEntry.getId());
		model.addAttribute("dictEntry", dictEntry);
		return "dictEntry/edit_dictEntry";
	}
	
	/** 保存 */
	@RequestMapping(value = "save", method = RequestMethod.POST)
	@ResponseBody
	public Response save(DictEntry dictEntry, Model model) {
		if(StringUtils.isEmpty(dictEntry.getId())){
			return dictEntryService.create(dictEntry);
		}
		return dictEntryService.update(dictEntry);
	}
	
	/**
	 * 删除版块，可删除多个版块
	 * 多个id用逗号连接成一个字符串，放入到board.id中
	 * @param board
	 */
	@RequestMapping(method=RequestMethod.DELETE)
	@ResponseBody
	public Response delete(DictEntry dictEntry, Model model){
		return dictEntryService.delete(dictEntry);
	}
	
}