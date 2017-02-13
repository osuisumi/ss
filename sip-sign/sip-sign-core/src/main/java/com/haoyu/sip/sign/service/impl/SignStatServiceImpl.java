package com.haoyu.sip.sign.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.utils.ThreadContext;
import com.haoyu.sip.sign.dao.ISignStatDao;
import com.haoyu.sip.sign.entity.SignStat;
import com.haoyu.sip.sign.service.ISignStatService;
import com.haoyu.sip.utils.Identities;

@Service
public class SignStatServiceImpl implements ISignStatService{
	
	@Resource
	private ISignStatDao signStatDao;

	@Override
	public Response updateSignStat(SignStat signStat) {
		signStat.setUpdatedby(ThreadContext.getUser());
		signStat.setUpdateTime(System.currentTimeMillis());
		int count = signStatDao.update(signStat);
		return count>0?Response.successInstance():Response.failInstance();
	}

	@Override
	public Response createSignStat(SignStat signStat) {
		if (StringUtils.isEmpty(signStat.getId())) {
			signStat.setId(Identities.uuid2());
		}
		signStat.setDefaultValue();
		int count = signStatDao.insert(signStat);
		return count>0?Response.successInstance():Response.failInstance();
	}

	@Override
	public SignStat getSignStat(String id) {
		return signStatDao.selectById(id);
	}

	@Override
	public List<SignStat> listSignStatRank(Map<String, Object> param, PageBounds pageBounds) {
		return signStatDao.selectRank(param, pageBounds);
	}

	@Override
	public int getCount(Map<String, Object> param) {
		return signStatDao.getCount(param);
	}

}
