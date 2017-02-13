package com.haoyu.sip.evaluate.service;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.evaluate.entity.EvaluateItem;

public interface IEvaluateItemService {
	
	Response createEvaluateItem(EvaluateItem evaluateItem);
	
	Response updateEvaluateItem(EvaluateItem evaluateItem);
	
	Response deleteEvaluateItemByLogic(EvaluateItem evaluateItem);
	
	List<EvaluateItem> listEvaluateItems(Map<String, Object> param, PageBounds pageBounds);

}
