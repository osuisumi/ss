package com.haoyu.sip.message.mobile.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.github.miemiedev.mybatis.paginator.domain.Order;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.github.miemiedev.mybatis.paginator.domain.Paginator;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.utils.BeanUtils;
import com.haoyu.sip.core.utils.ThreadContext;
import com.haoyu.sip.message.entity.Message;
import com.haoyu.sip.message.mobile.entity.MMessage;
import com.haoyu.sip.message.mobile.service.IMMessageService;
import com.haoyu.sip.message.service.IMessageService;
import com.haoyu.sip.user.mobile.entity.MUser;
import com.haoyu.sip.utils.Collections3;

@Repository
public class MMessageServiceImpl implements IMMessageService{

	@Resource
	private IMessageService messageService;
	
	@Override
	public Response listMessage(Message message, PageBounds pageBounds) {
		List<MMessage> mMessages = Lists.newArrayList();
		Map<String, Object> param = Maps.newHashMap();
		param.put("receiverId",ThreadContext.getUser().getId());
		
		if (Collections3.isEmpty(pageBounds.getOrders())) {			
			pageBounds.setOrders(Order.formString("CREATE_TIME.DESC"));
		}
		List<Message> messages = messageService.findMessages(param, pageBounds);
		
		PageList pageList = (PageList)messages;
		Paginator paginator = pageList.getPaginator();
		
		if (Collections3.isNotEmpty(messages)) {
			for (Message m : messages) {
				MMessage mMessage = new MMessage();
				BeanUtils.copyProperties(m, mMessage);
				if (m.getReceiver() != null) {
					MUser mUser = new MUser();
					BeanUtils.copyProperties(m.getReceiver(),mUser);
					mMessage.setReceiver(mUser);
				}
				if (m.getSender() != null) {
					MUser mUser = new MUser();
					BeanUtils.copyProperties(m.getSender(),mUser);
					mMessage.setSender(mUser);
				}
				mMessages.add(mMessage);
			}
		}
		
		Map<String, Object> resultMap = Maps.newHashMap();
		resultMap.put("mMessages",mMessages);
		resultMap.put("paginator",paginator);
		return Response.successInstance().responseData(resultMap);
	}

	@Override
	public Response listUserMessage(Message message, PageBounds pageBounds) {
		List<MMessage> mMessages = Lists.newArrayList();
		Map<String, Object> resultMap = Maps.newHashMap();
		Map<String, Object> param = Maps.newHashMap();
		if (message.getSender() == null || StringUtils.isEmpty(message.getSender().getId()) || message.getReceiver() == null ||StringUtils.isEmpty(message.getReceiver().getId())) {
			return Response.successInstance().responseData(resultMap);
		}
		param.put("senderId",message.getSender().getId());
		param.put("receiverId",message.getReceiver().getId());
		param.put("queryType","userMessageGroup");
		
		if (Collections3.isEmpty(pageBounds.getOrders())){			
			pageBounds.setOrders(Order.formString("CREATE_TIME.DESC"));
		}
		List<Message> messages = messageService.findMessages(param, pageBounds);
		
		PageList pageList = (PageList)messages;
		Paginator paginator = pageList.getPaginator();
		
		if (Collections3.isNotEmpty(messages)) {
			for (Message m : messages) {
				MMessage mMessage = new MMessage();
				BeanUtils.copyProperties(m, mMessage);
				if (m.getReceiver() != null) {
					MUser mUser = new MUser();
					BeanUtils.copyProperties(m.getReceiver(),mUser);
					mMessage.setReceiver(mUser);
				}
				if (m.getSender() != null) {
					MUser mUser = new MUser();
					BeanUtils.copyProperties(m.getSender(),mUser);
					mMessage.setSender(mUser);
				}
				mMessages.add(mMessage);
			}
		}
		
		resultMap.put("mUserMessages",mMessages);
		resultMap.put("paginator",paginator);
		return Response.successInstance().responseData(resultMap);
	}

	@Override
	public Response getMessage(Message message) {
		if (StringUtils.isNotEmpty(message.getId())) {
			MMessage mMessage = new MMessage();
			message = messageService.findMessageById(message.getId());
			BeanUtils.copyProperties(message,mMessage);
			if (message.getReceiver() != null) {
				MUser mUser = new MUser();
				BeanUtils.copyProperties(message.getReceiver(),mUser);
				mMessage.setReceiver(mUser);
			}
			if (message.getSender() != null) {
				MUser mUser = new MUser();
				BeanUtils.copyProperties(message.getSender(),mUser);
				mMessage.setSender(mUser);
			}
			return Response.successInstance().responseData(mMessage);
		}
		return Response.failInstance();
	}

	@Override
	public Response createMessage(Message message) {
		Response response = messageService.createMessage(message);
		if (response.isSuccess()) {
			MMessage mMessage = new MMessage();
			if (response.getResponseData() != null) {		
				message = (Message) response.getResponseData();
				BeanUtils.copyProperties(message,mMessage);
				if (message.getReceiver() != null) {
					MUser mUser = new MUser();
					BeanUtils.copyProperties(message.getReceiver(),mUser);
					mMessage.setReceiver(mUser);
				}
				if (message.getSender() != null) {
					MUser mUser = new MUser();
					BeanUtils.copyProperties(message.getSender(),mUser);
					mMessage.setSender(mUser);
				}
			}
			return Response.successInstance().responseData(mMessage);
		}
		return response;
	}

}