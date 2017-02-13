package com.haoyu.evaluate.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.web.AbstractBaseController;
import com.haoyu.sip.evaluate.entity.Evaluate;
import com.haoyu.sip.evaluate.entity.EvaluateItem;
import com.haoyu.sip.evaluate.service.IEvaluateItemService;
import com.haoyu.sip.utils.Collections3;

@Controller
@RequestMapping("**/evaluate/item/")
public class EvaluateItemController extends AbstractBaseController{
	
	@Resource
	private IEvaluateItemService evaluateItemService;
	
	@RequestMapping(method=RequestMethod.POST)
	@ResponseBody
	public Response createEvaluateItem(EvaluateItem evaluateItem){
		return evaluateItemService.createEvaluateItem(evaluateItem);
	}
	
	@RequestMapping(value="{id}", method=RequestMethod.PUT)
	@ResponseBody
	public Response updateEvaluateItem(EvaluateItem evaluateItem){
		return evaluateItemService.updateEvaluateItem(evaluateItem);
	}
	
	@RequestMapping(value="{id}", method=RequestMethod.DELETE)
	@ResponseBody
	public Response deleteEvaluateItem(EvaluateItem evaluateItem){
		return evaluateItemService.deleteEvaluateItemByLogic(evaluateItem);
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	@ResponseBody
	public Response updateBatch(Evaluate evaluate){
		if (Collections3.isNotEmpty(evaluate.getEvaluateItems())) {
			for (EvaluateItem evaluateItem : evaluate.getEvaluateItems()) {
				evaluateItemService.updateEvaluateItem(evaluateItem);
			}
		}
		return Response.successInstance();
	}

}
