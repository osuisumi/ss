/**
 * 
 */
package com.haoyu.sip.attitude.event;

import org.springframework.context.ApplicationEvent;

import com.haoyu.sip.attitude.entity.AttitudeUser;

/**
 * @author lianghuahuang
 *
 */
public class CreateAttitudeUserEvent extends ApplicationEvent {

	private static final long serialVersionUID = -8702109374763534745L;

	public CreateAttitudeUserEvent(AttitudeUser attitudeUser) {
			super(attitudeUser);
	}

}
