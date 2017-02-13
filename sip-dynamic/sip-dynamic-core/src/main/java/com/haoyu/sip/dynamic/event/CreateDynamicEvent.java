package com.haoyu.sip.dynamic.event;

import org.springframework.context.ApplicationEvent;

/**
 * 创建“动态”事件 
 * @author hqy
 *
 */
public  class CreateDynamicEvent extends ApplicationEvent{
	private static final long serialVersionUID = 4908218257833071407L;

	public CreateDynamicEvent(Object source) {
		super(source);
	}


}
