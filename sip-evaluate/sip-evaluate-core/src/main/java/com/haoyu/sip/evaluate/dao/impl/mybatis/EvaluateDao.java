package com.haoyu.sip.evaluate.dao.impl.mybatis;

import org.springframework.stereotype.Repository;

import com.haoyu.sip.core.jdbc.MybatisDao;
import com.haoyu.sip.evaluate.dao.IEvaluateDao;
import com.haoyu.sip.evaluate.entity.Evaluate;

@Repository
public class EvaluateDao extends MybatisDao implements IEvaluateDao{

	@Override
	public int insert(Evaluate evaluate) {
		return super.insert(evaluate);
	}

	@Override
	public Evaluate selectByPrimaryKey(String id) {
		return super.selectByPrimaryKey(id);
	}
	
}
