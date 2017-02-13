package com.haoyu.sip.textbook.service;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.textbook.entity.TextBookEntry;
import com.haoyu.sip.textbook.entity.TextBookRelation;

public interface ITextBookRelationService {
	
	Response create(TextBookRelation textBookRelation);

	Response update(TextBookRelation textBookRelation);

	Response deleteByLogic(TextBookRelation TextBookRelation);

	TextBookRelation get(TextBookRelation textBookrelation);

	List<TextBookRelation> list(TextBookRelation textBookRelation,PageBounds pageBounds);
	
	List<TextBookEntry> listEntry(TextBookRelation textBookRelation,PageBounds pageBounds);
	
	Response deleteByPhysics(TextBookRelation textBookRelation);
	
	Map<String,TextBookRelation> selectForMap(TextBookRelation textBookRelation);
	
	Map<String, List<String>> importUtil(String url);
	

}
