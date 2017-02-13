/**
 * 
 */
package com.haoyu.sip.feedback.service.impl;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.feedback.dao.IFeedbackDao;
import com.haoyu.sip.feedback.entity.Feedback;
import com.haoyu.sip.feedback.service.IFeedbackService;
import com.haoyu.sip.utils.Identities;

/**
 * @author lianghuahuang
 *
 */
@Service
public class FeedbackServiceImpl implements IFeedbackService {
	
	@Resource
	private IFeedbackDao feedbackDao;
	/* (non-Javadoc)
	 * @see com.haoyu.sip.feedback.service.IFeedbackService#createFeedback(com.haoyu.sip.feedback.entity.Feedback)
	 */
	@Override
	public Response createFeedback(Feedback feedback) {
		if(feedback==null||StringUtils.isEmpty(feedback.getContent())){
			return Response.failInstance().responseMsg("createFeedback fail!feedback is null or feedback.content is empty");
		}
		if(StringUtils.isEmpty(feedback.getId())){
			feedback.setId(Identities.uuid2());
		}
		int count = feedbackDao.insertFeedback(feedback);
		return count>0?Response.successInstance().responseData(feedback):Response.failInstance().responseMsg("createFeedback fail!");
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.feedback.service.IFeedbackService#findFeedbackById(java.lang.String)
	 */
	@Override
	public Feedback findFeedbackById(String id) {
		return feedbackDao.selectFeedbackById(id);
	}

}
