package com.haoyu.sip.tag.service;

import java.util.List;

import com.haoyu.sip.core.entity.Relation;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.tag.entity.Tag;
import com.haoyu.sip.tag.entity.TagRelation;

public interface ITagRelationService {

	Response createTagRelation(TagRelation tagRelation);
	
	/**
	 * 添加标签关联关系
	 * @param tags 标签
	 * @param relation 关系 包含id和type
	 * @param clean 是否清楚relation对应的tags关联信息 如果为true则清除，默认为false不清除
	 * @return
	 */
	Response createTagRelation(List<Tag> tags,Relation relation,boolean clean);
	
	Response deleteTagRelation(TagRelation tagRelation);
	
	Response deleteTagRelationByRelation(Relation relation);
	
	List<TagRelation> findTagRelationsByRelationIds(List<String> relationIds);
	
	
}
