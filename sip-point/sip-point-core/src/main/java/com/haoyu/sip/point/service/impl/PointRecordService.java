package com.haoyu.sip.point.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.point.dao.IPointRecordDao;
import com.haoyu.sip.point.entity.PointRecord;
import com.haoyu.sip.point.entity.PointStrategy;
import com.haoyu.sip.point.event.ChangePointRecordEvent;
import com.haoyu.sip.point.event.CreatePointRecordEvent;
import com.haoyu.sip.point.event.DeletePointRecordEvent;
import com.haoyu.sip.point.service.IPointRecordService;
import com.haoyu.sip.point.service.IPointStrategyService;
import com.haoyu.sip.point.utils.UserPointCount;
import com.haoyu.sip.utils.Collections3;
import com.haoyu.sip.utils.Identities;
@Service
public class PointRecordService implements IPointRecordService{
	@Resource
	private IPointRecordDao pointRecordDao;
	@Resource
	private IPointStrategyService pointStrategyService;
	@Resource
	private ApplicationContext applicationContext;

	@Override
	public Response createPointRecord(PointRecord pointRecord) {
		return createPointRecord(pointRecord, true);
	}
	
	@Override
	public Response createPointRecord(PointRecord pointRecord, boolean reCreate) {
		if(pointRecord == null){
			return Response.failInstance().responseMsg("create fail! pointRecord is null");
		}
		if(StringUtils.isEmpty(pointRecord.getId())){
			pointRecord.setId(Identities.uuid2());
		}
		pointRecord.setDefaultValue();
		int count = 0;
		if (reCreate) {
			count = pointRecordDao.insertPointRecord(pointRecord);
		}else{
			count = pointRecordDao.insertPointRecordIfNotExists(pointRecord);

		}
		if(count>0){
			applicationContext.publishEvent(new CreatePointRecordEvent(pointRecord));
			applicationContext.publishEvent(new ChangePointRecordEvent(pointRecord));
		}
		return count>0?Response.successInstance():Response.failInstance();
	}

	@Override
	public Response updatePointRecord(PointRecord pointRecord) {
		pointRecord.setUpdateValue();
		return pointRecordDao.updatePointRecord(pointRecord)>0?Response.successInstance():Response.failInstance();
	}

	@Override
	public PointRecord findPointRecordById(String id) {
		return pointRecordDao.selectPointRecordById(id);
	}

	@Override
	public List<PointRecord> findPointRecords(PointRecord pointRecord, PageBounds pageBounds) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PointRecord> findPointRecords(Map<String, Object> parameter, PageBounds pageBounds) {
		return pointRecordDao.findAll(parameter, pageBounds);
	}


	@Override
	public Map<String, Float> findUserPoint(List<String> userIds, String relationId,String pointStrategyRelationId) {
		Map<String,Float> result = Maps.newHashMap();
		for(String userId:userIds){
			result.put(userId, 0f);
		}
		Map<String,Object> parameter = Maps.newHashMap();
		parameter.put("userIds", userIds);
		parameter.put("relationId",relationId);
		parameter.put("pointStrategyRelationId",pointStrategyRelationId);
		List<UserPointCount> userPointCounts = this.pointRecordDao.selectUserPointCount(parameter);
		result.putAll(Collections3.extractToMap(userPointCounts, "user.id", "point"));
		return result;
	}

	@Override
	public float findUserPoint(String userId, String relationId,String pointStrategyRelationId ) {
		List<String> userIds = Lists.newArrayList();
		userIds.add(userId);
		Map<String,Float> result = findUserPoint(userIds,relationId,pointStrategyRelationId);
		return result.get(userId)==null?0:result.get(userId);
	}

	@Override
	public Response deletePointRecord(String pointStrategyId, String userId, String relationId, String entityId) {
		if(StringUtils.isAnyEmpty(userId,relationId)){
			return Response.failInstance().responseMsg("userId,relationId is null");
		}
		PointRecord pointRecord = new PointRecord();
		pointRecord.setPointStrategy(new PointStrategy(pointStrategyId));
		pointRecord.setUserId(userId);
		pointRecord.setRelationId(relationId);
		pointRecord.setEntityId(entityId);
		pointRecord.setUpdateValue();
		int count = this.pointRecordDao.deletePointRecordByLogic(pointRecord);
		if(count>0){
			applicationContext.publishEvent(new DeletePointRecordEvent(pointRecord));
			applicationContext.publishEvent(new ChangePointRecordEvent(pointRecord));
		}
		return count>0?Response.successInstance():Response.failInstance();
	}

	@Override
	public Response deletePointRecord(String pointStrategyId, String userId, String relationId) {
		return deletePointRecord(pointStrategyId, userId, relationId, null);
	}

	@Override
	public Response restorePointRecord(String pointStrategyId, String userId, String relationId, String entityId) {
		if(StringUtils.isAnyEmpty(userId,relationId)){
			return Response.failInstance().responseMsg("userId,relationId is null");
		}
		PointRecord pointRecord = new PointRecord();
		pointRecord.setPointStrategy(new PointStrategy(pointStrategyId));
		pointRecord.setUserId(userId);
		pointRecord.setRelationId(relationId);
		pointRecord.setEntityId(entityId);
		pointRecord.setUpdateValue();
		int count = this.pointRecordDao.restorePointRecord(pointRecord);
		if(count>0){
			applicationContext.publishEvent(new CreatePointRecordEvent(pointRecord));
			applicationContext.publishEvent(new ChangePointRecordEvent(pointRecord));
		}
		return count>0?Response.successInstance():Response.failInstance();
	}
	
	@Override
	public int getCount(PointRecord pointRecord) {
		return pointRecordDao.getCount(pointRecord);
	}

	@Override
	public Response deletePointRecord(String relationId, String entityId) {
		if(StringUtils.isAnyEmpty(entityId,relationId)){
			return Response.failInstance().responseMsg("entityId,relationId is null");
		}
		PointRecord pointRecord = new PointRecord();
		pointRecord.setRelationId(relationId);
		pointRecord.setEntityId(entityId);
		pointRecord.setUpdateValue();
		int count = this.pointRecordDao.deletePointRecordByLogic(pointRecord);
		if(count>0){
			applicationContext.publishEvent(new DeletePointRecordEvent(pointRecord));
			applicationContext.publishEvent(new ChangePointRecordEvent(pointRecord));
		}
		return count>0?Response.successInstance():Response.failInstance();
	}

	@Override
	public Map<String,Float> findUserExpendPoint(Map<String, Object> param) {
		List<UserPointCount> userExpendPointCounts = pointRecordDao.findUserExpendPoint(param);
		Map<String,Float> result = Maps.newHashMap();
		result.putAll(Collections3.extractToMap(userExpendPointCounts, "user.id", "point"));
		return result;
	}


}
