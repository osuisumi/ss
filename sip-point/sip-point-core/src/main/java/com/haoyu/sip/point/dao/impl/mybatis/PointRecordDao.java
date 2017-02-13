package com.haoyu.sip.point.dao.impl.mybatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.sip.core.jdbc.MybatisDao;
import com.haoyu.sip.point.dao.IPointRecordDao;
import com.haoyu.sip.point.entity.PointRecord;
import com.haoyu.sip.point.utils.UserPointCount;

@Repository
public class PointRecordDao extends MybatisDao implements IPointRecordDao{

	@Override
	public PointRecord selectPointRecordById(String id) {
		return super.selectByPrimaryKey(id);
	}

	@Override
	public int insertPointRecord(PointRecord pointRecord) {
		return super.insert("insert", pointRecord);
	}

	@Override
	public int updatePointRecord(PointRecord pointRecord) {
		return super.update("update", pointRecord);
	}

	@Override
	public int deletePointRecordByLogic(PointRecord pointRecord) {
		return super.deleteByLogic(pointRecord);
	}

	@Override
	public List<PointRecord> findAll(Map<String, Object> parameter, PageBounds pageBounds) {
		return super.selectList("select", parameter, pageBounds);
	}

	@Override
	public List<UserPointCount> selectUserPointCount(Map<String, Object> parameter) {
		return super.selectList("selectUserPointCount",parameter);
	}

	@Override
	public int insertPointRecordIfNotExists(PointRecord pointRecord) {
		return super.insert("insertIfNotExists", pointRecord);
	}

	@Override
	public int restorePointRecord(PointRecord pointRecord) {
		return super.update("restore", pointRecord);
	}

	@Override
	public int getCount(PointRecord pointRecord) {
		return super.selectOne("getCount", pointRecord);
	}

	@Override
	public List<UserPointCount> findUserExpendPoint(Map<String, Object> param) {
		return super.selectList("selectUserExpendPoint", param);
	}
}
