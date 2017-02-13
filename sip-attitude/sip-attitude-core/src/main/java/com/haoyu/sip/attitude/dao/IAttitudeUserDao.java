/**
 * 
 */
package com.haoyu.sip.attitude.dao;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.sip.attitude.entity.AttitudeStat;
import com.haoyu.sip.attitude.entity.AttitudeUser;
import com.haoyu.sip.core.entity.Relation;

/**
 * @author lianghuahuang
 *
 */
public interface IAttitudeUserDao {
	
	public AttitudeUser selectAttitudeUserById(String id);
	
	public List<AttitudeUser> selectAttitudeUser(AttitudeUser attitudeUser,PageBounds pageBounds);
	
	public List<AttitudeUser> selectAttitudeUser(Map<String,Object> parameter,PageBounds pageBounds);
	
	public Map<String,AttitudeStat> selectAttitudeStatByAttitudeAndRelation(String attitude,Relation relation); 
	
	public Map<String,AttitudeUser> selectAttitudeUsersByRelationIdsAndRelationType(List<String> relationIds,String relationType,String creator);
	
	public int insertAttitudeUser(AttitudeUser attitudeUser);
	
	public int updateAttitudeUser(AttitudeUser attitudeUser);
	
	public int deleteAttitudeUserByPhysics(String id);
	
	public int deleteAttitudeUserByLogic(AttitudeUser attitudeUser);
	
	List<AttitudeStat> selectAttitudeStat(Map<String,Object> param, PageBounds pageBounds);

	Map<String, AttitudeStat> selectAttitudeStatByParam(Map<String, Object> param);

}
