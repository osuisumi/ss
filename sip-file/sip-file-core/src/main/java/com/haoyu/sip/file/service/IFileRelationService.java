package com.haoyu.sip.file.service;

import java.util.List;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.web.SearchParam;
import com.haoyu.sip.file.entity.FileRelation;

public interface IFileRelationService {
	
	Response create(FileRelation obj);
	
	Response update(FileRelation obj);
	
	Response delete(String id);
	
	FileRelation get(String id);

	List<FileRelation> list(SearchParam searchParam, PageBounds pageBounds);

	Response deleteFileRelation(String fileId, String relationId);

}
