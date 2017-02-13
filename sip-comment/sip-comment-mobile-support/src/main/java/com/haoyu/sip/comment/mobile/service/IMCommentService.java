package com.haoyu.sip.comment.mobile.service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.sip.comment.entity.Comment;
import com.haoyu.sip.core.service.Response;

public interface IMCommentService {

	Response listComment(Comment comment, PageBounds pageBounds);

	Response updateComment(Comment comment);

	Response createComment(Comment comment);

}
