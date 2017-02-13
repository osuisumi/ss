package com.haoyu.sip.file.web;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.common.collect.Lists;
import com.haoyu.sip.core.entity.Relation;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.web.AbstractBaseController;
import com.haoyu.sip.file.entity.FileInfo;
import com.haoyu.sip.file.entity.FileRelation;
import com.haoyu.sip.file.entity.FileResource;
import com.haoyu.sip.file.event.DeleteFileRelationEvent;
import com.haoyu.sip.file.event.UploadFileEvent;
import com.haoyu.sip.file.service.IFileInfoService;
import com.haoyu.sip.file.service.IFileRelationService;
import com.haoyu.sip.file.service.IFileService;
import com.haoyu.sip.login.Loginer;

@Controller
@RequestMapping("**/file")
public class FileController extends AbstractBaseController{
	
	@Resource
	private IFileService fileService;
	@Resource
	private IFileInfoService fileInfoService;
	@Resource
	private IFileRelationService fileRelationService;
	@Resource
	private ApplicationContext applicationContext;
	
	/**
	 * 上传文件到远程服务器, 附件有版本控制
	 * @param request
	 * @param file
	 * @return
	 */
	@RequestMapping("uploadRemote")  
	@ResponseBody  
	public Response uploadRemote(MultipartFile file) {
		String relationId = request.getParameter("relationId");
		String type = request.getParameter("type");
		String parentId = request.getParameter("parentId");
		FileResource fileResource = new FileResource();
		FileRelation fileRelation = new FileRelation();
		fileRelation.setType(type);
		fileRelation.setRelation(new Relation(relationId));
		fileResource.setFileRelations(Lists.newArrayList(fileRelation));
		fileResource.setParentId(parentId);
		fileResource.getCreator().setRealName(Loginer.getLoginer(request).getRealName());
		try {
			Response response = fileService.uploadFile(file.getBytes(), file.getSize(), fileResource, file.getOriginalFilename());
			if (response.isSuccess()) {
				applicationContext.publishEvent(new UploadFileEvent(fileRelation));
			}
			return response;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return Response.failInstance();
	}  
	
	/**
	 * 上传文件到远程服务器, 没有版本控制
	 * @param request
	 * @param file
	 * @return
	 */
	@RequestMapping("uploadFileInfoRemote")  
	@ResponseBody  
	public Response uploadFileInfoRemote(MultipartFile file) {
		String relationId = request.getParameter("relationId");
		String type = request.getParameter("type");
		FileInfo fileInfo = new FileInfo();
		FileRelation fileRelation = new FileRelation();
		fileRelation.setType(type);
		fileRelation.setRelation(new Relation(relationId));
		fileInfo.setFileRelations(Lists.newArrayList(fileRelation));
		try {
			Response response = fileService.uploadFile(file.getBytes(), file.getSize(), fileInfo, file.getOriginalFilename());
			if (response.isSuccess()) {
				applicationContext.publishEvent(new UploadFileEvent(fileRelation));
			}
			return response;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return Response.failInstance();
	} 

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
			return fileService.uploadTemp(file.getBytes(),file.getOriginalFilename());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return Response.failInstance();
	}
	
	/**
	 * 更新附件, 如文件名
	 * @param request
	 * @param file
	 * @return
	 */
	@RequestMapping("updateFileInfo")
	@ResponseBody  
	public Response updateFileInfo(FileInfo fileInfo) {
		return fileInfoService.update(fileInfo);
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
	
	/**
	 * 删除附件
	 * @param request
	 * @param file
	 * @return
	 */
	@RequestMapping("deleteFileInfo")
	@ResponseBody  
	public Response deleteFileInfo(FileInfo fileInfo) {
		return fileService.deleteFileFromServer(fileInfo);
	}
	
	@RequestMapping(method=RequestMethod.GET)
	@ResponseBody
	public List<FileInfo> listFileInfo(String relationId, String relationType){
		Relation relation = new Relation(relationId);
		relation.setType(relationType);
		return fileService.listFileInfoByRelation(relation);
	}
	
	@RequestMapping("downloadSample")
	public void downloadSample(HttpServletResponse response) {
        try {
        	response.setContentType("multipart/form-data");  
        	response.setHeader("Content-Disposition", "attachment;fileName=" + new String("模板.doc".getBytes("UTF-8"), "iso8859-1"));  
            OutputStream os = response.getOutputStream();  
            byte[] b = fileService.downloadFile(null);
			os.write(b);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("previewFile")
	public String previewFile(String fileId, Model model){
		setParameterToModel(request, model);
		model.addAttribute("fileInfo", fileInfoService.get(fileId));
		return "common/preview_file";
	}
	
	
}
