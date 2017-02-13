package com.haoyu.dict.dao.impl.mybatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.dict.dao.IDictTypeDao;
import com.haoyu.dict.entity.DictType;
import com.haoyu.sip.core.jdbc.MybatisDao;
/**
 * 字典类型dao接口实现
 * @author hqy
 *
 */
@Repository
public class DictTypeDao extends MybatisDao implements IDictTypeDao{

	@Override
	public int create(DictType dictType) {
		return this.insert("insertSelective", dictType);
	}

	@Override
	public int deleteByDictTypeCodes(Map<String,Object> paramMap) {
		return this.delete("deleteByDictTypeCodes", paramMap);
	}

	@Override
	public int update(DictType dictType) {
		return this.update("updateSelective", dictType);
	}

	@Override
	public int checkLogical(DictType dictType) {
		return this.selectOne("checkLogical", dictType);
	}

	@Override
	public DictType get(String dictTypeCode) {
		return this.selectOne("selectByDictTypeCode", dictTypeCode);
	}
	
	@Override
	public List<DictType> list(Map<String,Object> paramMap, PageBounds pageBounds) {
		return this.selectList("select", paramMap, pageBounds);
	}

	

	
}
