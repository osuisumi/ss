package com.haoyu.sip.dynamic.service;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.dynamic.entity.Dynamic;

public interface IDynamicService{
	public abstract Response create(Dynamic dynamic);

	public abstract Response update(Dynamic dynamic);
	
	public Response update(Map<String,Object> parameter);

	public abstract Response delete(Dynamic dynamic);

	public abstract Dynamic get(String id);

	public abstract List<Dynamic> list(Dynamic dynamic, PageBounds paramPageBounds);
    /** 查询动态，最初需求：查一个学校的5条最新动态 hqy */
	public abstract List<Dynamic> selectDynamic(Map<String,Object> paramMap, PageBounds paramPageBounds);
}
