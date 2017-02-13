package com.haoyu.sip.message.mobile.controller;

import java.util.Arrays;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.web.AbstractBaseMobileController;
import com.haoyu.sip.message.entity.Message;
import com.haoyu.sip.message.mobile.service.IMMessageService;
import com.haoyu.sip.message.service.IMessageService;

@RestController
@RequestMapping("**/m/message")
public class MMessageController extends AbstractBaseMobileController{

	@Resource
	private IMMessageService mMessageService;
	@Resource
	private IMessageService messageService;
	
	@RequestMapping(method = RequestMethod.GET)
	public Response list(Message message){
		return mMessageService.listMessage(message,getPageBounds(10, true));
	}
	
	@RequestMapping(value = "userMessage",method = RequestMethod.GET)
	public Response listUserMessage(Message message){
		return mMessageService.listUserMessage(message,getPageBounds(10, true));
	}
	
	@RequestMapping(value="{id}",method=RequestMethod.GET)
	public Response get(Message message){
		return mMessageService.getMessage(message);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public Response create(Message message){
		return mMessageService.createMessage(message);
	}
	
	@RequestMapping(value="sendMessages",method=RequestMethod.POST)
	public Response sendMessages(Message message,String receiverIds){
		return messageService.sendMessageToUsers(message, Arrays.asList(receiverIds.split(",")));
	}
}
