package com.haoyu.sip.evaluate.dao;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.sip.evaluate.entity.EvaluateItem;

public interface IEvaluateItemDao {

	int insert(EvaluateItem evaluateItem);

	int update(EvaluateItem evaluateItem);

	int deleteByLogic(Map<String, Object> param);

	List<EvaluateItem> select(Map<String, Object> param, PageBounds pageBounds);

}
