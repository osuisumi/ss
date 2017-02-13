package com.haoyu.sip.textbook.service;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.textbook.entity.TextBookEntry;

public interface ITextBookEntryService {
	
	Response create(TextBookEntry textBookEntry);

	Response update(TextBookEntry textBookEntry);

	Response deleteByLogic(TextBookEntry textBookEntry);

	TextBookEntry get(TextBookEntry textBookEntry);

	List<TextBookEntry> list(TextBookEntry textBookEntry,PageBounds pageBounds);
	
	Integer getMaxValue(TextBookEntry textBookEntry);
	
	Map<String,TextBookEntry> selectForMap(TextBookEntry textBookEntry);

}
