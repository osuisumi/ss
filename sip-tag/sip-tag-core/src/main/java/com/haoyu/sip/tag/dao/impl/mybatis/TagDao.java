/**
 * 
 */
package com.haoyu.sip.tag.dao.impl.mybatis;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.google.common.collect.Maps;
import com.haoyu.sip.core.jdbc.MybatisDao;
import com.haoyu.sip.tag.dao.ITagDao;
import com.haoyu.sip.tag.entity.Tag;

/**
 * @author lianghuahuang
 *
 */
@Repository
public class TagDao extends MybatisDao implements ITagDao {

	/* (non-Javadoc)
	 * @see com.haoyu.sip.tag.dao.ITagDao#selectTagById(java.lang.String)
	 */
	@Override
	public Tag selectTagById(String id) {
		return super.selectByPrimaryKey(id);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.tag.dao.ITagDao#selectTagByLikeName(java.lang.String)
	 */
	@Override
	public List<Tag> selectTagByLikeName(String name,PageBounds pageBounds) {
		return super.selectList("selectTagByLikeName", name, pageBounds);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.tag.dao.ITagDao#selectTagByNameAndRelations(java.lang.String, java.util.List)
	 */
	@Override
	public List<Tag> selectTagByNameAndRelations(String name,
			List<String> relations,PageBounds pageBounds) {
		Map<String,Object> paramMap = Maps.newHashMap();
		if(StringUtils.isNotEmpty(name)){
			paramMap.put("name", name);
		}
		if(relations!=null&&!relations.isEmpty()){
			paramMap.put("relations", relations);
		}
		return super.selectList("selectTagByNameAndRelations", paramMap, pageBounds);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.tag.dao.ITagDao#insertTag(com.haoyu.sip.tag.entity.Tag)
	 */
	@Override
	public int insertTag(Tag tag) {
		tag.setDefaultValue();
		return super.insert(tag);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.tag.dao.ITagDao#deleteTagByLogic(com.haoyu.sip.tag.entity.Tag)
	 */
	@Override
	public int deleteTagByLogic(Tag tag) {
		tag.setUpdateValue();
		return super.deleteByLogic(tag);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.tag.dao.ITagDao#deleteTagByPhysics(java.lang.String)
	 */
	@Override
	public int deleteTagByPhysics(String id) {
		return super.deleteByPhysics(id);
	}

	@Override
	public int updateTag(Tag tag) {
		tag.setUpdateValue();
		return super.update(tag);
	}

	@Override
	public List<Tag> selectTagByRelationType(String relationType) {
		Map<String,Object> paramMap = Maps.newHashMap();
		if(StringUtils.isNotEmpty(relationType)){
			paramMap.put("relationType", relationType);
		}
		return super.selectList("selectTagByNameAndRelations", paramMap);
	}

}
