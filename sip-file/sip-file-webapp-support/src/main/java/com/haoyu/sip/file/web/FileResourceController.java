package com.haoyu.sip.file.web;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.web.AbstractBaseController;
import com.haoyu.sip.core.web.SearchParam;
import com.haoyu.sip.file.entity.FileResource;
import com.haoyu.sip.file.service.IFileResourceService;

@Controller
@RequestMapping("file/fileResource")
public class FileResourceController extends AbstractBaseController{

	@Resource
	private IFileResourceService fileResourceService;
	
	@RequestMapping("saveFolder")
	@ResponseBody
	public Response saveFolder(FileResource fileResource){
		return fileResourceService.createFileResource(fileResource);
	}
	
	/**
	 * 更新附件, 如文件名
	 * @param request
	 * @param file
	 * @return
	 */
	@RequestMapping("updateFileResource")
	@ResponseBody  
	public Response updateFileInfo(FileResource fileResource) {
		return fileResourceService.update(fileResource);
	}
	
	@RequestMapping("listFolderData")
	@ResponseBody  
	public Response listFolderData(SearchParam searchParam) {
		searchParam.getParamMap().put("isFolder", "Y");
		List<FileResource> fileResources = fileResourceService.list(searchParam, null);
		Response response = Response.successInstance();
		response.setResponseData(fileResources);
		return response;
	}
	
	/**
	 * 移动文件或文件夹的位置
	 * @param request
	 * @param file
	 * @return
	 */
	@RequestMapping("updateFileResourceParent")
	@ResponseBody  
	public Response updateFileResourceParent(FileResource fileResource) {
		return fileResourceService.updateFileResourceParent(fileResource);
	}
	
}
