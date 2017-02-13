/**
 * 
 */
package com.haoyu.sip.cms.recommend.service;

import java.util.List;

import com.haoyu.sip.cms.recommend.entity.Recommend;
import com.haoyu.sip.core.service.Response;

/**
 * @author lianghuahuang
 *
 */
public interface IRecommendService {
	
	Response createRecommend(Recommend recommend);
	
	Response updateRecommend(Recommend recommend);
	
	Response deleteRecommend(Recommend recommend);

	Response deleteRecommend(List<String> ids);
}
