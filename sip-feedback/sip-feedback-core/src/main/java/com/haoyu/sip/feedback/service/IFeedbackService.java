/**
 * 
 */
package com.haoyu.sip.feedback.service;

import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.feedback.entity.Feedback;

/**
 * @author lianghuahuang
 *
 */
public interface IFeedbackService {
	
	Response createFeedback(Feedback feedback);
	
	Feedback findFeedbackById(String id);
	
}
