package com.haoyu.sip.evaluate.dao.impl.mybatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.sip.core.jdbc.MybatisDao;
import com.haoyu.sip.evaluate.dao.IEvaluateRelationDao;
import com.haoyu.sip.evaluate.entity.EvaluateRelation;

@Repository
public class EvaluateRelationDao extends MybatisDao implements IEvaluateRelationDao{

	@Override
	public List<EvaluateRelation> select(Map<String, Object> param, PageBounds pageBounds) {
		return selectList("select", param, pageBounds);
	}

	@Override
	public int insert(EvaluateRelation evaluateRelation) {
		return super.insert(evaluateRelation);
	}

	@Override
	public int update(EvaluateRelation evaluateRelation) {
		return super.update(evaluateRelation);
	}

	@Override
	public EvaluateRelation selectByPrimaryKey(String id) {
		return super.selectByPrimaryKey(id);
	}

}
