package com.haoyu.sip.auth.listener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.haoyu.sip.auth.dao.IAuthRoleMenuDao;
import com.haoyu.sip.auth.dao.IAuthRolePermissionDao;
import com.haoyu.sip.auth.dao.IAuthUserRoleDao;
import com.haoyu.sip.auth.event.BatchDeleteAuthRoleEvent;
@Component
public class BatchDeleteAuthRoleListener implements ApplicationListener<BatchDeleteAuthRoleEvent>{
	@Resource
	private IAuthRolePermissionDao authRolePermissionDao;
	
	@Resource
	private IAuthRoleMenuDao authRoleMenuDao;
	
	@Resource
	private IAuthUserRoleDao authUserRoleDao;
	
	@SuppressWarnings("unchecked")
	@Override
	//删除authRole时，删除关联的rolePermission,roleMenu,roleUser
	public void onApplicationEvent(BatchDeleteAuthRoleEvent event) {
		List<String> roleIds = (List<String>) event.getSource();
		if(roleIds != null && roleIds.size()>0){
			Map<String,Object> param = new HashMap<String,Object>();
			param.put("roleIds", roleIds);
			authRolePermissionDao.deleteRolePermission(param);
			authRoleMenuDao.deleteRoleMenu(param);
			authUserRoleDao.deleteRoleUser(param);
		}
	}

}
