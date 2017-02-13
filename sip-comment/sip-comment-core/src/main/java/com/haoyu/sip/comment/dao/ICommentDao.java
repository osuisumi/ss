package com.haoyu.sip.comment.dao;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.sip.comment.entity.Comment;
import com.haoyu.sip.comment.entity.CommentStat;
import com.haoyu.sip.core.entity.Relation;
import com.haoyu.sip.core.web.SearchParam;


public interface ICommentDao{
	public List<Comment> select(SearchParam searchParam, PageBounds pageBounds);
	
	int insertComment(Comment comment);
	
	int updateComment(Comment comment);
	
	int deleteCommentByLogic(Comment comment);
	
	List<Comment> selectByComment(Comment comment,PageBounds pageBounds);
	
	CommentStat selectCommentStatByRelation(Relation relation);
	
	Map<String,CommentStat> selectCommentStatByRelations(List<String> relationIds);

	int selectCount(Map<String, Object> paramMap);
	
	List<Comment> findAll(Map<String, Object> parameter, PageBounds pageBounds);

	Map<String, Map<String, Integer>> getChildNum(Map<String, Object> param);
}
