package com.haoyu.sip.file.service;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.web.SearchParam;
import com.haoyu.sip.file.entity.FileResource;

public interface IFileResourceService {
	
	Response create(FileResource obj);
	
	Response update(FileResource obj);
	
	Response delete(String id);
	
	FileResource get(String id);

	List<FileResource> list(SearchParam searchParam, PageBounds pageBounds);
	
	Response updateFileCount(String id);

	Response createFileResource(FileResource fileResource);

	Response updateFileResourceParent(FileResource fileResource);

	int getCount(Map<String, Object> param);

}
