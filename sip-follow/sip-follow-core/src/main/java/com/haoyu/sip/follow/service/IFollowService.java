/**
 * 
 */
package com.haoyu.sip.follow.service;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.sip.core.entity.Relation;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.follow.entity.Follow;
import com.haoyu.sip.follow.entity.FollowStat;

/**
 * @author lianghuahuang
 *
 */
public interface IFollowService {
	
	public Follow findFollowById(String id);
	
	public List<Follow> findFollowByUserAndFolloweds(String user,List<String> relations,String type,PageBounds pageBounds);
	
	public List<Follow> findFollowByFollowEntity(Relation relation,PageBounds pageBounds);
	
	public List<Follow> findFollowByFollow(Follow follow,PageBounds pageBounds);

	public Response createFollow(Follow follow);	
	
	public Response deleteFollow(Follow follow);
	
	public Response deleteFollowByUserAndFollowEntity(Follow follow);
	
	public FollowStat getFollowStatByFollowEntity(Relation relation);
	
	public Map<String,FollowStat> getFollowStatByFolloweds(List<String> relationIds);
	
	public Map<String,Object> isFollow(String userId,String relationIds,String type);
	
	public List<FollowStat> listFollowStat(FollowStat followStat,PageBounds pageBounds);

	public List<Follow> listFollow(Follow follow,PageBounds pageBounds);

	public List<Follow> listFollow(Map<String, Object> param, PageBounds pageBounds);
}
