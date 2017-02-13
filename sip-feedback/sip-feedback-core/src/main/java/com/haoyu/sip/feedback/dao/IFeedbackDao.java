/**
 * 
 */
package com.haoyu.sip.feedback.dao;

import com.haoyu.sip.feedback.entity.Feedback;

/**
 * @author lianghuahuang
 *
 */
public interface IFeedbackDao {
	Feedback selectFeedbackById(String id);
	
	int insertFeedback(Feedback feedback);
}
