package com.haoyu.sip.status.dao;

import java.util.List;
import java.util.Map;

import com.haoyu.sip.status.entity.Status;

public interface IStatusDao {

	List<Status> select(Map<String, Object> param);

	int update(Status status);

	int insert(Status status);

}
