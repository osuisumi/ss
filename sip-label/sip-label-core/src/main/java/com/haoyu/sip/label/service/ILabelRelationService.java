package com.haoyu.sip.label.service;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.web.SearchParam;
import com.haoyu.sip.label.entity.LabelRelation;

public interface ILabelRelationService {

	Response create(LabelRelation obj);
	
	Response update(LabelRelation obj);
	
	Response delete(String id);
	
	LabelRelation get(String id);
	
	List<LabelRelation> list(SearchParam searchParam, PageBounds pageBounds);

	Response deleteByRelationId(String relationId);

	Response insertByIds(Map<String, Object> param);

}
