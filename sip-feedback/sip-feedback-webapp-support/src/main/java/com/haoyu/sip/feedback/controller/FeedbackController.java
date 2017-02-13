/**
 * 
 */
package com.haoyu.sip.feedback.controller;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.web.AbstractBaseController;
import com.haoyu.sip.feedback.entity.Feedback;
import com.haoyu.sip.feedback.service.IFeedbackService;

/**
 * @author lianghuahuang
 *
 */
@RestController
@RequestMapping("/feedbacks")
public class FeedbackController extends AbstractBaseController{
	@Resource
	private IFeedbackService feedbackService;
	
	@RequestMapping(method=RequestMethod.POST)
	public Response createFeedback(Feedback feedback){
		return feedbackService.createFeedback(feedback);
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public Feedback getFeedback(@PathVariable String id){
		return feedbackService.findFeedbackById(id);
	}
}
