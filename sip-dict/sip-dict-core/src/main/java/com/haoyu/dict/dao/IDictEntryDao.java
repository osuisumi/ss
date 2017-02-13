package com.haoyu.dict.dao;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.dict.entity.DictEntry;

/**
 * 字典  dao层接口
 * @author hqy
 *
 */
public interface IDictEntryDao {
	/** 增 */
	public int create(DictEntry dictEntry);
	/**
     * 删除ids中所有id对应的数据
     */
	public int deleteByIdsPhysics(Map<String,Object> map);
	/** 
	 * 删除
	 */
	public int deleteByDictTypeCodesPhysics(Map<String, Object> map);
	
	/**
     * 更新
     */
	public int update(DictEntry dictEntry);	
	
	/** 查 */
	public DictEntry get(String id);
	/** 合理性检查
	 * 在数据插入前或更新前，确保DICT_TYPE_CODE和DICT_VALUE组合唯一  根据返回值再决定是否要插入或更新 */
	public int checkLogical(DictEntry dictEntry);
	/** 查  条件查询 */
	public List<DictEntry> list(Map<String, Object> paramMap, PageBounds pageBounds);
	/** 通过ids，获取所有dictTypeCode */
	public List<String> getDictTypeCodesByIds(Map<String,Object> map);
	
	public String getMaxDictValue(DictEntry dictEntry);
	
	
}
