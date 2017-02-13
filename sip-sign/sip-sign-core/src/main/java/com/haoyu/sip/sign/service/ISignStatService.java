package com.haoyu.sip.sign.service;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.sign.entity.SignStat;

public interface ISignStatService {

	Response updateSignStat(SignStat signStat);

	Response createSignStat(SignStat signStat);

	SignStat getSignStat(String id);

	List<SignStat> listSignStatRank(Map<String, Object> param, PageBounds pageBounds);

	int getCount(Map<String, Object> param);

}
