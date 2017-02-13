package com.haoyu.sip.evaluate.mobile.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.web.AbstractBaseMobileController;
import com.haoyu.sip.evaluate.entity.EvaluateSubmission;
import com.haoyu.sip.evaluate.service.IEvaluateSubmissionService;

@Controller
@RequestMapping("**/m/evaluate/submission")
public class MEvaluateSubmissionController extends AbstractBaseMobileController{
	
	@Resource
	private IEvaluateSubmissionService evaluateSubmissionService;
	
	@RequestMapping(value="{id}", method=RequestMethod.PUT)
	@ResponseBody
	public Response updateEvaluateSubmission(EvaluateSubmission evaluateSubmission){
		return evaluateSubmissionService.updateEvaluateSubmission(evaluateSubmission);
	}

}
