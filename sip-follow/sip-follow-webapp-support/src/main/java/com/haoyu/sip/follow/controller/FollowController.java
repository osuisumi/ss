/**
 * 
 */
package com.haoyu.sip.follow.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.haoyu.sip.core.entity.Relation;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.utils.ThreadContext;
import com.haoyu.sip.core.web.AbstractBaseController;
import com.haoyu.sip.follow.entity.Follow;
import com.haoyu.sip.follow.entity.FollowStat;
import com.haoyu.sip.follow.service.IFollowService;

/**
 * @author lianghuahuang
 *
 */
@RestController
@RequestMapping("/follows")
public class FollowController extends AbstractBaseController {
	
	@Autowired
	private IFollowService followService;
	
	@RequestMapping(value="{id}",method = RequestMethod.GET)
	public Follow getFollow(Follow follow){
		return followService.findFollowById(follow.getId());
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public Response createFollow(Follow follow){
		follow.setCreator(ThreadContext.getUser());
		return followService.createFollow(follow);
	}
	
	@RequestMapping(value="{id}",method = RequestMethod.DELETE)
	public Response deleteFollow(Follow follow){
		return followService.deleteFollow(follow);
	}
	
	@RequestMapping(value="deleteByUserAndFollowEntity",method=RequestMethod.DELETE)
	public Response deleteFollowByUserAndFollowEntity(Follow follow){
		return followService.deleteFollowByUserAndFollowEntity(follow);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public List<Follow> getFollows(Relation followEntity){
		return followService.findFollowByFollowEntity(followEntity, this.getPageBounds(10, true));
	}
	
	@RequestMapping(value="isFollow",method = RequestMethod.GET)
	public Map<String,Object> isFollow(String userId,String relationIds,String type){
		return followService.isFollow(userId, relationIds, type);
	}
	
	@RequestMapping(value="/followStats/{followedId}",method = RequestMethod.GET)
	public FollowStat getFollowStat(@PathVariable String followedId,Relation followEntity){
		followEntity.setId(followedId);
		return followService.getFollowStatByFollowEntity(followEntity);
	}
}
