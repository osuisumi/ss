package com.haoyu.dict.service;

import java.util.List;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.dict.entity.DictType;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.web.SearchParam;

/**
 * 字典索引服务接口
 * 
 * @author hqy
 *
 */
public interface IDictTypeService {
	/** 增 插入前检查 dictTypeCode是否已存在 */
	public Response create(DictType dictType);

	/** 删 记得清楚缓存 试一下String[]的区别 */
	public Response delete(DictType dictType);

	/** 改 记得清缓存 */
	public Response update(DictType dictType);

	/** 检查合理性*/
	public Response checkLogical(DictType dictType);
	/** 查  通过dictTypeCode*/
	public DictType get(String dictTypeCode);
	
	/** 条件查询 */
	public List<DictType> list(SearchParam searchParam, PageBounds pageBounds);
}
