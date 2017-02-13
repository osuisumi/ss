package com.haoyu.sip.point.dao;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.sip.point.entity.PointRecord;
import com.haoyu.sip.point.utils.UserPointCount;

public interface IPointRecordDao {
	
	PointRecord selectPointRecordById(String id);

	int insertPointRecord(PointRecord pointRecord);

	int updatePointRecord(PointRecord pointRecord);

	int deletePointRecordByLogic(PointRecord pointRecord);

	List<PointRecord> findAll(Map<String, Object> parameter, PageBounds pageBounds);
	
	List<UserPointCount> selectUserPointCount(Map<String,Object> parameter);

	int insertPointRecordIfNotExists(PointRecord pointRecord);

	int restorePointRecord(PointRecord pointRecord);

	int getCount(PointRecord pointRecord);

	List<UserPointCount> findUserExpendPoint(Map<String, Object> param);
	
}
