package com.haoyu.sip.comment.service;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.sip.comment.entity.Comment;
import com.haoyu.sip.comment.entity.CommentStat;
import com.haoyu.sip.core.entity.Relation;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.web.SearchParam;


public interface ICommentService{
	           
	Response create(Comment obj);
	
	Response update(Comment obj);
	
	Response delete(String id);
	
	Comment get(String id);
	
	List<Comment> list(SearchParam searchParam, PageBounds pageBounds);
	
	List<Comment> list(SearchParam searchParam,PageBounds pageBounds,boolean isInCludeChildren);
	
	Response updateChildNum(String id);
	
	Response createComment(Comment comment);
	
	Response updateComment(Comment comment);
	
	Response deleteComment(Comment comment);
	
	List<Comment> findCommentByRelation(Relation relation, PageBounds pageBounds);
	
	CommentStat getCommentStatByRelation(Relation relation);
	
	Map<String,CommentStat> getCommentStatByRelations(List<String> relationIds);

	int getCount(Map<String, Object> paramMap);

	List<Comment> list(Comment comment, PageBounds pageBounds);
	
	List<Comment> list(Map<String, Object> param, PageBounds pageBounds);

	Map<String, Integer> getChildNum(Map<String, Object> param);

}
