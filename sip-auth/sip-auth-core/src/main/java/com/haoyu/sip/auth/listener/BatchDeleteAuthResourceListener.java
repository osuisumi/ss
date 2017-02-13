package com.haoyu.sip.auth.listener;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.haoyu.sip.auth.entity.AuthResource;
import com.haoyu.sip.auth.event.BatchDeleteAuthResourceEvent;
import com.haoyu.sip.auth.service.IAuthPermissionService;

@Component
public class BatchDeleteAuthResourceListener implements ApplicationListener<BatchDeleteAuthResourceEvent> {

	@Resource
	private IAuthPermissionService authPermissionService;

	@SuppressWarnings("unchecked")
	@Override
	public void onApplicationEvent(BatchDeleteAuthResourceEvent event) {
		List<AuthResource> roots = (List<AuthResource>) event.getSource();
		//删除resource下所有的permission
		List<String> prepareDeletePermissionIds = new ArrayList<String>();
		for(AuthResource r:roots){
			r.getTreeAllPermissionId(prepareDeletePermissionIds);
		}
		String ids = "";
		for(String permissionId:prepareDeletePermissionIds){
			if(ids.equals("")){
				ids = permissionId;
			}else{
				ids = ids +","+permissionId;
			}
		}
		authPermissionService.batchDeleteByIds(ids);

	}

}
