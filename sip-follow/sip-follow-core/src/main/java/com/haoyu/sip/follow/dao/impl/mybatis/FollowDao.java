/**
 * 
 */
package com.haoyu.sip.follow.dao.impl.mybatis;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.google.common.collect.Maps;
import com.haoyu.sip.core.entity.Relation;
import com.haoyu.sip.core.jdbc.MybatisDao;
import com.haoyu.sip.follow.dao.IFollowDao;
import com.haoyu.sip.follow.entity.Follow;
import com.haoyu.sip.follow.entity.FollowStat;

/**
 * @author lianghuahuang
 *
 */
@Repository
public class FollowDao extends MybatisDao implements IFollowDao {
	
	/* (non-Javadoc)
	 * @see com.haoyu.sip.follow.dao.IFollowDao#selectFollowById(java.lang.String)
	 */
	@Override
	public Follow selectFollowById(String id) {
		return super.selectByPrimaryKey(id);
	}
	
	@Override
	public FollowStat selectFollowStatByFollowEntity(Relation relation) {
		return super.selectOne("selectFollowStatByFollowEntity", relation);
	}

	@Override
	public Map<String, FollowStat> selectFollowStatByFolloweds(List<String> ids) {
		return super.selectMap("selectFollowStatByFolloweds", ids, "followedId");
	}


	/* (non-Javadoc)
	 * @see com.haoyu.sip.follow.dao.IFollowDao#sleectFollowByUserAndRelations(java.lang.String, java.util.List, java.lang.String)
	 */
	@Override
	public List<Follow> selectFollowByUserAndFolloweds(String user,
			List<String> relations, String type,PageBounds pageBounds) {
		Map<String,Object> param = Maps.newHashMap();
		if(StringUtils.isNotEmpty(user)){
			param.put("userId", user);
		}
		if(StringUtils.isNotEmpty(type)){
			param.put("followedType", type);
		}
		
		if(relations!=null&&!relations.isEmpty()){
			param.put("followedIds", relations);
		}
		
		return super.selectList("selectFollowByUserAndFolloweds", param, pageBounds);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.follow.dao.IFollowDao#selectByFollow(Follow follow)
	 */
	@Override
	public List<Follow> selectByFollow(Follow follow,PageBounds pageBounds) {
		return super.selectList("selectByFollow", follow,pageBounds);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.follow.dao.IFollowDao#insertFollow(com.haoyu.sip.follow.entity.Follow)
	 */
	@Override
	public int insertFollow(Follow follow) {
		follow.setDefaultValue();
		return super.insert(follow);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.follow.dao.IFollowDao#deleteFollowByPhysics(java.lang.String)
	 */
	@Override
	public int deleteFollowByPhysics(String id) {
		return super.deleteByPhysics(id);
	}

	@Override
	public int updateFollowStat(String followedId) {
		return update("updateFollowStat", followedId);
	}

	@Override
	public int insertFollowStat(FollowStat followStat) {
		return update("insertFollowStat", followStat);
	}

	@Override
	public List<FollowStat> selectFollowStat(Map<String,Object> param, PageBounds pageBounds) {
		return selectList("selectFollowStat", param, pageBounds);
	}

	@Override
	public List<Follow> selectFollow(Map<String, Object> param, PageBounds pageBounds) {
		return selectList("selectFollow", param, pageBounds);
	}


}
