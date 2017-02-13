package com.haoyu.sip.label.web;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.web.AbstractBaseController;
import com.haoyu.sip.label.entity.Label;
import com.haoyu.sip.label.service.ILabelService;

@Controller
@RequestMapping("label")
public class LabelController extends AbstractBaseController{

	@Resource
	private ILabelService labelService;
	
	@RequestMapping("listLableByName")
	@ResponseBody
	public Response listLabelByName(Label label){
		Response response = Response.successInstance();
		List<Label> labels = labelService.getLebals(label, getPageBounds(10, true));
		response.setResponseData(labels);
		return response;
	}
	
	@RequestMapping("addLabel")
	@ResponseBody
	public Response addLabel(Label label){
		return labelService.createLabel(label);
	}
	
}
