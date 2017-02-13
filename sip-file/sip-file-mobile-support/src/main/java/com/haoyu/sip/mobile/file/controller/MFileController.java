package com.haoyu.sip.mobile.file.controller;

import java.io.IOException;
import java.io.OutputStream;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.utils.BeanUtils;
import com.haoyu.sip.core.web.AbstractBaseMobileController;
import com.haoyu.sip.file.entity.FileInfo;
import com.haoyu.sip.file.entity.FileRelation;
import com.haoyu.sip.file.event.DeleteFileRelationEvent;
import com.haoyu.sip.file.service.IFileInfoService;
import com.haoyu.sip.file.service.IFileRelationService;
import com.haoyu.sip.file.service.IFileService;
import com.haoyu.sip.mobile.file.entity.MFileInfo;
import com.haoyu.sip.mobile.file.service.IMFileInfoService;

@Controller
@RequestMapping("**/m/file")
public class MFileController extends AbstractBaseMobileController{
	
	@Resource
	private IFileService fileService;
	@Resource
	private IFileInfoService fileInfoService;
	@Resource
	private IFileRelationService fileRelationService;
	@Resource
	private ApplicationContext applicationContext;
	@Resource
	private IMFileInfoService fileInfoMobileService;
	
	/**
	 * 上传文件到临时目录
	 * @param request
	 * @param file
	 * @return
	 */
	@RequestMapping("uploadTemp")  
	@ResponseBody  
	public Response uploadTemp(MultipartFile file) {
		try {
			Response response = fileService.uploadTemp(file.getBytes(),file.getOriginalFilename());
			if (response.isSuccess()) {
				FileInfo fileInfo = (FileInfo) response.getResponseData();
				MFileInfo mFileInfo = new MFileInfo();
				BeanUtils.copyProperties(fileInfo, mFileInfo);
				mFileInfo.setUrl(fileInfo.getUrl());
				response.responseData(mFileInfo);
			}
			return response;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return Response.failInstance();
	}
	
	/**
	 * 下载附件
	 * @param request
	 * @param file
	 * @return
	 */
	@RequestMapping("downloadFile")
	public void downloadFile(HttpServletResponse response, FileInfo fileInfo) {
        try {
        	response.setContentType("multipart/form-data");  
        	response.setHeader("Content-Disposition", "attachment;fileName=" + new String(fileInfo.getFileName().getBytes("UTF-8"), "iso8859-1"));  
            OutputStream os = response.getOutputStream();  
            byte[] b = fileService.downloadFile(fileInfo);
			os.write(b);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 删除附件关系
	 * @param request
	 * @param file
	 * @return
	 */
	@RequestMapping("deleteFileRelation")
	@ResponseBody  
	public Response deleteFileRelation(FileRelation fileRelation) {
		Response response = fileRelationService.deleteFileRelation(fileRelation.getFileId(), fileRelation.getRelation().getId());
		if (response.isSuccess()) {
			applicationContext.publishEvent(new DeleteFileRelationEvent(fileRelation));
		}
		return response;
	}

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public Response list(FileInfo fileInfo){
		return fileInfoMobileService.listFileInfo(fileInfo,getPageBounds(10, true));
	}
}
