package com.haoyu.sip.comment.dao.impl.mybatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.sip.comment.dao.ICommentDao;
import com.haoyu.sip.comment.entity.Comment;
import com.haoyu.sip.comment.entity.CommentStat;
import com.haoyu.sip.core.entity.Relation;
import com.haoyu.sip.core.jdbc.MybatisDao;
import com.haoyu.sip.core.web.SearchParam;

@Repository
public class CommentDao extends MybatisDao implements ICommentDao{

	public List<Comment> select(SearchParam searchParam, PageBounds pageBounds) {
		return selectList("select", searchParam.getParamMap(), pageBounds);
	}

	@Override
	public int insertComment(Comment comment) {
		comment.setDefaultValue();
		return super.insert(comment);
	}

	@Override
	public int updateComment(Comment comment) {
		comment.setUpdateValue();
		return super.update(comment);
	}

	@Override
	public int deleteCommentByLogic(Comment comment) {
		comment.setUpdateValue();
		return super.deleteByLogic(comment);
	}

	@Override
	public List<Comment> selectByComment(Comment comment, PageBounds pageBounds) {
		return super.selectList("selectByComment", comment, pageBounds);
	}

	@Override
	public CommentStat selectCommentStatByRelation(Relation relation) {
		return super.selectOne("selectCommentStatByRelation", relation);
	}

	@Override
	public Map<String, CommentStat> selectCommentStatByRelations(
			List<String> relationIds) {
		return super.selectMap("selectCommentStatByRelations", relationIds, "relationId");
	}

	@Override
	public int selectCount(Map<String, Object> paramMap) {
		return super.selectOne("selectCount", paramMap);
	}

	@Override
	public List<Comment> findAll(Map<String, Object> parameter, PageBounds pageBounds) {
		return selectList("select", parameter, pageBounds);
	}

	@Override
	public Map<String, Map<String, Integer>> getChildNum(Map<String, Object> param) {
		return super.selectMap("getChildNum", param,"id");
	}
	
}
