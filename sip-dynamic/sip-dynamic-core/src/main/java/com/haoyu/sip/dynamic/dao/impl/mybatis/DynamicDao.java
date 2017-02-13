package com.haoyu.sip.dynamic.dao.impl.mybatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.sip.core.jdbc.MybatisDao;
import com.haoyu.sip.dynamic.dao.IDynamicDao;
import com.haoyu.sip.dynamic.entity.Dynamic;
@Repository
public class DynamicDao extends MybatisDao implements IDynamicDao{

	@Override
	public List<Dynamic> select(Map<String, Object> param, PageBounds pageBounds) {
		return super.selectList("selectByParameter", param, pageBounds);
	}

	@Override
	public int update(Map<String, Object> parameter) {
		return super.update("update", parameter);
	}

	@Override
	public int batchDelete(List<String> ids) {
		if(CollectionUtils.isEmpty(ids)){
			return 0;
		}
		return super.delete("batchDelete", ids);
	}

}
