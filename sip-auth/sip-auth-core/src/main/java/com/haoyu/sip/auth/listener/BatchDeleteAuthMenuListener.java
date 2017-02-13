package com.haoyu.sip.auth.listener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.haoyu.sip.auth.dao.IAuthRoleMenuDao;
import com.haoyu.sip.auth.entity.AuthMenu;
import com.haoyu.sip.auth.event.BatchDeleteAuthMenuEvent;
@Component
public class BatchDeleteAuthMenuListener implements ApplicationListener<BatchDeleteAuthMenuEvent>{
	@Resource
	private IAuthRoleMenuDao authRoleMenuDao;
	@SuppressWarnings("unchecked")
	@Override
	public void onApplicationEvent(BatchDeleteAuthMenuEvent event) {
		List<AuthMenu> roots = (List<AuthMenu>) event.getSource();
		if(roots!= null && roots.size()>0){
			List<String> menuIds = new ArrayList<String>();
			for(AuthMenu m:roots){
				m.getTreeAllMenuId(menuIds);
			}
			Map<String,Object> param = new HashMap<String,Object>();
			param.put("menuIds", menuIds);
			authRoleMenuDao.deleteRoleMenu(param);
		}
	}

}
