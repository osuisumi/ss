package com.haoyu.sip.sign.dao;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.sip.sign.entity.SignStat;

public interface ISignStatDao {

	int update(SignStat signStat);

	int insert(SignStat signStat);

	SignStat selectById(String id);

	List<SignStat> selectRank(Map<String, Object> param, PageBounds pageBounds);

	int getCount(Map<String, Object> param);

}
