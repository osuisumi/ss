package com.haoyu.dict.service.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.dict.dao.IDictEntryDao;
import com.haoyu.dict.dao.IDictTypeDao;
import com.haoyu.dict.entity.DictType;
import com.haoyu.dict.service.IDictTypeService;
import com.haoyu.dict.utils.DictionaryUtils;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.web.SearchParam;
/**
 * 字典类型服务接口实现
 * @author hqy
 *
 */
@Service
public class DictTypeService implements IDictTypeService{

	@Resource
	private IDictTypeDao dictTypeDao;
	@Resource
	private IDictEntryDao dictEntryDao;
	
	@Override
	public Response create(DictType dictType) {
		int i = dictTypeDao.checkLogical(dictType);
		if(i>0){
			/*已冲突*/
			return Response.failInstance();
		}	
		dictType.setDefaultValue();
		return dictTypeDao.create(dictType) > 0 ? Response.successInstance():Response.failInstance();	
	}

	@Override
	public Response delete(DictType dictType) {
		String[] array = dictType.getDictTypeCode().split(",");
		List<String> dictTypeCodes = Arrays.asList(array);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("dictTypeCodes", dictTypeCodes);
		int count = dictTypeDao.deleteByDictTypeCodes(map);
		if(count > 0 ){	
			/** 删除 dictEntry中的无用数据*/
			dictEntryDao.deleteByDictTypeCodesPhysics(map);
			/*清缓存 */
			DictionaryUtils.init(array);			
			return Response.successInstance();
		}
		return  Response.failInstance();
	}

	@Override
	public Response update(DictType dictType) {		
		dictType.setUpdateValue();
		int count = dictTypeDao.update(dictType);
		if(count > 0 ){
			/* 清缓存 */
			DictionaryUtils.init(dictType.getDictTypeCode());
			return Response.successInstance();
		}
		return  Response.failInstance();
	}

	@Override
	public Response checkLogical(DictType dictType) {
		return dictTypeDao.checkLogical(dictType) > 0 ? Response.failInstance():Response.successInstance();
	}
	
	@Override
	public DictType get(String dictTypeCode) {
		return dictTypeDao.get(dictTypeCode);
	}
	
	@Override
	public List<DictType> list(SearchParam searchParam, PageBounds pageBounds) {
		return dictTypeDao.list(searchParam.getParamMap(), pageBounds);
	}


}
