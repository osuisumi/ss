package com.haoyu.sip.file.dao.impl.mybatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.sip.core.jdbc.MybatisDao;
import com.haoyu.sip.core.utils.ThreadContext;
import com.haoyu.sip.file.dao.IFileResourceDao;
import com.haoyu.sip.file.entity.FileResource;

@Repository
public class FileResourceDao extends MybatisDao implements IFileResourceDao{
	
	public List<FileResource> select(Map<String, Object> paramMap, PageBounds pageBounds) {
		return selectList("select", paramMap, pageBounds);
	}

	@Override
	public int updateChildFileParent(String id) {
		FileResource fileResource = new FileResource();
		fileResource.setId(id);
		fileResource.setUpdateTime(System.currentTimeMillis());
		fileResource.setUpdatedby(ThreadContext.getUser());
		return update("updateChildFileParent", fileResource);
	}

	@Override
	public int getCount(Map<String, Object> param) {
		return selectOne("getCount", param);
	}

}
