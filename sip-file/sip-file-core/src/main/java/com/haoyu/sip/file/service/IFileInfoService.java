package com.haoyu.sip.file.service;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.web.SearchParam;
import com.haoyu.sip.file.entity.FileInfo;

public interface IFileInfoService {

	Response create(FileInfo obj);
	
	Response update(FileInfo obj);
	
	Response delete(String id);
	
	FileInfo get(String id);
	
	List<FileInfo> list(SearchParam searchParam, PageBounds pageBounds);

	Response createFileInfo(FileInfo fileInfo);

	int getCount(Map<String, Object> param);

	List<FileInfo> listFileInfo(Map<String, Object> param, PageBounds pageBounds);
}
