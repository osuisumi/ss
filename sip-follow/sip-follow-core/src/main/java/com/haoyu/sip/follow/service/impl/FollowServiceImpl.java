/**
 * 
 */
package com.haoyu.sip.follow.service.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.google.common.collect.Maps;
import com.haoyu.sip.core.entity.Relation;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.utils.ThreadContext;
import com.haoyu.sip.follow.dao.IFollowDao;
import com.haoyu.sip.follow.entity.Follow;
import com.haoyu.sip.follow.entity.FollowStat;
import com.haoyu.sip.follow.event.CreateFollowEvent;
import com.haoyu.sip.follow.event.DeleteFollowEvent;
import com.haoyu.sip.follow.service.IFollowService;

/**
 * @author lianghuahuang
 *
 */
@Service
public class FollowServiceImpl implements IFollowService {
	@Autowired
	private IFollowDao followDao;
	@Autowired
	private ApplicationContext applicationContext;
	/* (non-Javadoc)
	 * @see com.haoyu.sip.follow.service.IFollowService#findFollowById(java.lang.String)
	 */
	@Override
	public Follow findFollowById(String id) {
		return followDao.selectFollowById(id);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.follow.service.IFollowService#findFollowByUserAndFolloweds(java.lang.String, java.util.List, java.lang.String,PageBounds pageBounds)
	 */
	@Override
	public List<Follow> findFollowByUserAndFolloweds(String user,
			List<String> relations, String type,PageBounds pageBounds) {
		return followDao.selectFollowByUserAndFolloweds(user, relations, type, pageBounds);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.follow.service.IFollowService#findFollowByFollowEntity(com.haoyu.sip.core.entity.Relation,PageBounds pageBounds)
	 */
	@Override
	public List<Follow> findFollowByFollowEntity(Relation relation,PageBounds pageBounds) {
		Follow follow = new Follow();
		follow.setFollowEntity(relation);
		follow.setCreator(ThreadContext.getUser());
		return followDao.selectByFollow(follow, pageBounds);
	}
	
	@Override
	public List<Follow> findFollowByFollow(Follow follow, PageBounds pageBounds) {
		return followDao.selectByFollow(follow, pageBounds);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.follow.service.IFollowService#createFollow(com.haoyu.sip.follow.entity.Follow)
	 */
	@Override
	public Response createFollow(Follow follow) {
		if(follow==null){
			return Response.failInstance().responseMsg("createFollow fail!because follow is null!");
		}
		if(StringUtils.isEmpty(follow.getFollowEntity().getId())||StringUtils.isEmpty(follow.getCreator().getId())){
			return Response.failInstance().responseMsg("createAttitudeUser fail!attitude or relation.id or creator.id is null");
		}
		if(StringUtils.isEmpty(follow.getId())){
			follow.setId(Follow.getId(follow.getCreator().getId(),follow.getFollowEntity().getId(), follow.getFollowEntity().getType()));
		}
		int count = 0;
		try{
			count = followDao.insertFollow(follow);
		}catch(DuplicateKeyException e){
			//主键冲突
			return Response.failInstance().responseMsg("duplicate create follow!");
		}
		if(count>0){
			count = followDao.updateFollowStat(follow.getFollowEntity().getId());
			if (count <= 0) {
				FollowStat followStat = new FollowStat();
				followStat.setFollowedId(follow.getFollowEntity().getId());
				followStat.setFollowNum(1);
				count = followDao.insertFollowStat(followStat);
			}
			applicationContext.publishEvent(new CreateFollowEvent(follow));
		}
		return count>0?Response.successInstance().responseData(follow):Response.failInstance().responseMsg("createFollow fail!");
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.follow.service.IFollowService#deleteFollow(com.haoyu.sip.follow.entity.Follow)
	 */
	@Override
	public Response deleteFollow(Follow follow) {
		int count  = followDao.deleteFollowByPhysics(follow.getId());
		if(count>0){
			if (follow.getFollowEntity() != null && StringUtils.isNotEmpty(follow.getFollowEntity().getId())) {
				count = followDao.updateFollowStat(follow.getFollowEntity().getId());
			}
			applicationContext.publishEvent(new DeleteFollowEvent(follow));
		}
		return count>0?Response.successInstance():Response.failInstance();
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.follow.service.IFollowService#getFollowStatByFollowEntity(com.haoyu.sip.core.entity.Relation)
	 */
	@Override
	public FollowStat getFollowStatByFollowEntity(Relation relation) {
		return followDao.selectFollowStatByFollowEntity(relation);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.follow.service.IFollowService#getFollowStatByFolloweds(java.util.List)
	 */
	@Override
	public Map<String, FollowStat> getFollowStatByFolloweds(
			List<String> relationIds) {
		return followDao.selectFollowStatByFolloweds(relationIds);
	}

	@Override
	public Map<String, Object> isFollow(String userId,String relationIds,String type) {
		List<String> relationIdsArray = Arrays.asList(relationIds.split(","));
		List<Follow> follows = followDao.selectFollowByUserAndFolloweds(userId, relationIdsArray, type, null);
		Map<String,Object> result = new HashMap<String,Object>();
		for(String relationId:relationIdsArray){
			result.put(relationId, false);
		}
		if(!CollectionUtils.isEmpty(follows)){
			for(Follow follow:follows){
				result.put(follow.getFollowEntity().getId(), true);
			}
		}
		return result;
	}

	@Override
	public Response deleteFollowByUserAndFollowEntity(Follow follow) {
		if(StringUtils.isEmpty(follow.getCreator().getId()) || StringUtils.isEmpty(follow.getFollowEntity().getId()) || StringUtils.isEmpty(follow.getFollowEntity().getType())){
			return Response.failInstance().responseMsg("delete follow fail! creator or relationId or relationType is null");
		}
		Follow f = new Follow();
		f.setId(Follow.getId(follow.getCreator().getId(), follow.getFollowEntity().getId(), follow.getFollowEntity().getType()));
		return this.deleteFollow(f);
	}

	@Override
	public List<FollowStat> listFollowStat(FollowStat followStat, PageBounds pageBounds) {
		Map<String,Object> param = Maps.newHashMap();
		if (StringUtils.isNotEmpty(followStat.getFollowedId())) {
			param.put("followedIds", Arrays.asList(followStat.getFollowedId().split(",")));
		}
		return followDao.selectFollowStat(param,pageBounds);
	}

	@Override
	public List<Follow> listFollow(Follow follow, PageBounds pageBounds) {
		Map<String,Object> param = Maps.newHashMap();
		if (follow.getFollowEntity() != null) {
			if (StringUtils.isNotEmpty(follow.getFollowEntity().getId())) {
				param.put("relationIds", Arrays.asList(follow.getFollowEntity().getId().split(",")));
			}
			if (StringUtils.isNotEmpty(follow.getFollowEntity().getType())) {
				param.put("relationType",follow.getFollowEntity().getType());
			}
		}
		if (follow.getCreator() != null && StringUtils.isNotEmpty(follow.getCreator().getId())) {
			param.put("creatorId",follow.getCreator().getId());
		}
		return followDao.selectFollow(param,pageBounds);
	}

	@Override
	public List<Follow> listFollow(Map<String, Object> param, PageBounds pageBounds) {
		return followDao.selectFollow(param, pageBounds);
	}

}
