package com.haoyu.sip.comment.mobile.service.impl;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.Order;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.github.miemiedev.mybatis.paginator.domain.Paginator;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.haoyu.sip.attitude.entity.AttitudeStat;
import com.haoyu.sip.attitude.service.IAttitudeService;
import com.haoyu.sip.comment.entity.Comment;
import com.haoyu.sip.comment.mobile.entity.MComment;
import com.haoyu.sip.comment.mobile.service.IMCommentService;
import com.haoyu.sip.comment.service.ICommentService;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.utils.BeanUtils;
import com.haoyu.sip.core.utils.PropertiesLoader;
import com.haoyu.sip.core.utils.ThreadContext;
import com.haoyu.sip.user.mobile.entity.MUser;
import com.haoyu.sip.utils.Collections3;

@Service
public class MCommentServiceImpl implements IMCommentService{

	@Resource
	private ICommentService commentService;
	@Resource
	private RedisTemplate redisTemplate;
	@Resource
	private IAttitudeService attitudeService;
	
	@Override
	public Response listComment(Comment comment, PageBounds pageBounds) {
		List<MComment> mComments = Lists.newArrayList();
		Map<String, Object> param = Maps.newHashMap();
		if (comment.getRelation() != null) {
			if (StringUtils.isNotEmpty(comment.getRelation().getId())) {				
				param.put("relationId",comment.getRelation().getId());
			}
			if (StringUtils.isNotEmpty(comment.getRelation().getType())) {				
				param.put("relationType",comment.getRelation().getType());
			}
		}
		
		if (StringUtils.isNotEmpty(comment.getMainId())) {
			param.put("mainId",comment.getMainId());
		}
		
		if (Collections3.isNotEmpty(pageBounds.getOrders())) {
			pageBounds.setOrders(Order.formString("CREATE_TIME.DESC"));
		}
		
		String relationId = (String) param.get("relationId");
		String key = PropertiesLoader.get("redis.app.key") + ":list_comment:" + relationId + ":" + DigestUtils.md5Hex(param.toString() + pageBounds.toString());
		ValueOperations<String, List<Comment>> valueOper = redisTemplate.opsForValue();
		List<Comment> comments = Lists.newArrayList();
		
		if (redisTemplate.hasKey(key)) {
			comments = valueOper.get(key);
		}else{
			comments = commentService.list(param, pageBounds);
			valueOper.set(key, comments);
			redisTemplate.expire(key, 2, TimeUnit.HOURS);
		} 
		
		PageList pageList = (PageList)comments;
		Paginator paginator = pageList.getPaginator();
		
		if (Collections3.isNotEmpty(comments)) {
			Map<String,AttitudeStat> attitudeStatMap = Maps.newHashMap();
			param = Maps.newHashMap();
			param.put("relationIds",Collections3.extractToList(comments, "id"));
			param.put("attitude","support");
			attitudeStatMap = attitudeService.getAttitudeStatByParam(param);
			
			for (Comment c : comments) {
				MComment mComment = new MComment();
				BeanUtils.copyProperties(c, mComment);
				
				if (c.getCreator() != null) {
					MUser mUser = new MUser();
					BeanUtils.copyProperties(c.getCreator(), mUser);
					mComment.setCreator(mUser);
				}
				
				if (attitudeStatMap.get(c.getId()) != null) {
					mComment.setSupportNum(attitudeStatMap.get(c.getId()).getParticipateNum());
				}
				mComments.add(mComment);
			}
		}
		
		Map<String, Object> resultMap = Maps.newHashMap();
		resultMap.put("mComments",mComments);
		resultMap.put("paginator",paginator);
		return Response.successInstance().responseData(resultMap);
	}

	@Override
	public Response updateComment(Comment comment) {
		Response response = commentService.updateComment(comment);
		if (response.isSuccess()) {
			MComment mComment = new MComment();
			if (response.getResponseData() != null) {
				comment = (Comment) response.getResponseData();
				BeanUtils.copyProperties(comment,mComment);
				
				if (comment.getCreator() != null) {
					MUser mUser = new MUser();
					BeanUtils.copyProperties(comment.getCreator(), mUser);
					mComment.setCreator(mUser);
				}
				
				Map<String,AttitudeStat> attitudeStatMap = Maps.newHashMap();
				Map<String,Object>param = Maps.newHashMap();
				param.put("relationIds",Lists.newArrayList(comment.getId()));
				param.put("attitude","support");
				attitudeStatMap = attitudeService.getAttitudeStatByParam(param);
				
				if (attitudeStatMap.get(comment.getId()) != null) {
					mComment.setSupportNum(attitudeStatMap.get(comment.getId()).getParticipateNum());
				}
			}
			return Response.successInstance().responseData(mComment);
		}
		return response;
	}

	@Override
	public Response createComment(Comment comment) {
		if(ThreadContext.getUser() != null && StringUtils.isNotEmpty(ThreadContext.getUser().getId())){
			comment.setCreator(ThreadContext.getUser());
			Response response = commentService.createComment(comment);
			if (response.isSuccess()) {
				MComment mComment = new MComment();
				if (response.getResponseData() != null) {	
					comment = (Comment) response.getResponseData();
					BeanUtils.copyProperties(comment,mComment);
					
					if (comment.getCreator() != null) {
						MUser mUser = new MUser();
						BeanUtils.copyProperties(comment.getCreator(), mUser);
						mComment.setCreator(mUser);
					}
				}
				return Response.successInstance().responseData(mComment);
			}
			return response;
		}
		return Response.failInstance();
	}

}
