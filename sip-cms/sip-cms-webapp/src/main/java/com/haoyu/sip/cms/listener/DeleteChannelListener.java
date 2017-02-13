/**
 * 
 */
package com.haoyu.sip.cms.listener;


import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.haoyu.sip.auth.entity.AuthPermission;
import com.haoyu.sip.auth.entity.AuthResource;
import com.haoyu.sip.auth.service.IAuthPermissionService;
import com.haoyu.sip.auth.service.IAuthResourceService;
import com.haoyu.sip.cms.channel.entity.Channel;
import com.haoyu.sip.cms.channel.event.CreateChannelEvent;
import com.haoyu.sip.cms.channel.event.DeleteChannelEvent;
import com.haoyu.sip.utils.Identities;

/**
 * @author lianghuahuang
 *
 */
@Component
public class DeleteChannelListener implements ApplicationListener<DeleteChannelEvent> {
	@Resource
	private IAuthResourceService authResourceService;
	
	@Resource
	private IAuthPermissionService authPermissionService;
	
/*	@Resource
	private Map<String,String> channelUriMapping;*/
	
	@Resource
	private String authResourceChannelId;
	
	@Override
	public void onApplicationEvent(DeleteChannelEvent event) {
		Channel channel = (Channel)event.getSource();
		//先 删除权限资源信息
		if(channel!=null&&StringUtils.isNotEmpty(channel.getId())){
			AuthResource ar = new AuthResource(channel.getId());
			ar.setUpdateValue();
			AuthPermission ap = new AuthPermission();
			ap.setResource(ar);
			ap.setUpdateValue();
			authPermissionService.deletePermission(ap);
			authResourceService.deleteResource(ar);
		}

	}

}
