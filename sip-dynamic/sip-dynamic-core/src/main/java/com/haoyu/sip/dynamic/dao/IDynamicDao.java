package com.haoyu.sip.dynamic.dao;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.sip.dynamic.entity.Dynamic;

public interface IDynamicDao{
	
	public List<Dynamic> select(Map<String,Object> param,PageBounds pageBounds);
	
	public int update(Map<String,Object> parameter);
	
	public int batchDelete(List<String> ids);
	
}