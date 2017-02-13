package com.haoyu.sip.file.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.haoyu.base.utils.BaseServiceUtils;
import com.haoyu.sip.core.jdbc.MybatisDao;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.file.dao.IFileDownloadUserDao;
import com.haoyu.sip.file.entity.FileDownloadUser;
import com.haoyu.sip.file.service.IFileDownloadUserService;

@Service
public class FileDownloadUserServiceImpl implements IFileDownloadUserService{
	
	@Resource
	private IFileDownloadUserDao fileDownloadUserDao;
	
	@Override
	public Response create(FileDownloadUser obj) {
		return BaseServiceUtils.create(obj, (MybatisDao)fileDownloadUserDao);
	}

}
