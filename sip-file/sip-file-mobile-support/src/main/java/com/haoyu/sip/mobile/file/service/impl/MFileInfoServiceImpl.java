package com.haoyu.sip.mobile.file.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.github.miemiedev.mybatis.paginator.domain.Paginator;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.utils.BeanUtils;
import com.haoyu.sip.file.entity.FileInfo;
import com.haoyu.sip.file.service.IFileInfoService;
import com.haoyu.sip.mobile.file.entity.MFileInfo;
import com.haoyu.sip.mobile.file.service.IMFileInfoService;
import com.haoyu.sip.utils.Collections3;

@Repository
public class MFileInfoServiceImpl implements IMFileInfoService{

	@Resource
	private IFileInfoService fileInfoService;
	
	@Override
	public Response listFileInfo(FileInfo fileInfo, PageBounds pageBounds) {
		List<MFileInfo> mFileInfos = Lists.newArrayList();
		Map<String, Object> param = Maps.newHashMap();
		if (Collections3.isNotEmpty(fileInfo.getFileRelations()) && fileInfo.getFileRelations().get(0) != null && fileInfo.getFileRelations().get(0).getRelation() != null) {
			if (StringUtils.isNotEmpty(fileInfo.getFileRelations().get(0).getRelation().getId())) {				
				param.put("relationId",fileInfo.getFileRelations().get(0).getRelation().getId());
			}
			if (StringUtils.isNotEmpty(fileInfo.getFileRelations().get(0).getRelation().getType())) {
				param.put("type",fileInfo.getFileRelations().get(0).getRelation().getType());
			}
		}
		
		List<FileInfo> fileInfos = fileInfoService.listFileInfo(param, pageBounds);

		PageList pageList = (PageList)fileInfos;
		Paginator paginator = pageList.getPaginator();

		if (Collections3.isNotEmpty(fileInfos)) {
			mFileInfos = BeanUtils.getCopyList(fileInfos, MFileInfo.class);
		}
		
		Map<String, Object> resultMap = Maps.newHashMap();
		resultMap.put("mFileInfos",mFileInfos);
		resultMap.put("paginator",paginator);
		return Response.successInstance().responseData(resultMap);
	}

	
}
