package com.haoyu.sip.label.dao.impl.mybatis;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.haoyu.sip.core.jdbc.MybatisDao;
import com.haoyu.sip.label.dao.ILabelRelationDao;

@Repository
public class LabelRelationDao extends MybatisDao implements ILabelRelationDao{

	public int insertByIds(Map<String, Object> param) {
		return insert("insertByIds", param);
	}

	@Override
	public int deleteByRelationId(String relationId) {
		return update("deleteByRelationId", relationId);
	}

}
