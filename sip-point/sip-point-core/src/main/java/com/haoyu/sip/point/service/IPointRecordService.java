package com.haoyu.sip.point.service;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.point.entity.PointRecord;

public interface IPointRecordService {
	
	Response createPointRecord(PointRecord pointRecord, boolean reCreate);
	
	Response createPointRecord(PointRecord pointRecord);
	
	Response updatePointRecord(PointRecord pointRecord);
	
	PointRecord findPointRecordById(String id);
	
	List<PointRecord> findPointRecords(PointRecord pointRecord,PageBounds pageBounds);
	
	List<PointRecord> findPointRecords(Map<String,Object> parameter,PageBounds pageBounds);
	
	Map<String,Float> findUserPoint(List<String> userIds,String relationId,String pointStrategyRelationId);
	
	float findUserPoint(String userId,String relationId,String pointStrategyRelationId);

	Response deletePointRecord(String pointStrategyId, String userId, String relationId, String entityId);
	
	Response deletePointRecord(String pointStrategyId,String userId,String relationId);
	
	Response restorePointRecord(String pointStrategyId, String userId, String relationId, String entityId);

	int getCount(PointRecord pointRecord);
	
	Response deletePointRecord(String relationId, String entityId);

	Map<String,Float> findUserExpendPoint(Map<String, Object> param);
}
