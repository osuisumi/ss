package com.haoyu.sip.sign.service.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.haoyu.sip.core.entity.Relation;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.utils.ThreadContext;
import com.haoyu.sip.sign.dao.ISignUserDao;
import com.haoyu.sip.sign.entity.SignStat;
import com.haoyu.sip.sign.entity.SignUser;
import com.haoyu.sip.sign.event.CreateSignUserEvent;
import com.haoyu.sip.sign.service.ISignStatService;
import com.haoyu.sip.sign.service.ISignUserService;

@Service
public class SignUserServiceImpl implements ISignUserService{
	
	@Resource
	private ISignUserDao signUserDao;
	@Resource
	private ISignStatService signStatService;
	@Resource
	private ApplicationContext applicationContext;

	@Override
	public Response createSignUser(String relationId) {
		String signStatId = SignStat.getId(relationId, ThreadContext.getUser().getId());
		String today = DateFormatUtils.format(new Date(), "yyyy-MM-dd");
		SignUser signUser = new SignUser();
		signUser.setId(SignUser.getId(signStatId, today));
		signUser.setSignStat(new SignStat(signStatId));
		signUser.setDefaultValue();
		int count = 0;
		try {
			count = signUserDao.insert(signUser);
		} catch (DuplicateKeyException e) {
			return Response.failInstance().responseMsg("you have signed today");
		}
		if (count > 0) {
			try {
				SignStat signStat = signStatService.getSignStat(signStatId);
				if (signStat != null && signStat.getLastSignTime() != null) {
					Date now = DateUtils.parseDate(today, "yyyy-MM-dd");
					if (now.compareTo(DateUtils.addDays(signStat.getLastSignTime(), 1)) <= 0) {
						signStat.setSignLastNum(signStat.getSignLastNum().add(BigDecimal.valueOf(1)));
					}else{
						signStat.setSignLastNum(BigDecimal.valueOf(1));
					}
				}else{
					signStat = new SignStat();
					signStat.setSignLastNum(BigDecimal.valueOf(1));
				}
				signStat.setId(signStatId);
				signStat.setSignNum(BigDecimal.valueOf(1));
				signStat.setLastSignTime(DateUtils.parseDate(today, "yyyy-MM-dd"));
				signStat.setRelation(new Relation(relationId));
				Response response = signStatService.updateSignStat(signStat);
				if (!response.isSuccess()) {
					response = signStatService.createSignStat(signStat);
				}
				if (response.isSuccess()) {
					applicationContext.publishEvent(new CreateSignUserEvent(signUser));
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return Response.successInstance();
	}

}
