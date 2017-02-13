/**
 * 
 */
package com.haoyu.sip.tag.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.tag.dao.ITagDao;
import com.haoyu.sip.tag.entity.Tag;
import com.haoyu.sip.tag.entity.TagRelation;
import com.haoyu.sip.tag.service.ITagRelationService;
import com.haoyu.sip.tag.service.ITagService;
import com.haoyu.sip.utils.Collections3;

/**
 * @author lianghuahuang
 *
 */
@Service("tagService")
public class TagServiceImpl implements ITagService {
	@Autowired
	private ITagDao tagDao;
	/* (non-Javadoc)
	 * @see com.haoyu.sip.tag.service.ITagService#createTag(com.haoyu.sip.tag.entity.Tag)
	 */
	@Autowired
	private ITagRelationService tagRelationService;
	@Override
	public Response createTag(Tag tag) {
		if(tag==null){
			return Response.failInstance().responseMsg("createTag fail!tag is null!");
		}
		//防止出现重复标签添加
		tag.setId(DigestUtils.md5Hex(tag.getName()));
		int count = 0;
		try{
			count = tagDao.insertTag(tag);
		}catch(DuplicateKeyException e){
			//标签重复导致主键冲突
			Response.failInstance().responseMsg("duplicate create tag!");
		}
		return count>0?Response.successInstance().responseData(tag):Response.failInstance().responseMsg("createTag is fail");
	}

	public void setTagDao(ITagDao tagDao) {
		this.tagDao = tagDao;
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.tag.service.ITagService#updateTag(com.haoyu.sip.tag.entity.Tag)
	 */
	@Override
	public Response updateTag(Tag tag) {
		int count  = tagDao.updateTag(tag);
		return count>0?Response.successInstance().responseData(tag):Response.failInstance().responseMsg("updateTag is fail");
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.tag.service.ITagService#deleteTag(java.lang.String, java.lang.String)
	 */
	@Override
	public Response deleteTag(Tag tag) {
		if(tag==null||StringUtils.isEmpty(tag.getId())){
			return Response.failInstance().responseMsg("delete Tag fail,because tag is null or tag.id is null!");
		}
		int count  = tagDao.deleteTagByLogic(tag);
		return count>0?Response.successInstance():Response.failInstance();
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.tag.service.ITagService#findTagById(java.lang.String)
	 */
	@Override
	public Tag findTagById(String id) {
		return tagDao.selectTagById(id);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.tag.service.ITagService#findTagByLikeName(java.lang.String, com.github.miemiedev.mybatis.paginator.domain.PageBounds)
	 */
	@Override
	public List<Tag> findTagByLikeName(String name, PageBounds pageBounds) {
		return tagDao.selectTagByLikeName(name, pageBounds);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.tag.service.ITagService#findTagByNameAndRelations(java.lang.String, java.util.List, com.github.miemiedev.mybatis.paginator.domain.PageBounds)
	 */
	@Override
	public List<Tag> findTagByNameAndRelations(String name,
			List<String> relations, PageBounds pageBounds) {
		return tagDao.selectTagByNameAndRelations(name, relations, pageBounds);
	}

	@Override
	public List<Tag> findTasByRelationType(String relationType) {
		return tagDao.selectTagByRelationType(relationType);
	}

	@Override
	public Map<String, List<Tag>> findEntityTagsByEntityIds(List<String> relationIds) {
		Map<String,List<Tag>> result = new HashMap<String,List<Tag>>();
		for(String relationId:relationIds){
			List<Tag> tags = new ArrayList<Tag>();
			result.put(relationId, tags);
		}
		List<TagRelation> tagRelations = tagRelationService.findTagRelationsByRelationIds(relationIds);
		List<Tag> allTags = this.findTagByNameAndRelations(null,relationIds , null);
		Map<String,Tag> allTagsMap = Collections3.extractToMap(allTags, "id", null);
		if(!CollectionUtils.isEmpty(tagRelations)){
			for(TagRelation tr:tagRelations){
				result.get(tr.getRelation().getId()).add(allTagsMap.get(tr.getTag().getId()));
			}
		}
		return result;
	}

}
