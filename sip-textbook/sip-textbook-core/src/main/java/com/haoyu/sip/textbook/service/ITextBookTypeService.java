package com.haoyu.sip.textbook.service;

import java.util.List;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.textbook.entity.TextBookType;

public interface ITextBookTypeService {
	
	Response create(TextBookType textBookType);

	Response update(TextBookType textBookType);

	Response deleteByLogic(TextBookType TextBookType);

	TextBookType get(TextBookType textBookType);

	List<TextBookType> list(TextBookType textBookType,PageBounds pageBounds);

}
