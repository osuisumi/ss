package com.haoyu.sip.message.service;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.message.entity.Message;

public interface IMessageService {
	Response createMessage(Message message);

	Response updateMessage(Message message);

	Response deleteMessage(Message message);

	Message findMessageById(String id);

	List<Message> findMessages(Message message, PageBounds pageBounds);

	List<Message> findMessages(Map<String, Object> parameter, PageBounds pageBounds);
	
	Response sendMessageToAllUser(Message message);
	
	Response sendMessageToUsers(Message message,List<String> receiveIds);

}
