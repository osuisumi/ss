package com.haoyu.sip.evaluate.service;

import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.evaluate.entity.Evaluate;

public interface IEvaluateService {

	Response createEvaluate(Evaluate evaluate);
	
	Evaluate getEvaluate(String id);
}
