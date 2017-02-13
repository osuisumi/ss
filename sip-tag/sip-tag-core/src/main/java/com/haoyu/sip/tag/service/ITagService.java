package com.haoyu.sip.tag.service;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.tag.entity.Tag;

public interface ITagService {

	Response createTag(Tag tag);
	
	Response updateTag(Tag tag);
	
	Response deleteTag(Tag tag);
	
	Tag findTagById(String id);
	
	List<Tag> findTagByLikeName(String name,PageBounds pageBounds);
	
	List<Tag> findTagByNameAndRelations(String name,List<String> relations,PageBounds pageBounds);
	
	Map<String,List<Tag>> findEntityTagsByEntityIds(List<String> relationIds);
	
	List<Tag> findTasByRelationType(String relationType);
	
}
