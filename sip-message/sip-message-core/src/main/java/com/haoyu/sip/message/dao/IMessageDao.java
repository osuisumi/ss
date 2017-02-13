package com.haoyu.sip.message.dao;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.sip.message.entity.Message;

public interface IMessageDao {
	
	Message selectMessageById(String id);
	
	int insertMessage(Message message);
	
	int batchInsert(Map<String,Object> parameter);
	
	int updateMessage(Message message);
	
	int deleteMessageByLogic(Message message);
	
	int deleteMessageByPhysics(String id);

	List<Message> findAll(Map<String, Object> parameter, PageBounds pageBounds);

}
