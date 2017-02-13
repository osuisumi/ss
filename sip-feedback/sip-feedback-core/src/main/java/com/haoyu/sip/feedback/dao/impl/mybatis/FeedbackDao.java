/**
 * 
 */
package com.haoyu.sip.feedback.dao.impl.mybatis;

import org.springframework.stereotype.Repository;

import com.haoyu.sip.core.jdbc.MybatisDao;
import com.haoyu.sip.feedback.dao.IFeedbackDao;
import com.haoyu.sip.feedback.entity.Feedback;

/**
 * @author lianghuahuang
 *
 */
@Repository
public class FeedbackDao extends MybatisDao implements IFeedbackDao {

	/* (non-Javadoc)
	 * @see com.haoyu.sip.feedback.dao.IFeedbackDao#selectFeedbackById(java.lang.String)
	 */
	@Override
	public Feedback selectFeedbackById(String id) {
		return super.selectByPrimaryKey(id);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.feedback.dao.IFeedbackDao#insertFeedback(com.haoyu.sip.feedback.entity.Feedback)
	 */
	@Override
	public int insertFeedback(Feedback feedback) {
		feedback.setDefaultValue();
		return super.insert(feedback);
	}

}
