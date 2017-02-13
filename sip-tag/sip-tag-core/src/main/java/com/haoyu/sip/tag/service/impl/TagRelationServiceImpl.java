/**
 * 
 */
package com.haoyu.sip.tag.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.haoyu.sip.core.entity.Relation;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.tag.dao.ITagRelationDao;
import com.haoyu.sip.tag.entity.Tag;
import com.haoyu.sip.tag.entity.TagRelation;
import com.haoyu.sip.tag.service.ITagRelationService;
import com.haoyu.sip.utils.Collections3;

/**
 * @author lianghuahuang
 *
 */
@Service
public class TagRelationServiceImpl implements ITagRelationService {
	@Autowired
	private ITagRelationDao tagRelationDao;
	/* (non-Javadoc)
	 * @see com.haoyu.sip.tag.service.ITagRelationService#createTagRelation(com.haoyu.sip.tag.entity.TagRelation)
	 */
	@Override
	public Response createTagRelation(TagRelation tagRelation) {
		int count = tagRelationDao.insertTagRelation(tagRelation);
		return count>0?Response.successInstance().responseData(tagRelation):Response.failInstance().responseMsg("createTagRelation is fail");
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.tag.service.ITagRelationService#createTagRelation(java.util.List, com.haoyu.sip.core.entity.Relation, java.lang.String, boolean)
	 */
	@Override
	public Response createTagRelation(List<Tag> tags, Relation relation, boolean clean) {
		TagRelation tr = new TagRelation();
		tr.setRelation(relation);
		if(clean){
			tagRelationDao.deleteTagRelation(tr);
		}
		if (Collections3.isNotEmpty(tags)) {
			int count = tagRelationDao.insertTagRelationByTagsAndRelation(tags, tr);
			return count>0?Response.successInstance().responseData(tags):Response.failInstance().responseMsg("createTagRelation is fail");
		}
		return Response.successInstance();
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.tag.service.ITagRelationService#deleteTagRelation(com.haoyu.sip.tag.entity.TagRelation)
	 */
	@Override
	public Response deleteTagRelation(TagRelation tagRelation) {
		int count  =tagRelationDao.deleteTagRelation(tagRelation);
		return count>0?Response.successInstance():Response.failInstance().responseMsg("deleteTagRelation is fail");
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.tag.service.ITagRelationService#deleteTagRelationByRelation(com.haoyu.sip.core.entity.Relation)
	 */
	@Override
	public Response deleteTagRelationByRelation(Relation relation) {
		TagRelation tr = new TagRelation();
		tr.setRelation(relation);
		int count  =tagRelationDao.deleteTagRelation(tr);
		return count>0?Response.successInstance():Response.failInstance().responseMsg("deleteTagRelationByRelation is fail");
	}

	@Override
	public List<TagRelation> findTagRelationsByRelationIds(List<String> relationIds) {
		return tagRelationDao.findTagRelationByRelationIds(relationIds);
	}

}
