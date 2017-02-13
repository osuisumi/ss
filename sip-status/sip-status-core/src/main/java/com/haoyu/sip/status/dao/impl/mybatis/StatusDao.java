package com.haoyu.sip.status.dao.impl.mybatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.haoyu.sip.core.jdbc.MybatisDao;
import com.haoyu.sip.status.dao.IStatusDao;
import com.haoyu.sip.status.entity.Status;

@Repository
public class StatusDao extends MybatisDao implements IStatusDao{

	@Override
	public List<Status> select(Map<String, Object> param) {
		return selectList("select", param);
	}

	@Override
	public int update(Status status) {
		return super.update(status);
	}

	@Override
	public int insert(Status status) {
		return super.insert(status);
	}


}
