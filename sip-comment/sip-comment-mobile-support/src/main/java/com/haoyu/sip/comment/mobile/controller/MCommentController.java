package com.haoyu.sip.comment.mobile.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.haoyu.sip.comment.entity.Comment;
import com.haoyu.sip.comment.mobile.service.IMCommentService;
import com.haoyu.sip.comment.service.ICommentService;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.web.AbstractBaseMobileController;

@RestController
@RequestMapping("**/m/comment")
public class MCommentController extends AbstractBaseMobileController{

	@Resource
	private ICommentService commentService;
	@Resource
	private IMCommentService commentMobileService;
	
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public Response list(Comment comment) {
		return commentMobileService.listComment(comment,getPageBounds(10,true));
	}
	
	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public Response create(Comment comment) {
		return commentMobileService.createComment(comment);
	}

	@RequestMapping(value="{id}",method=RequestMethod.PUT)
	@ResponseBody
	public Response update(Comment comment){
		return commentMobileService.updateComment(comment);
	}
	
	@RequestMapping(value="{id}",method = RequestMethod.DELETE)
	@ResponseBody
	public Response delete(Comment comment) {
		return commentService.deleteComment(comment);
	}

}
