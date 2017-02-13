package com.haoyu.dict.service.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.dict.dao.IDictEntryDao;
import com.haoyu.dict.entity.DictEntry;
import com.haoyu.dict.service.IDictEntryService;
import com.haoyu.dict.utils.DictionaryUtils;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.web.SearchParam;
import com.haoyu.sip.utils.Identities;

/** *
 * 字典服务接口实现
 * @author hqy
 *
 */
@Service
public class DictEntryService implements IDictEntryService {

	@Resource
	private IDictEntryDao dictEntryDao;
	
	/** 选择性插入  先判断要插入的数据是否和数据库的数据有冲突 ：保证 确保DICT_TYPE_CODE和DICT_VALUE组合唯一  */
	@Override
	public Response create(DictEntry dictEntry) {
		if (StringUtils.isNotEmpty(dictEntry.getDictValue())) {
			int i = dictEntryDao.checkLogical(dictEntry);
			if(i>0){
				/*已冲突*/
				return Response.failInstance();
			}	
		}else{
			String maxValue = dictEntryDao.getMaxDictValue(dictEntry);
			if (StringUtils.isNotEmpty(maxValue)) {
				int num = Integer.parseInt(maxValue);
				String code = String.valueOf(num+1);
				if (code.length() < 2) {
					code = "0" + code;
				}
				dictEntry.setDictValue(code);
			}
		}
		dictEntry.setId(Identities.uuid2());
		dictEntry.setDefaultValue();
		int count = dictEntryDao.create(dictEntry);
		if(count > 0 ){
			//清缓存
			DictionaryUtils.init(dictEntry.getDictTypeCode());
			return Response.successInstance().responseData(dictEntry);
		}
		return Response.failInstance();	
	}

	/**
	 * 删除 记得清缓存
	 */
	@Override
	public Response delete(DictEntry dictEntry) {
		String[] array = dictEntry.getId().split(",");
		List<String> ids = Arrays.asList(array);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("ids", ids);
		int count = dictEntryDao.deleteByIdsPhysics(map);
		if(count > 0 ){
			/*清缓存 */
			List<String> dictTypeCodes = dictEntryDao.getDictTypeCodesByIds(map);
			DictionaryUtils.init(dictTypeCodes);
			return Response.successInstance();
		}
		return  Response.failInstance();	
	}

	/** 选择性更新  先判断要更新的数据是否和数据库的数据有冲突 ：保证 确保DICT_TYPE_CODE和DICT_VALUE组合唯一
	 *  确保 dictEntry.dictTypeCode要存在 */
	@Override
	public Response update(DictEntry dictEntry) {
		dictEntry.setUpdateValue();
		int count = dictEntryDao.update(dictEntry);
		if(count > 0 ){
			/* 清缓存 */
			DictionaryUtils.init(dictEntry.getDictTypeCode());
			return Response.successInstance();
		}
		return  Response.failInstance();
	}

	@Override
	public DictEntry get(String id) {
		return dictEntryDao.get(id);
	}

	/** 选择性查询 	直查 */
	@Override
	public List<DictEntry> list(SearchParam searchParam, PageBounds pageBounds) {
		return dictEntryDao.list(searchParam.getParamMap(), pageBounds);
	}

}
