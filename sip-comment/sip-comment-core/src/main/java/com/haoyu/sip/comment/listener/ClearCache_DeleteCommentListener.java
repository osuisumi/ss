package com.haoyu.sip.comment.listener;

import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.haoyu.sip.comment.entity.Comment;
import com.haoyu.sip.comment.event.DeleteCommentEvent;
import com.haoyu.sip.comment.service.ICommentService;
import com.haoyu.sip.core.utils.PropertiesLoader;

@Component
public class ClearCache_DeleteCommentListener implements ApplicationListener<DeleteCommentEvent>{
	
	@Resource
	private RedisTemplate redisTemplate;
	@Resource
	private ICommentService commentService;
	
	@Override
	public void onApplicationEvent(DeleteCommentEvent event) {
		Comment comment = (Comment) event.getSource();
		String relationId = comment.getRelation().getId();
		String key = PropertiesLoader.get("redis.app.key") + ":list_comment:" + relationId + ":*";
		if (StringUtils.isNotEmpty(relationId)) {
			Set<String> keys = redisTemplate.keys(key);
			redisTemplate.delete(keys);
		}
	}
}
