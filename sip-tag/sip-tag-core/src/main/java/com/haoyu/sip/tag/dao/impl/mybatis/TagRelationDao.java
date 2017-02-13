/**
 * 
 */
package com.haoyu.sip.tag.dao.impl.mybatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import com.google.common.collect.Maps;
import com.haoyu.sip.core.entity.Relation;
import com.haoyu.sip.core.jdbc.MybatisDao;
import com.haoyu.sip.tag.dao.ITagRelationDao;
import com.haoyu.sip.tag.entity.Tag;
import com.haoyu.sip.tag.entity.TagRelation;

/**
 * @author lianghuahuang
 *
 */
@Repository
public class TagRelationDao extends MybatisDao implements ITagRelationDao {

	/* (non-Javadoc)
	 * @see com.haoyu.sip.tag.dao.ITagRelationDao#insertTagRelation(com.haoyu.sip.tag.entity.TagRelation)
	 */
	@Override
	public int insertTagRelation(TagRelation tagRelation) {
		return super.insert(tagRelation);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.tag.dao.ITagRelationDao#deleteTagRelationByRelation(com.haoyu.sip.core.entity.Relation)
	 */
	@Override
	public int deleteTagRelation(TagRelation tagRelation) {
		return super.delete("deleteByTagRelation", tagRelation);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.tag.dao.ITagRelationDao#insertTagRelationByTagsAndRelation(java.util.List, com.haoyu.sip.core.entity.Relation)
	 */
	@Override
	public int insertTagRelationByTagsAndRelation(List<Tag> tags,
			TagRelation tagRelation) {
		Map<String,Object> paramMap = Maps.newHashMap();
		paramMap.put("tags", tags);
		paramMap.put("tagRelation",tagRelation);
		return super.insert("insertByTagsAndRelation", paramMap);
	}

	@Override
	public List<TagRelation> findTagRelationByRelationIds(List<String> relationIds) {
		Map<String,Object> paramMap = Maps.newHashMap();
		if(!CollectionUtils.isEmpty(relationIds)){
			paramMap.put("relationIds", relationIds);
		}
		return super.selectList("selectByParameter", paramMap);
	}

}
