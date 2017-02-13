package com.haoyu.sip.file.service.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.ServletContext;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ServletContextAware;

import com.google.common.collect.Lists;
import com.haoyu.sip.core.entity.Relation;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.utils.PropertiesLoader;
import com.haoyu.sip.core.web.SearchParam;
import com.haoyu.sip.file.entity.FileDownloadUser;
import com.haoyu.sip.file.entity.FileInfo;
import com.haoyu.sip.file.entity.FileRelation;
import com.haoyu.sip.file.entity.FileResource;
import com.haoyu.sip.file.event.DownloadFileEvent;
import com.haoyu.sip.file.service.IFileRelationService;
import com.haoyu.sip.file.service.IFileService;
import com.haoyu.sip.file.service.IFileDownloadUserService;
import com.haoyu.sip.file.service.IFileInfoService;
import com.haoyu.sip.file.service.IFileResourceService;
import com.haoyu.sip.utils.Collections3;
import com.haoyu.sip.utils.Identities;

@Service("fileService")
public class FileServiceImpl implements IFileService, ServletContextAware{

	private String confFile;
	private boolean fileFastdfsUse;
	private String localFileDir;
	private String tempFileDir;
	private String fileforbidSuffix;

	@Resource
	private IFileResourceService fileResourceService;
	@Resource
	private IFileInfoService fileInfoService;
	@Resource
	private IFileRelationService fileRelationService;
	@Resource
	private IFileDownloadUserService fileDownloadUserService;
	@Resource
	private PropertiesLoader propertiesLoader;
	@Resource
	private ApplicationContext applicationContext;
	
	private TrackerClient tracker;
	
	private ServletContext servletContext;

	public void setFileResourceService(IFileResourceService fileResourceService) {
		this.fileResourceService = fileResourceService;
	}

	public void setFileInfoService(IFileInfoService fileInfoService) {
		this.fileInfoService = fileInfoService;
	}

	public void setFileRelationService(IFileRelationService fileRelationService) {
		this.fileRelationService = fileRelationService;
	}

	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

