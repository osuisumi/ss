/**
 * 
 */
package com.haoyu.sip.tag.dao;

import java.util.List;

import com.haoyu.sip.tag.entity.Tag;
import com.haoyu.sip.tag.entity.TagRelation;

/**
 * @author lianghuahuang
 *
 */
public interface ITagRelationDao {
	
	public int insertTagRelation(TagRelation tagRelation);
	
	public int deleteTagRelation(TagRelation tagRelation);
	
	public int insertTagRelationByTagsAndRelation(List<Tag> tags,TagRelation tagRelation);
	
	public List<TagRelation> findTagRelationByRelationIds(List<String> relationIds);
}
