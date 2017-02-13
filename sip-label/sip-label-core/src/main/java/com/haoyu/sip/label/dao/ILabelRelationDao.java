package com.haoyu.sip.label.dao;

import java.util.Map;

public interface ILabelRelationDao {

	int deleteByRelationId(String relationId);

	int insertByIds(Map<String, Object> param);

}