	@PostConstruct
	public void init() {
		try {
			confFile = propertiesLoader.getProperty("fdfs.conf.file");
			fileFastdfsUse = Boolean.valueOf((String)propertiesLoader.getProperty("file.fdfs.is.user"));
			localFileDir = propertiesLoader.getProperty("file.remote.dir");
			tempFileDir = propertiesLoader.getProperty("file.temp.dir");
			fileforbidSuffix = propertiesLoader.getProperty("file.forbid.suffix");
					
			if (fileFastdfsUse) {
				ClientGlobal.init(confFile);
				tracker = new TrackerClient();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (MyException e) {
			e.printStackTrace();
		}
	}

	private StorageClient getStorageClient(TrackerServer trackerServer) throws FileNotFoundException, IOException, MyException {
		StorageServer storageServer = null;
		StorageClient client = new StorageClient(trackerServer, storageServer);
		return client;
	}

	@Override
	public Response uploadFile(byte[] bytes, long fileSize, FileResource fileResource, String originalFilename) {
		String subfix = StringUtils.substringAfterLast(originalFilename, ".");
		if (StringUtils.isNotEmpty(fileforbidSuffix) && fileforbidSuffix.contains(subfix)) {
			Response response = new Response();
			response.setResponseCode("01");
			response.setResponseMsg("文件非法");
			return response;
		}
		String results[] = this.uploadFileToServer(bytes, originalFilename);
		if (results != null) {
			String groupName = results[0];
			String url = results[1];
			FileInfo fileInfo = fileResource.getNewestFile();
			if (fileInfo == null) {
				fileInfo = new FileInfo();
			}
			if (StringUtils.isEmpty(fileInfo.getId())) {
				fileInfo.setId(Identities.uuid2());
			}
			if (StringUtils.isEmpty(fileResource.getId())) {
				fileResource.setId(Identities.uuid2());
				fileResource.setName(originalFilename);
				fileResource.setIsFolder("N");
				fileResource.setNewestFile(fileInfo);
				fileResourceService.createFileResource(fileResource);
			}
			fileInfo.setFileName(originalFilename);
			fileInfo.setGroupName(groupName);
			fileInfo.setUrl(url);
			fileInfo.setFileSize(fileSize);
			fileInfo.setFileResource(fileResource);
			Response response = fileInfoService.create(fileInfo);
			if (response.isSuccess()) {
				fileResource.setFileRelations(null);
				fileInfo.setFileResource(null);
				fileInfo.setUrl(url);
				fileInfo.setRemark(com.haoyu.sip.file.utils.FileUtils.getFileUrl(url));
				response.setResponseData(fileResource);
			}
			return response;
		}
		return Response.failInstance();
	}
	
	@Override
	public Response uploadFile(byte[] bytes, long fileSize, FileInfo fileInfo, String originalFilename) {
		String subfix = StringUtils.substringAfterLast(originalFilename, ".");
		if (StringUtils.isNotEmpty(fileforbidSuffix) && fileforbidSuffix.contains(subfix)) {
			Response response = new Response();
			response.setResponseCode("01");
			response.setResponseMsg("文件非法");
			return response;
		}
		String results[] = this.uploadFileToServer(bytes, originalFilename);
		if (results != null) {
			String groupName = results[0];
			String url = results[1];
			if (StringUtils.isEmpty(fileInfo.getId())) {
				fileInfo.setId(Identities.uuid2());
			}
			if (StringUtils.isEmpty(fileInfo.getFileName())) {
				fileInfo.setFileName(originalFilename);
			}
			fileInfo.setGroupName(groupName);
			fileInfo.setUrl(url);
			fileInfo.setFileSize(fileSize);
			fileInfo.setId(Identities.uuid2());
			Response response = fileInfoService.createFileInfo(fileInfo);
			if (response.isSuccess()) {
				response.setResponseData(fileInfo);
			}
			return response;
		}
		return Response.failInstance();
	}

	private String[] uploadFileToServer(byte[] bytes, String fileName) {
		try {
			if (fileFastdfsUse) {
				TrackerServer trackerServer = tracker.getConnection();
				StorageClient client = getStorageClient(trackerServer);
				NameValuePair[] metaList = null;
				String[] results = client.upload_file(bytes, StringUtils.substringAfterLast(fileName, "."), metaList);
				if (trackerServer != null) {
					trackerServer.close();
				}
				return results;
			} else {
				String url = new StringBuilder().append(DateFormatUtils.format(new Date(), "yyyy-MM")).append("/").append(Identities.uuid2()).append(".").append(StringUtils.substringAfterLast(fileName, ".")).toString();
				File disFile = new File(localFileDir + url);
				FileUtils.writeByteArrayToFile(disFile, bytes);
				String[] results = { "", url };
				return results;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private String[] uploadFileToServer(File file, String fileName) {
		try {
			if (fileFastdfsUse) {
				TrackerServer trackerServer = tracker.getConnection();
				StorageClient client = getStorageClient(trackerServer);
				NameValuePair[] metaList = null;
				String[] results = client.upload_file(FileUtils.readFileToByteArray(file), StringUtils.substringAfterLast(fileName, "."), metaList);
				if (trackerServer != null) {
					trackerServer.close();
				}
				return results;
			} else {
				String url = new StringBuilder().append(DateFormatUtils.format(new Date(), "yyyy-MM")).append("/").append(Identities.uuid2()).append(".").append(StringUtils.substringAfterLast(fileName, ".")).toString();
//				File disFile = new File(localFileDir + url);
//				FileUtils.writeByteArrayToFile(disFile, bytes);
//				FileUtils.copyFileToDirectory(file, new File(localFileDir + url));
				FileUtils.moveFile(file, new File(localFileDir + url));
				String[] results = { "", url };
				return results;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Response uploadTemp(byte[] bytes, String fileName) {
		String subfix = StringUtils.substringAfterLast(fileName, ".");
		if (StringUtils.isNotEmpty(fileforbidSuffix) && fileforbidSuffix.contains(subfix)) {
			Response response = new Response();
			response.setResponseCode("01");
			response.setResponseMsg("文件非法");
			return response;
		}
		try {
			String url = new StringBuilder().append(DateFormatUtils.format(new Date(), "yyyy-MM")).append("/").append(Identities.uuid2()).append(".").append(StringUtils.substringAfterLast(fileName, ".")).toString();
			File destFile = new File(tempFileDir + url);
			if (!destFile.getParentFile().exists()) {
				destFile.getParentFile().mkdirs();
			}
			FileUtils.writeByteArrayToFile(destFile, bytes);
			Response response = Response.successInstance();
			FileInfo fileInfo = new FileInfo();
			fileInfo.setId(Identities.uuid2());
			fileInfo.setFileName(fileName);
			fileInfo.setUrl(url);
			response.setResponseData(fileInfo);
			return response;
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return Response.failInstance();
	}

	@Override
	public Response createFileList(List<FileInfo> fileInfos, String relationId, String type) {
		if (Collections3.isNotEmpty(fileInfos)) {
			List<File> files = Lists.newArrayList();
			for (FileInfo fileInfo : fileInfos) {
				File file = new File(tempFileDir + fileInfo.getUrl());
				long length = file.length();
				String[] results = this.uploadFileToServer(file, fileInfo.getFileName());
				if (results != null) {
					String groupName = results[0];
					String url = results[1];
					if (StringUtils.isEmpty(fileInfo.getId())) {
						fileInfo.setId(Identities.uuid2());
					}
					FileRelation fileRelation = new FileRelation();
					fileRelation.setRelation(new Relation(relationId));
					fileRelation.setType(type);
					fileInfo.getFileRelations().add(fileRelation);
					fileInfo.setFileName(fileInfo.getFileName());
					fileInfo.setGroupName(groupName);
					fileInfo.setUrl(url);
					fileInfo.setFileSize(length);
					fileInfo.setId(Identities.uuid2());
					Response response = fileInfoService.createFileInfo(fileInfo);
					if (response.isSuccess()) {
						files.add(file);
					}
				}
			}
			for (File file : files) {
				FileUtils.deleteQuietly(file);
			}
			Response response = Response.successInstance();
			response.setResponseData(files);
			return response;
		}
		return Response.successInstance();
	}

	@Override
	public Response updateFileList(List<FileInfo> newList, List<FileInfo> oldList, String relationId, String type) {
		if (Collections3.isNotEmpty(newList) || Collections3.isNotEmpty(oldList)) {
			List<FileInfo> addList = Collections3.subtract(newList, oldList);
			List<FileInfo> deleteList = Collections3.subtract(oldList, newList);
			if (Collections3.isNotEmpty(deleteList)) {
				for (FileInfo fileInfo: deleteList) {
					fileRelationService.deleteFileRelation(fileInfo.getId(), relationId);
				}
			}
			createFileList(addList, relationId, type);
		}
		return Response.successInstance();
	}
	
	@Override
	public Response createFile(FileInfo fileInfo, String relationId, String type) {
		if (fileInfo != null) {
			List<FileInfo> fileInfos = Lists.newArrayList(fileInfo);
			return createFileList(fileInfos, relationId, type);
		}
		return Response.successInstance();
	}
	
	@Override
	public Response updateFile(FileInfo newFile, FileInfo oldFile, String relationId, String type) {
		if (newFile != null || oldFile != null) {
			if (newFile != null && oldFile != null && newFile.getId().equals(oldFile.getId())) {
				return Response.successInstance();
			}
			if (newFile != null) {
				this.createFile(newFile, relationId, type);
			}
			if (oldFile != null) {
				fileRelationService.deleteFileRelation(oldFile.getId(), relationId);
			}
		}
		return Response.successInstance();
	}

	@Override
	public Response deleteFileFromServer(FileInfo fileInfo) {
		Response response = fileInfoService.delete(fileInfo.getId());
		if (response.isSuccess()) {
			if (fileFastdfsUse) {
				try {
					TrackerServer trackerServer = tracker.getConnection();
					StorageClient client = getStorageClient(trackerServer);
					if (StringUtils.isEmpty(fileInfo.getGroupName()) && StringUtils.isEmpty(fileInfo.getUrl())) {
						fileInfo = fileInfoService.get(fileInfo.getId());
					}
					int result = client.delete_file(fileInfo.getGroupName(), fileInfo.getUrl());
					if (trackerServer != null) {
						trackerServer.close();
					}
					return result==0?Response.successInstance():Response.failInstance();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (MyException e) {
					e.printStackTrace();
				} 
				return Response.failInstance();
			} else {
				if (StringUtils.isEmpty(fileInfo.getUrl())) {
					fileInfo = fileInfoService.get(fileInfo.getId());
				}
				File file = new File(localFileDir+fileInfo.getUrl());
				FileUtils.deleteQuietly(file);
				return Response.successInstance();
			}
		}
		return response;
	}
	
	@Override
	public byte[] downloadFile(FileInfo fileInfo) {
		byte[] bytes = null;
		if (fileInfo != null && StringUtils.isNoneEmpty(fileInfo.getId())) {
			FileRelation fileRelation = null;
			if (Collections3.isNotEmpty(fileInfo.getFileRelations())) {
				fileRelation = fileInfo.getFileRelations().get(0);
			}
			fileInfo = fileInfoService.get(fileInfo.getId());
			if (fileFastdfsUse) {
				TrackerServer trackerServer = null;
				try {	
					trackerServer = tracker.getConnection();
					StorageClient client = getStorageClient(trackerServer);
					bytes = client.download_file(fileInfo.getGroupName(), fileInfo.getUrl());
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (MyException e) {
					e.printStackTrace();
				}finally{
					try {
						if( trackerServer !=null){
							trackerServer.close();
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}else {
				File file = new File(localFileDir + fileInfo.getUrl());
				if (file.isFile()) {
					try {
						bytes = FileUtils.readFileToByteArray(file);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			FileDownloadUser fileDownloadUser = new FileDownloadUser();
			fileDownloadUser.setFileId(fileInfo.getId());
			fileDownloadUserService.create(fileDownloadUser);
			if (fileRelation != null) {
				if (fileRelation.getRelation() != null && StringUtils.isNotEmpty(fileRelation.getRelation().getId())) {
					FileRelation fr = new FileRelation();
					String frid = FileRelation.getId(fileInfo.getId(), fileRelation.getRelation().getId());
					fr.setId(frid);
					fr.setDownloadNum(1);
					fileRelationService.update(fr);
				}
				applicationContext.publishEvent(new DownloadFileEvent(fileRelation));
			}
		}else{
			File file = new File(servletContext.getRealPath("/sample/模板.doc"));
			if (file.isFile()) {
				try {
					bytes = FileUtils.readFileToByteArray(file);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return bytes;
	}

	@Override
	public List<FileInfo> listFileInfoByRelationId(String relationId) {
		SearchParam searchParam = new SearchParam();
		searchParam.getParamMap().put("relationId", relationId);
		return fileInfoService.list(searchParam, null);
	}
	
	@Override
	public List<FileInfo> listFileInfoByRelation(Relation relation) {
		SearchParam searchParam = new SearchParam();
		searchParam.getParamMap().put("relationId", relation.getId());
		searchParam.getParamMap().put("type", relation.getType());
		return fileInfoService.list(searchParam, null);
	}

	@Override
	public int getFileInfoCount(Map<String, Object> param) {
		return fileInfoService.getCount(param);
	}

}
