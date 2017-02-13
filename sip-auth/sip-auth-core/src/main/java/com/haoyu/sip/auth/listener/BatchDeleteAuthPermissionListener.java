package com.haoyu.sip.auth.listener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.haoyu.sip.auth.dao.IAuthRolePermissionDao;
import com.haoyu.sip.auth.event.BatchDeleteAuthPermissionEvent;
@Component
public class BatchDeleteAuthPermissionListener implements ApplicationListener<BatchDeleteAuthPermissionEvent> {

	@Resource
	private IAuthRolePermissionDao authRolePermission;

	@SuppressWarnings("unchecked")
	@Override
	public void onApplicationEvent(BatchDeleteAuthPermissionEvent event) {
		List<String> ids = (List<String>) event.getSource();
		if (ids != null && ids.size() > 0) {
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("permissionIds", ids);
			authRolePermission.deleteRolePermission(param);
		}

	}

}
