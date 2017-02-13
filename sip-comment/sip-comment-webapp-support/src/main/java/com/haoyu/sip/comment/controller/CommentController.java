/**
 * 
 */
package com.haoyu.sip.comment.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.haoyu.sip.comment.entity.Comment;
import com.haoyu.sip.comment.service.ICommentService;
import com.haoyu.sip.core.entity.Relation;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.utils.ThreadContext;
import com.haoyu.sip.core.web.AbstractBaseController;
import com.haoyu.sip.core.web.SearchParam;

/**
 * @author lianghuahuang
 *
 */
@Controller
@RequestMapping("/comment")
public class CommentController extends AbstractBaseController {
	
	@Resource
	private ICommentService commentService;
	
	@RequestMapping(value="edit", method=RequestMethod.GET)
	public String edit(Comment comment, Model model){
		model.addAttribute("comments",commentService.get(comment.getId()));
		return "comment/edit_comment";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	@ResponseBody
	public Response create(Comment comment){
		return commentService.createComment(comment);
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
	@ResponseBody
	public Response delete(@PathVariable String id,Comment comment){
		comment.setId(id);
		return commentService.deleteComment(comment);
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.PUT)
	@ResponseBody
	public Response update(Comment comment){
		return commentService.updateComment(comment);
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public List<Comment> getComments(Relation relation){
		return commentService.findCommentByRelation(relation, this.getPageBounds(10, true));
	}
	
	@RequestMapping(value="list", method=RequestMethod.GET)
	public String list(SearchParam searchParam, Model model){
		List<Comment> comments = commentService.list(searchParam, getPageBounds(10, true));
		model.addAttribute("comments",comments);
		model.addAllAttributes(request.getParameterMap());
		return "comment/list_comment";
	}
	
	@RequestMapping(value = "child", method = RequestMethod.GET)
	public String more(SearchParam searchParam, Model model){
		List<Comment> comments = commentService.list(searchParam, null);
		model.addAttribute("comments",comments);
		model.addAllAttributes(request.getParameterMap());
		return "comment/list_child_comment";
	}
	
	@RequestMapping(value="getMyCommentNum",method=RequestMethod.GET)
	@ResponseBody
	public int getMyCommentNum(SearchParam searchParam){
		int myCommentNum = 0;
		if (ThreadContext.getUser() != null) {
			searchParam.getParamMap().put("creator", ThreadContext.getUser().getId());
			searchParam.getParamMap().put("mainId", "null");
			myCommentNum = commentService.getCount(searchParam.getParamMap());
		}
		return myCommentNum;
	}
	
	@RequestMapping(value="api",method = RequestMethod.GET)
	@ResponseBody
	public List<Comment> api(SearchParam searchParam,Model model){
		List<Comment> comments = commentService.list(searchParam.getParamMap(), getPageBounds(10, true));
		return comments;
	}
	
}
