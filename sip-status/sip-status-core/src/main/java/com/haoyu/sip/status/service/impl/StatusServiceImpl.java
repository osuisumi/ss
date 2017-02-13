package com.haoyu.sip.status.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;
import com.haoyu.sip.core.entity.Relation;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.status.dao.IStatusDao;
import com.haoyu.sip.status.entity.Status;
import com.haoyu.sip.status.event.UpdateStatusEvent;
import com.haoyu.sip.status.service.IStatusService;
import com.haoyu.sip.utils.Collections3;

@Service
public class StatusServiceImpl implements IStatusService{

	@Resource
	private IStatusDao statusDao;
	@Resource
	private ApplicationContext applicationContext;
	
	@Override
	public Map<String, Map<String, Status>> mapStatus(List<String> relationIds) {
		Map<String, Object> param = Maps.newHashMap();
		param.put("relationIds", relationIds);
		List<Status> statuses = statusDao.select(param);
		Map<String, Map<String, Status>> statusMap = Maps.newHashMap();
		if (Collections3.isNotEmpty(statuses)) {
			for (Status status : statuses) {
				if (!statusMap.containsKey(status.getRelation().getId())) {
					statusMap.put(status.getRelation().getId(), Maps.newHashMap());
				}
				Map<String, Status> map = statusMap.get(status.getRelation().getId());
				map.put(status.getState(), status);
			}
		}
		return statusMap;
	}

	@Override
	public Map<String, Status> mapStatus(String relationId) {
		Map<String, Object> param = Maps.newHashMap();
		param.put("relationId", relationId);
		List<Status> statuses = statusDao.select(param);
		Map<String, Status> statusMap = Maps.newHashMap();
		if (Collections3.isNotEmpty(statuses)) {
			for (Status status : statuses) {
				statusMap.put(status.getState(), status);
			}
		}
		return statusMap;
	}

	@Override
	public Response updateStatus(Status status) {
		String[] array = status.getRelation().getId().split(",");
		for (String relationId : array) {
			String id = Status.getId(relationId, status.getState());
			Status sta = new Status();
			sta.setId(id);
			Relation relation = new Relation();
			relation.setId(relationId);
			relation.setType(status.getRelation().getType());
			sta.setRelation(relation);
			sta.setDays(status.getDays());
			sta.setState(status.getState());
			sta.setLastSetTime(new Date());
			int count = statusDao.update(sta);
			if (count <= 0) {
				count = statusDao.insert(sta);
			}
			if (count > 0) {
				applicationContext.publishEvent(new UpdateStatusEvent(sta));
			}
		}
		return Response.successInstance();
	}

}
