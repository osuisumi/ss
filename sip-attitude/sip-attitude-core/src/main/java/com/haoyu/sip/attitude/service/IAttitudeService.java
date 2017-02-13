/**
 * 
 */
package com.haoyu.sip.attitude.service;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.sip.attitude.entity.AttitudeStat;
import com.haoyu.sip.attitude.entity.AttitudeUser;
import com.haoyu.sip.core.entity.Relation;
import com.haoyu.sip.core.service.Response;

/**
 * @author lianghuahuang
 *
 */
public interface IAttitudeService {
	
	public Response createAttitudeUser(AttitudeUser attitudeUser);
	
	public Response updateAttitudeUser(AttitudeUser attitudeUser);
	
	public List<AttitudeUser> findAttitudeUserByAttitudeAndRelation(String attitude,Relation relation,PageBounds pageBounds);
	
	public List<AttitudeUser> findAttitudeUserByParameter(Map<String,Object> parameter,PageBounds pageBounds);
	
	public AttitudeUser findAttitudeUserById(String id);
	
	public Map<String,AttitudeStat> getAttitudeStatByAttitudeAndRelation(String attitude,Relation relation);
	
	public Map<String,AttitudeUser> getAttitudesByRelationIdsAndRelationType(List<String> relationIds,String relationType,String creator);
	
	public Response deleteAttitideUser(String attitude,String relationId,String creator);

	public List<AttitudeUser> findAttitudeUser(AttitudeUser attitudeUser);

	public List<AttitudeStat> listAttitudeStat(AttitudeStat attitudeStat,PageBounds pageBounds);

	public Map<String,AttitudeStat> getAttitudeStatByParam(Map<String,Object> parameter);

}
