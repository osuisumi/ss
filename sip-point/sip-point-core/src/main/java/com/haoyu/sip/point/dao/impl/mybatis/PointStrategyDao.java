package com.haoyu.sip.point.dao.impl.mybatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.sip.core.jdbc.MybatisDao;
import com.haoyu.sip.point.dao.IPointStrategyDao;
import com.haoyu.sip.point.entity.PointStrategy;

@Repository
public class PointStrategyDao extends MybatisDao implements IPointStrategyDao{

	@Override
	public PointStrategy selectPointStrategyById(String id) {
		return super.selectByPrimaryKey(id);
	}

	@Override
	public int insertPointStrategy(PointStrategy pointStrategy) {
		return super.insert("insert",pointStrategy);
	}

	@Override
	public int updatePointStrategy(PointStrategy pointStrategy) {
		return super.update("update", pointStrategy);
	}

	@Override
	public int deletePointStrategyByLogic(PointStrategy pointStrategy) {
		return super.deleteByLogic(pointStrategy);
	}

	@Override
	public int deletePointStrategyByPhysics(String id) {
		return super.deleteByPhysics(id);
	}

	@Override
	public List<PointStrategy> findAll(Map<String, Object> parameter, PageBounds pageBounds) {
		return super.selectList("select",parameter,pageBounds);
	}

}
