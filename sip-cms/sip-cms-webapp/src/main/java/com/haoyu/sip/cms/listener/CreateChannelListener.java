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
import com.haoyu.sip.utils.Identities;

/**
 * @author lianghuahuang
 *
 */
@Component
public class CreateChannelListener implements ApplicationListener<CreateChannelEvent> {
	@Resource
	private IAuthResourceService authResourceService;
	
	@Resource
	private IAuthPermissionService authPermissionService;
	
/*	@Resource
	private Map<String,String> channelUriMapping;*/
	
	@Resource
	private String authResourceChannelId;
	
	@Override
	public void onApplicationEvent(CreateChannelEvent event) {
		Channel channel = (Channel)event.getSource();
		if(channel==null||StringUtils.isEmpty(channel.getId()))
			return;
		//先创建权限资源信息
		AuthResource ar = new AuthResource(channel.getId());
		ar.setCode(channel.getAlias());
		ar.setName(channel.getName());
		ar.setRelationId(channel.getRelationId());
		ar.setParent(new AuthResource(authResourceChannelId));
		ar.setDefaultValue();
		authResourceService.createResource(ar);
		
		//插入通用栏目内容查询权限
		AuthPermission ap = new AuthPermission();
		ap.setId(Identities.uuid2());
		ap.setActionURI("/cms_channels/edit_contents/"+channel.getId());
		ap.setAction("read");
		ap.setName("内容管理");
		ap.setDefaultValue();
		ap.setResource(ar);
		authPermissionService.createPermission(ap);
		
		//TODO 暂时不实现更细粒度的控制，后期可以补充
/*		
		//根据channelType创建permision信息
		if(channelUriMapping!=null&&channelUriMapping.containsKey(channel.getDisplayType())){
			String uri = channelUriMapping.get(channel.getDisplayType());
			
			//插入通用栏目内容查询权限
			AuthPermission ap = new AuthPermission();
			ap.setId(Identities.uuid2());
			ap.setActionURI("/cms_channels/edit_contents/"+channel.getId());
			ap.setAction("read");
			ap.setName("栏目内容查询");
			ap.setDefaultValue();
			ap.setResource(ar);
			authPermissionService.createPermission(ap);
			
			//创建查询、更新、创建、删除权限
			ap.setId(Identities.uuid2());
			ap.setName("查询");
			ap.setActionURI(String.format(uri, channel.getId()));
			authPermissionService.createPermission(ap);
			
			ap.setId(Identities.uuid2());
			ap.setName("更新");
			ap.setActionURI(uri+"/"+channel.getId());
			authPermissionService.createPermission(ap);
		}*/
	}

}
