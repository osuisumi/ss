/**
 * 
 */
package com.haoyu.sip.cms.recommend.dao;

import java.util.List;

import com.haoyu.sip.cms.recommend.entity.Recommend;

/**
 * @author lianghuahuang
 *
 */
public interface IRecommendDao {
	
	int insertRecommend(Recommend recommend);
	
	int updateRecommend(Recommend recommend);
	
	int deleteRecommendByPhysics(Recommend recommend);
	
	int deleteRecommendByPhysics(List<String> ids);
}
