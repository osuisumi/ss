package com.haoyu.sip.file.service;

import java.util.List;
import java.util.Map;

import com.haoyu.sip.core.entity.Relation;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.file.entity.FileInfo;
import com.haoyu.sip.file.entity.FileResource;

public interface IFileService {
	
	/**
	 * 上传到远程服务器, 附件有版本控制
	 * @param bytes
	 * @param fileSize
	 * @param fileResource
	 * @param originalFilename
	 * @param userId 
	 * @return
	 */
	Response uploadFile(byte[] bytes, long size, FileResource fileResource, String originalFilename);

	/**
	 * 上传到远程服务器, 附件没有版本控制
	 * @param bytes
	 * @param fileSize
	 * @param fileResource
	 * @param originalFilename
	 * @return
	 */
	Response uploadFile(byte[] bytes, long fileSize, FileInfo fileInfo, String originalFilename);

	/**
	 * 上传到临时目录
	 * @param bytes
	 * @param fileName
	 * @return
	 */
	Response uploadTemp(byte[] bytes, String fileName);
	
	/**
	 * 下载附件
	 * @param fileInfo 
	 * fileInfo参数如果只有id, 会先查询groupName和url, 再下载
	 * fileInfo参数如果有groupName和url, 则直接下载
	 * @param realPath 
	 * @return
	 */
	byte[] downloadFile(FileInfo fileInfo);

	/**
	 * 删除没有版本控制的附件, 或者删除有版本控制的附件的其中一个版本
	 * @param fileInfo
	 * fileInfo参数必须有id
	 * fileInfo参数如果有groupName和url, 则直接删除文件, 如果没有, 会先根据id查询groupName和url, 再删除文件
	 * @param updatedby
	 * @return
	 */
	Response deleteFileFromServer(FileInfo fileInfo);
	
	/**
	 * 以下四个接口用于获取到临时文件后, 上传文件到服务器, 并更新数据
	 * @param addList
	 * @param deleteList
	 * @param relationId
	 * @param updatedby
	 * @param userId 
	 * @return
	 */
	Response updateFileList(List<FileInfo> newList, List<FileInfo> oldList, String relationId, String type);
	
	Response createFileList(List<FileInfo> fileInfos, String relationId, String type);

	Response createFile(FileInfo fileInfo, String relationId, String type);

	Response updateFile(FileInfo newFile, FileInfo oldFile, String relationId, String type);
	
	List<FileInfo> listFileInfoByRelationId(String relationId);
	
	List<FileInfo> listFileInfoByRelation(Relation relation);
	
	int getFileInfoCount(Map<String, Object> param);

}
