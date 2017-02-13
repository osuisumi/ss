package com.haoyu.sip.mobile.file.service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.file.entity.FileInfo;

public interface IMFileInfoService {

	Response listFileInfo(FileInfo fileInfo, PageBounds pageBounds);

}
