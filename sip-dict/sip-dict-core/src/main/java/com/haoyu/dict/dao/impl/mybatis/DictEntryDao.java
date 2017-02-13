package com.haoyu.dict.dao.impl.mybatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.dict.dao.IDictEntryDao;
import com.haoyu.dict.entity.DictEntry;
import com.haoyu.sip.core.jdbc.MybatisDao;

/**
 * 数据库访问
 * @author 
 */
@Repository 
public class DictEntryDao extends MybatisDao implements IDictEntryDao {

	/** hqy*/
	@Override
	public int create(DictEntry dictEntry){
		return this.insert("insertSelective", dictEntry);
	}
	
	/**
	 * 感觉这个没什么用
	public int delete(String id){
		return this.delete("deleteByPrimaryKeyPhysics", id);
	}
	 */
	/** hqy */
	@Override
	public int deleteByIdsPhysics(Map<String,Object> map) {
		return this.delete("deleteByIdsPhysics", map);
	}
	
	/** hqy */
	@Override
	public int deleteByDictTypeCodesPhysics(Map<String,Object> map) {
		return this.delete("deleteByDictTypeCodesPhysics", map);
	}
	
	/** hqy */
	@Override
	public int update(DictEntry dictEntry){
		 return this.update("updateByPrimaryKeySelective", dictEntry);
	}
	
	/** hqy*/
	@Override
	public int checkLogical(DictEntry dictEntry){
		return this.selectOne("checkLogical", dictEntry);
	}
	
	/** 
	 * 工具类中用到,按条件查询，其中 Map<String, DictEntry>中的key为dictValue
	 */
	public Map<String, DictEntry> selectByObjectForMap(DictEntry dictEntry) {
		return this.selectMap("selectByObjectForMap", dictEntry, "dictValue");
	}
	
	/** hqy */
	@Override
	public DictEntry get(String id){
		return this.selectByPrimaryKey(id);
	}
	
	/** hqy */
	public List<DictEntry> list(Map<String,Object> map, PageBounds pageBounds){
		return this.selectList("select", map, pageBounds);
	}
	
	/**hqy */
	@Override
	public List<String> getDictTypeCodesByIds(Map<String, Object> map) {
		return this.selectList("selectDictTypeCodesByIds",map);
	}
	
	
	/** 选择性查询 */
	@Deprecated
	public List<DictEntry> selectDictLike(DictEntry t) {
		return this.selectList("select", t);
	}
	/**
	 * 合理性检查
	 * 在数据插入前或更新前，确保DICT_TYPE_CODE和DICT_VALUE组合唯一  根据返回值再决定是否要插入或更新
	 */
	@Deprecated
	public int checkSameValue(DictEntry dictEntry) {
		return this.selectOne("checkLogical",dictEntry);
	}

	/**
     * 删除所有id（idArray）对应的数据
     */
	@Deprecated
	public int deleteByIdArray(String[] idArray) {
		return this.delete("deleteByIds", idArray);
	}

	@Override
	public String getMaxDictValue(DictEntry dictEntry) {
		return this.selectOne("getMaxDictValue", dictEntry);
	}
	
	
}
