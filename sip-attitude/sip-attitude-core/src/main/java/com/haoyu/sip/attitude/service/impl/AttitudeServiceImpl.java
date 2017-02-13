/**
 * 
 */
package com.haoyu.sip.attitude.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.google.common.collect.Maps;
import com.haoyu.sip.attitude.dao.IAttitudeUserDao;
import com.haoyu.sip.attitude.entity.AttitudeStat;
import com.haoyu.sip.attitude.entity.AttitudeUser;
import com.haoyu.sip.attitude.event.CreateAttitudeUserEvent;
import com.haoyu.sip.attitude.event.DeleteAttitudeUserEvent;
import com.haoyu.sip.attitude.service.IAttitudeService;
import com.haoyu.sip.core.entity.Relation;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.utils.Collections3;

/**
 * @author lianghuahuang
 *
 */
@Service
public class AttitudeServiceImpl implements IAttitudeService {
	
	@Autowired
	private IAttitudeUserDao attitudeUserDao;
	
	@Autowired  
	private ApplicationContext applicationContext;  
	/* (non-Javadoc)
	 * @see com.haoyu.sip.attitude.service.IAttitudeService#createAttitudeUser(com.haoyu.sip.attitude.entity.AttitudeUser)
	 */
	@Override
	public Response createAttitudeUser(AttitudeUser attitudeUser) {
		if(attitudeUser==null){
			return Response.failInstance().responseMsg("createAttitudeUser fail!attitudeUser is null");
		}
		if(StringUtils.isEmpty(attitudeUser.getAttitude())||StringUtils.isEmpty(attitudeUser.getRelation().getId())||StringUtils.isEmpty(attitudeUser.getCreator().getId())){
			return Response.failInstance().responseMsg("createAttitudeUser fail!attitude or relation.id or creator.id is null");
		}
		if(StringUtils.isEmpty(attitudeUser.getId())){
			attitudeUser.setId(AttitudeUser.getId(attitudeUser.getAttitude(), attitudeUser.getRelation().getId(), attitudeUser.getCreator().getId()));
		}
		int count = 0;
		try{
			count = attitudeUserDao.insertAttitudeUser(attitudeUser);
		}catch(DuplicateKeyException e){
			//已经表明过态度
			return Response.failInstance().responseMsg("duplicate create attitudeUser!");
		}
		if(count>0){
			applicationContext.publishEvent(new CreateAttitudeUserEvent(attitudeUser));
		}
		return count>0?Response.successInstance().responseData(attitudeUser):Response.failInstance().responseMsg("createAttitudeUser is fail");
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.attitude.service.IAttitudeService#updateAttitudeUser(com.haoyu.sip.attitude.entity.AttitudeUser)
	 */
	@Override
	public Response updateAttitudeUser(AttitudeUser attitudeUser) {
		int count  = attitudeUserDao.updateAttitudeUser(attitudeUser);
		return count>0?Response.successInstance().responseData(attitudeUser):Response.failInstance().responseMsg("updateAttitudeUser is fail");
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.attitude.service.IAttitudeService#findAttitudeUserByAttitudeAndRelation(java.lang.String, com.haoyu.sip.core.entity.Relation, com.github.miemiedev.mybatis.paginator.domain.PageBounds)
	 */
	@Override
	public List<AttitudeUser> findAttitudeUserByAttitudeAndRelation(
			String attitude, Relation relation, PageBounds pageBounds) {
		AttitudeUser au = new AttitudeUser();
		au.setAttitude(attitude);
		au.setRelation(relation);
		return attitudeUserDao.selectAttitudeUser(au, pageBounds);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.attitude.service.IAttitudeService#findAttitudeUserById(java.lang.String)
	 */
	@Override
	public AttitudeUser findAttitudeUserById(String id) {
		return attitudeUserDao.selectAttitudeUserById(id);
	}
	
	@Override
	public List<AttitudeUser> findAttitudeUserByParameter(Map<String, Object> parameter, PageBounds pageBounds) {
		return this.attitudeUserDao.selectAttitudeUser(parameter, pageBounds);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.attitude.service.IAttitudeService#getAttitudeStatByAttitudeAndRelation(java.lang.String, com.haoyu.sip.core.entity.Relation)
	 */
	@Override
	public Map<String, AttitudeStat> getAttitudeStatByAttitudeAndRelation(
			String attitude, Relation relation) {
		return attitudeUserDao.selectAttitudeStatByAttitudeAndRelation(attitude, relation);
	}

	@Override
	public Response deleteAttitideUser(String attitude, String relationId, String creator) {
		if(StringUtils.isAnyEmpty(attitude,relationId,creator)){
			return Response.failInstance().responseMsg("delete attitudeUser fail!attitude,relationId,creator is null");
		}
		AttitudeUser attitudeUser = this.attitudeUserDao.selectAttitudeUserById(AttitudeUser.getId(attitude, relationId, creator));
		if(attitudeUser!=null){
			this.attitudeUserDao.deleteAttitudeUserByPhysics(attitudeUser.getId());
			applicationContext.publishEvent(new DeleteAttitudeUserEvent(attitudeUser));
			return Response.successInstance();
		}else{
			return Response.failInstance();
		}
	}

	@Override
	public Map<String, AttitudeUser> getAttitudesByRelationIdsAndRelationType(List<String> relationIds, String relationType, String creator) {
		return this.attitudeUserDao.selectAttitudeUsersByRelationIdsAndRelationType(relationIds, relationType, creator);
	}

	@Override
	public List<AttitudeUser> findAttitudeUser(AttitudeUser attitudeUser) {
		Map<String,Object> param = Maps.newHashMap();
		if (attitudeUser.getRelation() != null && StringUtils.isNotEmpty(attitudeUser.getRelation().getType()) && Collections3.isNotEmpty(Arrays.asList(attitudeUser.getRelation().getId().split(",")))) {
			param.put("relationIds", Arrays.asList(attitudeUser.getRelation().getId().split(",")));
			param.put("relationType",attitudeUser.getRelation().getType());
		}
		if (attitudeUser.getCreator() != null && StringUtils.isNotEmpty(attitudeUser.getCreator().getId())) {
			param.put("creatorId",attitudeUser.getCreator().getId());
		}
		if (StringUtils.isNotEmpty(attitudeUser.getAttitude())) {
			param.put("attitude",attitudeUser.getAttitude());
		}
		return this.attitudeUserDao.selectAttitudeUser(param, null);
	}

	@Override
	public List<AttitudeStat> listAttitudeStat(AttitudeStat attitudeStat, PageBounds pageBounds) {
		Map<String,Object> param = Maps.newHashMap();
		if (attitudeStat.getRelation() != null && StringUtils.isNotEmpty(attitudeStat.getRelation().getId())) {
			param.put("relationIds",Arrays.asList(attitudeStat.getRelation().getId().split(",")));
		}
		if (StringUtils.isNotEmpty(attitudeStat.getAttitude())) {
			param.put("attitude",attitudeStat.getAttitude());
		}
		return this.attitudeUserDao.selectAttitudeStat(param, pageBounds);
	}

	@Override
	public Map<String, AttitudeStat> getAttitudeStatByParam(Map<String, Object> parameter) {
		return this.attitudeUserDao.selectAttitudeStatByParam(parameter);
	}
}