/**
 * 
 */
package com.haoyu.sip.attitude.dao.impl.mybatis;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.google.common.collect.Maps;
import com.haoyu.sip.attitude.dao.IAttitudeUserDao;
import com.haoyu.sip.attitude.entity.AttitudeStat;
import com.haoyu.sip.attitude.entity.AttitudeUser;
import com.haoyu.sip.core.entity.Relation;
import com.haoyu.sip.core.jdbc.MybatisDao;

/**
 * @author lianghuahuang
 *
 */
@Repository
public class AttitudeUserDao extends MybatisDao implements IAttitudeUserDao {

	/* (non-Javadoc)
	 * @see com.haoyu.sip.attitude.dao.IAttitudeUserDao#selectAttitudeUserById(java.lang.String)
	 */
	@Override
	public AttitudeUser selectAttitudeUserById(String id) {
		return super.selectByPrimaryKey(id);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.attitude.dao.IAttitudeUserDao#selectAttitudeUser(com.haoyu.sip.attitude.entity.AttitudeUser, com.github.miemiedev.mybatis.paginator.domain.PageBounds)
	 */
	@Override
	public List<AttitudeUser> selectAttitudeUser(AttitudeUser attitudeUser,
			PageBounds pageBounds) {
		return super.selectList("selectByAttitudeUser", attitudeUser, pageBounds);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.attitude.dao.IAttitudeUserDao#selectAttitudeStatByAttitudeAndRelation(java.lang.String, com.haoyu.sip.core.entity.Relation)
	 */
	@Override
	public Map<String, AttitudeStat> selectAttitudeStatByAttitudeAndRelation(
			String attitude, Relation relation) {
		AttitudeUser attitudeUser = new AttitudeUser();
		if(StringUtils.isNotEmpty(attitude)){
			attitudeUser.setAttitude(attitude);
		}
		attitudeUser.setRelation(relation);
		return super.selectMap("selectAttitudeStatByAttitudeAndRelation", attitudeUser, "attitude");
	}
	
	@Override
	public List<AttitudeUser> selectAttitudeUser(Map<String, Object> parameter, PageBounds pageBounds) {
		return super.selectList("selectByParameter", parameter, pageBounds);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.attitude.dao.IAttitudeUserDao#insertAttitudeUser(com.haoyu.sip.attitude.entity.AttitudeUser)
	 */
	@Override
	public int insertAttitudeUser(AttitudeUser attitudeUser) {
		attitudeUser.setDefaultValue();
		return super.insert(attitudeUser);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.attitude.dao.IAttitudeUserDao#updateAttitudeUser(com.haoyu.sip.attitude.entity.AttitudeUser)
	 */
	@Override
	public int updateAttitudeUser(AttitudeUser attitudeUser) {
		attitudeUser.setUpdateTime(System.currentTimeMillis());
		return super.update(attitudeUser);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.attitude.dao.IAttitudeUserDao#deleteAttitudeUserByPhysics(java.lang.String)
	 */
	@Override
	public int deleteAttitudeUserByPhysics(String id) {
		return super.deleteByPhysics(id);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.attitude.dao.IAttitudeUserDao#deleteAttitudeUserByLogic(com.haoyu.sip.attitude.entity.AttitudeUser)
	 */
	@Override
	public int deleteAttitudeUserByLogic(AttitudeUser attitudeUser) {
		attitudeUser.setUpdateTime(System.currentTimeMillis());
		return super.deleteByLogic(attitudeUser);
	}

	@Override
	public Map<String, AttitudeUser> selectAttitudeUsersByRelationIdsAndRelationType(List<String> relationIds, String relationType, String creator) {
		Map<String,Object> parameter = Maps.newHashMap();
		parameter.put("relationType",relationType);
		parameter.put("creatorId",creator);
		if(!CollectionUtils.isEmpty(relationIds)){
			parameter.put("relationIds", relationIds);
		}
		return super.selectMap("selectByParameter", parameter,"relation.id");
	}

	@Override
	public List<AttitudeStat> selectAttitudeStat(Map<String, Object> param, PageBounds pageBounds) {
		return selectList("selectAttitudeStat", param, pageBounds);
	}

	@Override
	public Map<String, AttitudeStat> selectAttitudeStatByParam(Map<String, Object> param) {
		return super.selectMap("selectAttitudeStatByParam", param, "relation.id");
	}
}
