package com.haoyu.sip.evaluate.dao.impl.mybatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.sip.core.jdbc.MybatisDao;
import com.haoyu.sip.evaluate.dao.IEvaluateItemDao;
import com.haoyu.sip.evaluate.entity.EvaluateItem;

@Repository
public class EvaluateItemDao extends MybatisDao implements IEvaluateItemDao{

	@Override
	public int insert(EvaluateItem evaluateItem) {
		return super.insert(evaluateItem);
	}

	@Override
	public int update(EvaluateItem evaluateItem) {
		return super.update(evaluateItem);
	}

	@Override
	public int deleteByLogic(Map<String, Object> param) {
		return super.deleteByLogic(param);
	}

	@Override
	public List<EvaluateItem> select(Map<String, Object> param, PageBounds pageBounds) {
		return selectList("param", param, pageBounds);
	}

}
