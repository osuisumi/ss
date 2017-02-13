package com.haoyu.sip.file.dao;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.sip.file.entity.FileInfo;

public interface IFileInfoDao {

	int getCount(Map<String, Object> param);

	List<FileInfo> select(Map<String, Object> param, PageBounds pageBounds);
	
}
