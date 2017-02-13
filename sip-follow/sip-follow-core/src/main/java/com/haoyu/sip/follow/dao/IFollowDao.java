/**
 * 
 */
package com.haoyu.sip.follow.dao;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.sip.core.entity.Relation;
import com.haoyu.sip.follow.entity.Follow;
import com.haoyu.sip.follow.entity.FollowStat;

/**
 * @author lianghuahuang
 *
 */
public interface IFollowDao {
	
	Follow selectFollowById(String id);
	
	List<Follow> selectFollowByUserAndFolloweds(String user,List<String> relations,String type,PageBounds pageBounds);
	
	List<Follow> selectByFollow(Follow follow,PageBounds pageBounds);
	
	FollowStat selectFollowStatByFollowEntity(Relation relation);
	
	Map<String,FollowStat> selectFollowStatByFolloweds(List<String> ids);
	
	int insertFollow(Follow follow);	
	
	int deleteFollowByPhysics(String id);

	int updateFollowStat(String followedId);

	int insertFollowStat(FollowStat followStat);

	List<FollowStat> selectFollowStat(Map<String,Object> param, PageBounds pageBounds);

	List<Follow> selectFollow(Map<String,Object> param, PageBounds pageBounds);

}
