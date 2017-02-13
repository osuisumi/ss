package com.haoyu.sip.message.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.google.common.collect.Maps;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.utils.PropertiesLoader;
import com.haoyu.sip.message.dao.IMessageDao;
import com.haoyu.sip.message.entity.Message;
import com.haoyu.sip.message.service.IMessageService;
import com.haoyu.sip.utils.Identities;

@Service
public class MessageService implements IMessageService{
	@Resource
	private IMessageDao messageDao;
	@Resource
	private PropertiesLoader propertiesLoader;

	@Override
	public Response createMessage(Message message) {
		if(message == null){
			return Response.failInstance().responseMsg("create message fail! message is null");
		}
		if(!message.isEffective()){
			return Response.failInstance().responseMsg("create message fail! message is unEffective");
		}
		if(StringUtils.isEmpty(message.getId())){
			message.setId(Identities.uuid2());
		}
		return messageDao.insertMessage(message)>0?Response.successInstance().responseData(message):Response.failInstance();
	}

	@Override
	public Response updateMessage(Message message) {
		return messageDao.updateMessage(message)>0?Response.successInstance():Response.failInstance();
	}

	@Override
	public Response deleteMessage(Message message) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Message findMessageById(String id) {
		return messageDao.selectMessageById(id);
	}

	@Override
	public List<Message> findMessages(Message message, PageBounds pageBounds) {
		Map<String,Object> parameter = Maps.newHashMap();
		if(message!= null){
			if(message.getReceiver()!=null && StringUtils.isNotEmpty(message.getReceiver().getId())){
				parameter.put("receiverId", message.getReceiver().getId());
			}
			if(message.getSender()!= null && StringUtils.isNotEmpty(message.getSender().getId())){
				parameter.put("senderId",message.getSender().getId());
			}
		}
		return messageDao.findAll(parameter, pageBounds);
	}

	@Override
	public List<Message> findMessages(Map<String, Object> parameter, PageBounds pageBounds) {
		return messageDao.findAll(parameter, pageBounds);
	}

	@Override
	public Response sendMessageToAllUser(Message message) {
		if(message == null){
			return Response.failInstance().responseMsg("sendMessageToAllUser fail! message is null");
		}
		if(!message.isEffective()){
			return Response.failInstance().responseMsg("sendMessageToAllUser fail! message is unEffective");
		}
		Map<String,Object> parameter = Maps.newHashMap();
		message.setDefaultValue();
		message.setId(propertiesLoader.getProperty("db.uuid"));
		parameter.put("entity",message);
		return messageDao.batchInsert(parameter)>0?Response.successInstance():Response.failInstance();
	}

	@Override
	public Response sendMessageToUsers(Message message, List<String> receiverIds) {
		if(message == null){
			return Response.failInstance().responseMsg("sendMessageToUsers fail! message is null");
		}
		if(CollectionUtils.isEmpty(receiverIds)){
			return Response.failInstance().responseMsg("sendMessageToUsers fail! receiveIds is null");
		}
		Map<String,Object> parameter = Maps.newHashMap();
		message.setDefaultValue();
		message.setId(propertiesLoader.getProperty("db.uuid"));
		parameter.put("entity",message);
		parameter.put("receiverIds",receiverIds);
		return messageDao.batchInsert(parameter)>0?Response.successInstance():Response.failInstance();
	}

}
