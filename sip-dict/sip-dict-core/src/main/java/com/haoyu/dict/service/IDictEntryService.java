package com.haoyu.dict.service;


import java.util.List;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.dict.entity.DictEntry;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.web.SearchParam;

/**
 * 字典服务接口
 * @author hqy
 *
 */
public interface IDictEntryService {
	/** 增  */
	public Response create(DictEntry dictEntry);
	/** 删  dictEntry.id 可包括多个id，中间用逗句隔开  */
	public Response delete(DictEntry dictEntry);
	/** 改*/
	public Response update(DictEntry dictEntry);	
	/** 查 */
	public DictEntry get(String id);
	/** 查 条件查询   */
	public List<DictEntry> list(SearchParam searchParam, PageBounds pageBounds);
	
}
