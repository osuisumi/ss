package com.haoyu.sip.evaluate.dao;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.sip.evaluate.entity.EvaluateRelation;

public interface IEvaluateRelationDao {

	List<EvaluateRelation> select(Map<String, Object> param, PageBounds pageBounds);

	int insert(EvaluateRelation evaluateRelation);

	int update(EvaluateRelation evaluateRelation);

	EvaluateRelation selectByPrimaryKey(String id);

}
