package com.haoyu.sip.evaluate.dao;

import com.haoyu.sip.evaluate.entity.Evaluate;

public interface IEvaluateDao {

	int insert(Evaluate evaluate);

	Evaluate selectByPrimaryKey(String id);
}
