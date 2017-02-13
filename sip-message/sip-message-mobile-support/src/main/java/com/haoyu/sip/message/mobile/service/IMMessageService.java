package com.haoyu.sip.message.mobile.service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.message.entity.Message;

public interface IMMessageService {

	Response listMessage(Message message, PageBounds pageBounds);

	Response listUserMessage(Message message, PageBounds pageBounds);

	Response getMessage(Message message);

	Response createMessage(Message message);

}
