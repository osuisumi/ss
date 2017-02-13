package com.haoyu.sip.comment.listener;

import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.haoyu.sip.comment.entity.Comment;
import com.haoyu.sip.comment.event.CreateCommentEvent;
import com.haoyu.sip.core.utils.PropertiesLoader;

@Component
public class ClearCache_CreateCommentListener implements ApplicationListener<CreateCommentEvent>{
	
	@Resource
	private RedisTemplate redisTemplate;
	
	@Override
	public void onApplicationEvent(CreateCommentEvent event) {
		Comment comment = (Comment) event.getSource();
		String relationId = comment.getRelation().getId();
		String key = PropertiesLoader.get("redis.app.key") + ":list_comment:" + relationId + ":*";
		if (StringUtils.isNotEmpty(relationId)) {
			Set<String> keys = redisTemplate.keys(key);
			redisTemplate.delete(keys);
		}
	}
}
