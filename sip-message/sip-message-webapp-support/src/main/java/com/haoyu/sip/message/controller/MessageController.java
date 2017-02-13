package com.haoyu.sip.message.controller;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.web.AbstractBaseController;
import com.haoyu.sip.message.entity.Message;
import com.haoyu.sip.message.service.IMessageService;

@Controller
@RequestMapping("**/message")
public class MessageController extends AbstractBaseController{
	
	@Resource
	private IMessageService messageService;
	
	@RequestMapping(value="createBatch", method=RequestMethod.POST)
	@ResponseBody
	public Response createMessage(Message message){
		if (message != null && message.getReceiver() != null && StringUtils.isNotEmpty(message.getReceiver().getId())) {
			String[] array = message.getReceiver().getId().split(",");
			List<String> receiveIds = Arrays.asList(array);
			return messageService.sendMessageToUsers(message, receiveIds);
		}else{
			return Response.failInstance().responseMsg("receive is null");
		}
	}

}
