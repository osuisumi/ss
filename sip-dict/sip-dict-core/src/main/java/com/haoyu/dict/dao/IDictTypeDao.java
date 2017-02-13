package com.haoyu.dict.dao;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.dict.entity.DictType;
import com.haoyu.sip.core.service.Response;

/**
 * 字典索引dao服务接口
 * @author  hqy
 *
 */
public interface IDictTypeDao {
	/** 增  */
	public int create(DictType dictType);
	/** 删 */
	public int deleteByDictTypeCodes(Map<String,Object> paramMap);
	/** 改  */
	public int update(DictType dictType);
	/** 合理性 检查  确保 dictTypeCode唯一 */
	public int checkLogical(DictType dictType);
	/** 查  通过dictTypeCode*/
	public DictType get(String dictTypeCode);
	
	/** 查  */
	public List<DictType> list(Map<String,Object> paramMap,PageBounds pageBounds);
	
}
