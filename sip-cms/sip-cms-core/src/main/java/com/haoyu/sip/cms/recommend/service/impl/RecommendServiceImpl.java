/**
 * 
 */
package com.haoyu.sip.cms.recommend.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.haoyu.sip.cms.recommend.dao.IRecommendDao;
import com.haoyu.sip.cms.recommend.entity.Recommend;
import com.haoyu.sip.cms.recommend.service.IRecommendService;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.utils.Identities;

/**
 * @author lianghuahuang
 *
 */
@Service
public class RecommendServiceImpl implements IRecommendService {
	@Resource
	private IRecommendDao recommendDao;
	/* (non-Javadoc)
	 * @see com.haoyu.sip.cms.recommend.service.IRecommendService#createRecommend(com.haoyu.sip.cms.recommend.entity.Recommend)
	 */
	@Override
	public Response createRecommend(Recommend recommend) {
		if(recommend==null){
			return Response.failInstance().responseMsg("createRecommend fail!recommend is null!");
		}
		if(recommend.getChannel()==null||StringUtils.isEmpty(recommend.getChannel().getId())){
			return Response.failInstance().responseMsg("createRecommend fail!recommend channel is null!");
		}
		if(recommend.getRelation()==null||StringUtils.isEmpty(recommend.getRelation().getId())){
			return Response.failInstance().responseMsg("createRecommend fail!recommend relation is null!");
		}
		if(StringUtils.isEmpty(recommend.getId())){
			recommend.setId(DigestUtils.md5Hex(recommend.getChannel().getId()+recommend.getRelation().getId()));
		}
		int count = 0 ;
		try{
			count = recommendDao.insertRecommend(recommend);
		}catch(DuplicateKeyException e){
			return Response.failInstance().responseData("recommend's channel relation is duplicate!");
		}
		return count>0?Response.successInstance().responseData(recommend):Response.failInstance().responseMsg("createRecommend fail!");
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.cms.recommend.service.IRecommendService#updateRecommend(com.haoyu.sip.cms.recommend.entity.Recommend)
	 */
	@Override
	public Response updateRecommend(Recommend recommend) {
		if(recommend==null||StringUtils.isEmpty(recommend.getId())){
			return Response.failInstance().responseMsg("updateRecommend is fail!recommend is null or recommend's id is null");
		}
		int count = recommendDao.updateRecommend(recommend);
		return count>0?Response.successInstance().responseData(recommend):Response.failInstance().responseMsg("updateRecommend fail!");
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.cms.recommend.service.IRecommendService#deleteRecommend(com.haoyu.sip.cms.recommend.entity.Recommend)
	 */
	@Override
	public Response deleteRecommend(Recommend recommend) {
		if(recommend==null||(StringUtils.isEmpty(recommend.getId())&&recommend.getChannel()==null&&recommend.getRelation()==null)){
			return Response.failInstance().responseMsg("deleteRecommend is fail!recommend is null or recommend's id is null");
		}
		int count = recommendDao.deleteRecommendByPhysics(recommend);
		return count>0?Response.successInstance():Response.failInstance().responseMsg("deleteRecommend fail!");
	}

	@Override
	public Response deleteRecommend(List<String> ids) {
		if(ids==null||ids.isEmpty()){
			return Response.failInstance().responseMsg("deleteRecommend is fail!recommend's id is null");
		}
		int count = recommendDao.deleteRecommendByPhysics(ids);
		return count>0?Response.successInstance():Response.failInstance().responseMsg("deleteRecommend fail!");
	}

}
