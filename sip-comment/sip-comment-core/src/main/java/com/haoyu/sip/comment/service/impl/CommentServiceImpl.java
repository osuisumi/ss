package com.haoyu.sip.comment.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.google.common.collect.Maps;
import com.haoyu.base.utils.BaseServiceUtils;
import com.haoyu.sip.comment.dao.ICommentDao;
import com.haoyu.sip.comment.entity.Comment;
import com.haoyu.sip.comment.entity.CommentStat;
import com.haoyu.sip.comment.event.CreateCommentEvent;
import com.haoyu.sip.comment.event.DeleteCommentEvent;
import com.haoyu.sip.comment.event.UpdateCommentEvent;
import com.haoyu.sip.comment.service.ICommentService;
import com.haoyu.sip.core.entity.Relation;
import com.haoyu.sip.core.jdbc.MybatisDao;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.web.SearchParam;
import com.haoyu.sip.utils.Identities;

@Service
public class CommentServiceImpl implements ICommentService{

	@Resource
	private ICommentDao commentDao;
	
	@Autowired
	private ApplicationContext applicationContext;
	
	@Override
	public Response create(Comment obj) {
		return BaseServiceUtils.create(obj, (MybatisDao)commentDao);
	}

	@Override
	public Response update(Comment obj) {
		return BaseServiceUtils.update(obj, (MybatisDao)commentDao);
	}

	@Override
	public Response delete(String id) {
		return BaseServiceUtils.delete(id, (MybatisDao)commentDao);
	}

	@Override
	public Comment get(String id) {
		return (Comment) BaseServiceUtils.get(id, (MybatisDao)commentDao);
	}

	@Override
	public List<Comment> list(SearchParam searchParam, PageBounds pageBounds) {
		return ((MybatisDao)commentDao).selectList("select", searchParam.getParamMap(), pageBounds);
	}
	
	@Override
	public List<Comment> list(SearchParam searchParam, PageBounds pageBounds, boolean isIncludeChildren) {
		if(isIncludeChildren){
			searchParam.getParamMap().put("isIncludeChildren", true);
		}
		return this.list(searchParam, pageBounds);
	}

	@Override
	public Response updateChildNum(String id) {
		Comment comment = new Comment();
		comment.setId(id);
		comment.setChildNum(1);
		return this.update(comment);
	}

	@Override
	public Response createComment(Comment comment) {
		if(comment==null){
			return Response.failInstance().responseMsg("createComment fail!because comment is null!");
		}
		if(StringUtils.isEmpty(comment.getRelation().getId())){
			return Response.failInstance().responseMsg("createAttitudeUser fail!attitude or relation.id is null");
		}
		if(StringUtils.isEmpty(comment.getId())){
			comment.setId(Identities.uuid2());
		}
		comment.setDefaultValue();
		int	count = commentDao.insertComment(comment);
		if(count>0){
			if (StringUtils.isNotEmpty(comment.getMainId())) {
				this.updateChildNum(comment.getMainId());
			}
			applicationContext.publishEvent(new CreateCommentEvent(comment));
		}
		return count>0?Response.successInstance().responseData(comment):Response.failInstance().responseMsg("createComment fail!");
	}

	@Override
	public Response updateComment(Comment comment) {
		if(comment==null||StringUtils.isEmpty(comment.getId())){
			return Response.failInstance().responseMsg("updateComment fail!comment is null or comment.id is null");
		}
		int count =  commentDao.updateComment(comment);
		if (count > 0) {
			applicationContext.publishEvent(new UpdateCommentEvent(comment));
		}
		return count>0?Response.successInstance().responseData(comment):Response.failInstance().responseMsg("updateComment fail!");
	}

	@Override
	public Response deleteComment(Comment comment) {
		comment = this.get(comment.getId());
		if(comment==null||StringUtils.isEmpty(comment.getId())){
			return Response.failInstance().responseMsg("deleteComment fail!comment is null or comment.id is null");
		}
		int count =  commentDao.deleteCommentByLogic(comment);
		if(count>0){
			applicationContext.publishEvent(new DeleteCommentEvent(comment));
		}
		return count>0?Response.successInstance():Response.failInstance().responseMsg("deleteComment fail!");
	}

	@Override
	public List<Comment> findCommentByRelation(Relation relation,
			PageBounds pageBounds) {
		Comment comment  = new Comment();
		comment.setRelation(relation);
		return commentDao.selectByComment(comment, pageBounds);
	}

	@Override
	public CommentStat getCommentStatByRelation(Relation relation) {
		return commentDao.selectCommentStatByRelation(relation);
	}

	@Override
	public Map<String, CommentStat> getCommentStatByRelations(
			List<String> relationIds) {
		return commentDao.selectCommentStatByRelations(relationIds);
	}

	@Override
	public int getCount(Map<String, Object> paramMap) {
		return commentDao.selectCount(paramMap);
	}

	@Override
	public List<Comment> list(Comment comment, PageBounds pageBounds) {
		SearchParam searchParam = new SearchParam();
		Map<String,Object> param = Maps.newHashMap();
		if (comment.getRelation() != null && StringUtils.isNotEmpty(comment.getRelation().getId())) {
			param.put("relationIds",Arrays.asList(comment.getRelation().getId().split(",")));
		}
		if (StringUtils.isNotEmpty(comment.getMainId())) {
			param.put("mainId",comment.getMainId());
		}
		searchParam.setParamMap(param);
		return this.list(searchParam, pageBounds);
	}

	@Override
	public List<Comment> list(Map<String, Object> param, PageBounds pageBounds) {
		return this.commentDao.findAll(param, pageBounds);
	}

	@Override
	public Map<String, Integer> getChildNum(Map<String, Object> param) {
		Map<String, Map<String, Integer>> map = commentDao.getChildNum(param);
		Map<String, Integer> countMap = Maps.newHashMap();
		Number num = 0;
		for(String key : map.keySet()) {
			num = (Number)map.get(key).get("count");
			countMap.put(key,num.intValue());
		}
		return countMap;
	}

}
