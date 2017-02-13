package com.haoyu.sip.textbook.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.base.utils.BaseServiceUtils;
import com.haoyu.sip.core.jdbc.MybatisDao;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.textbook.dao.ITextBookTypeDao;
import com.haoyu.sip.textbook.entity.TextBookType;
import com.haoyu.sip.textbook.service.ITextBookTypeService;

@Service
public class TextBookTypeService implements ITextBookTypeService{
	@Resource
	private ITextBookTypeDao textBookTypeDao;

	@Override
	public Response create(TextBookType textBookType) {
		return BaseServiceUtils.create(textBookType,(MybatisDao)textBookTypeDao );
	}

	@Override
	public Response update(TextBookType textBookType) {
		return BaseServiceUtils.update(textBookType,(MybatisDao)textBookTypeDao );
	}

	@Override
	public Response deleteByLogic(TextBookType TextBookType) {
		return BaseServiceUtils.delete(TextBookType.getId(),(MybatisDao)textBookTypeDao );
	}

	@Override
	public TextBookType get(TextBookType textBookType) {
		return (TextBookType) BaseServiceUtils.get(textBookType.getId(),(MybatisDao)textBookTypeDao );
	}

	@Override
	public List<TextBookType> list(TextBookType textBookType, PageBounds pageBounds) {
		return ((MybatisDao)textBookTypeDao).selectList("select", textBookType, pageBounds);
	}

}
