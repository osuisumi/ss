package com.haoyu.sip.status.service;

import java.util.List;
import java.util.Map;

import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.status.entity.Status;

public interface IStatusService {
	
	Map<String, Map<String, Status>> mapStatus(List<String> relationIds);
	
	Map<String, Status> mapStatus(String relationId);
	
	Response updateStatus(Status status);
	
}
