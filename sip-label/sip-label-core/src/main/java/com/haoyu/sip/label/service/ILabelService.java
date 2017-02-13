package com.haoyu.sip.label.service;

import java.util.List;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.web.SearchParam;
import com.haoyu.sip.label.entity.Label;

public interface ILabelService {
	
	Response create(Label obj);
	
	Response update(Label obj);
	
	Response delete(String id);
	
	Label get(String id);
	
	List<Label> list(SearchParam searchParam, PageBounds pageBounds);
	
	/**
	 * 添加或更新标签接口
	 * @param ids 新标签ID集合
	 * @param relationId 关系ID(如研讨ID,备课ID) 
	 * @param type	类型(如DISCUSSION, LESSONPLAN)
	 * @param creator
	 * @return
	 */
	Response updateLabels(List<String> ids, String relationId, String type);
	
	List<Label> getLebalsByRelationId(String relationId);

	List<Label> getLebals(Label label, PageBounds pageBounds);

	Response createLabel(Label label);

}
