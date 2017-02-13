package com.haoyu.sip.textbook.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.base.utils.BaseServiceUtils;
import com.haoyu.sip.core.jdbc.MybatisDao;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.textbook.dao.ITextBookEntryDao;
import com.haoyu.sip.textbook.entity.TextBookEntry;
import com.haoyu.sip.textbook.service.ITextBookEntryService;
import com.haoyu.sip.textbook.utils.TextBookUtils;
import com.haoyu.sip.utils.Identities;

@Service
public class TextBookEntryService implements ITextBookEntryService{
	@Resource
	private ITextBookEntryDao textBookEntryDao;

	@Override
	public Response create(TextBookEntry textBookEntry) {
		//textBookEntry.setId(Identities.uuid2());
		if(StringUtils.isEmpty(textBookEntry.getId())){
			textBookEntry.setId(Identities.uuid2());
		}
		textBookEntry.setDefaultValue();
		Integer max = this.getMaxValue(textBookEntry);
		textBookEntry.setTextBookValue(String.valueOf(max));
		textBookEntry.setSortNo(max);
		int count = ((MybatisDao)textBookEntryDao).insert("insert",textBookEntry);
		Response response =  null;
		if(count>0){
			response = Response.successInstance();
			response.setResponseData(textBookEntry);
			TextBookUtils.init(textBookEntry.getTextBookTypeCode());
		}else{
			response = Response.failInstance();
		}
		return response;
	}

	@Override
	public Response update(TextBookEntry textBookEntry) {
		return BaseServiceUtils.update(textBookEntry, (MybatisDao)textBookEntryDao);
	}

	@Override
	public Response deleteByLogic(TextBookEntry textBookEntry) {
		return BaseServiceUtils.delete(textBookEntry.getId(), (MybatisDao)textBookEntryDao);
	}

	@Override
	public TextBookEntry get(TextBookEntry textBookEntry) {
		return (TextBookEntry) BaseServiceUtils.get(textBookEntry.getId(), (MybatisDao)textBookEntryDao);
	}

	@Override
	public List<TextBookEntry> list(TextBookEntry textBookEntry, PageBounds pageBounds) {
		return ((MybatisDao)textBookEntryDao).selectList("select", textBookEntry, pageBounds);
	}

	@Override
	public Integer getMaxValue(TextBookEntry textBookEntry) {
		Integer count = ((MybatisDao)textBookEntryDao).selectOne("getMaxValue", textBookEntry);
		return count +1;
	}

	@Override
	public Map<String, TextBookEntry> selectForMap(TextBookEntry textBookEntry) {
		return ((MybatisDao)textBookEntryDao).selectMap("select", textBookEntry,"textBookValue");
	}

}
