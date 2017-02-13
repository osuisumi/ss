/**
 * 
 */
package com.haoyu.sip.tag.dao;

import java.util.List;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.sip.tag.entity.Tag;

/**
 * @author lianghuahuang
 *
 */
public interface ITagDao {
	Tag selectTagById(String id);
	
	List<Tag> selectTagByLikeName(String name,PageBounds pageBounds);
	
	List<Tag> selectTagByNameAndRelations(String name,List<String> relations,PageBounds pageBounds);
	
	List<Tag> selectTagByRelationType(String relationType);
	
	int insertTag(Tag tag);
	
	int updateTag(Tag tag);
	
	int deleteTagByLogic(Tag tag);
	
	int deleteTagByPhysics(String id);
}
