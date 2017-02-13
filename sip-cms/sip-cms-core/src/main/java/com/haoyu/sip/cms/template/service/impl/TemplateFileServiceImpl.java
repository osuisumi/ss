/**
 * 
 */
package com.haoyu.sip.cms.template.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import org.apache.commons.io.FileUtils;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.haoyu.sip.cms.channel.entity.Channel;
import com.haoyu.sip.cms.channel.service.IChannelService;
import com.haoyu.sip.cms.siteinfo.service.ISiteInfoService;
import com.haoyu.sip.cms.template.entity.TemplateFile;
import com.haoyu.sip.cms.template.service.ITemplateFileService;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.utils.PropertiesLoader;
import com.haoyu.sip.core.utils.ThreadContext;

/**
 * @author lianghuahuang
 *
 */
@Service
public class TemplateFileServiceImpl implements ITemplateFileService {

	private static String templateLoaderPath = PropertiesLoader
			.get("cms.template.path");

	@Resource
	private IChannelService channelService;
	
	@Resource
	private ISiteInfoService siteInfoService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.haoyu.sip.cms.template.service.ITemplateFileService#createTemplateFile
	 * (java.lang.String, com.haoyu.sip.cms.template.entity.TemplateFile)
	 */
	@Override
	public Response createTemplateFile(String type, String id,
			TemplateFile templateFile) {
		String mappingFolder = getMappingFolder();
		String relativePath = "/cms/" + mappingFolder + "/"
				+ getFileNameById(id);
		if (type.equals("admin") || type.equals("frontEnd")) {
			String path = getTemplateFilePath(type, relativePath);
			try {
				File file = new File(path);
				FileUtils.writeStringToFile(file,
						templateFile.getFileContent(), "UTF-8");
				templateFile.setName(file.getName());
				templateFile.setPath(mappingFolder + "/" + file.getName());
				return Response.successInstance().responseData(templateFile);
			} catch (IOException e) {
				e.printStackTrace();
				return Response.failInstance().responseMsg(e.getMessage());
			}
		}
		return Response.failInstance().responseMsg("type is invalid!");
	}

	/**
	 * @param type
	 * @param relativePath
	 * @return
	 */
	private String getTemplateFilePath(String type, String relativePath) {
		StringBuffer path = new StringBuffer();
		path.append(templateLoaderPath);
		if (type.equals("admin")) {
			path.append("/").append(type);
		}
		path.append(relativePath);
		return path.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.haoyu.sip.cms.template.service.ITemplateFileService#updateTemplateFile
	 * (java.lang.String, com.haoyu.sip.cms.template.entity.TemplateFile)
	 */
	@Override
	public Response updateTemplateFile(String type,String id, TemplateFile templateFile) {
		String mappingFolder  = getMappingFolder();
		String relativePath = "/cms/" + mappingFolder + "/"
				+ getFileNameById(id);
		if (type.equals("admin") || type.equals("frontEnd")) {
			String path = getTemplateFilePath(type, relativePath);
			try {
				File file = new File(path);
				if(file.exists()){
					FileUtils.writeStringToFile(file,
							templateFile.getFileContent(), "UTF-8");
					templateFile.setName(file.getName());
					templateFile.setPath(mappingFolder + "/" + file.getName());
					return Response.successInstance().responseData(templateFile);
				}
				return Response.failInstance().responseMsg("file is not exists!");
			} catch (IOException e) {
				e.printStackTrace();
				return Response.failInstance().responseMsg(e.getMessage());
			}
		}
		return Response.failInstance().responseMsg("type is invalid!");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.haoyu.sip.cms.template.service.ITemplateFileService#findTemplateFile
	 * (java.lang.String, java.lang.String)
	 */
	@Override
	public TemplateFile findTemplateFile(String type, String id) {
		String mappingFolder = getMappingFolder();
		String relativePath = "/cms/" + mappingFolder + "/"
				+ getFileNameById(id);
		if (type.equals("admin") || type.equals("frontEnd")) {
			String path = getTemplateFilePath(type, relativePath);
			try {
				File file = new File(path);
				TemplateFile template = new TemplateFile(file.getName(), mappingFolder
						+ "/" + file.getName());
				if (file.exists()) {
					template.setFileContent(FileUtils.readFileToString(file));
					return template;
				}
				return template;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.haoyu.sip.cms.template.service.ITemplateFileService#deleteTemplateFile
	 * (java.lang.String)
	 */
	@Override
	public Response deleteTemplateFile(String id) {
		String mappingFolder = getMappingFolder();
		String relativePath = "/cms/" + mappingFolder + "/"
				+ getFileNameById(id);
		String path = getTemplateFilePath("", relativePath);
		File file;
		file = new File(path);
		if (file.exists()) {
			file.delete();
		}
		path = getTemplateFilePath("", relativePath);
		file = new File(path);
		if (file.exists()) {
			file.delete();
		}
		return Response.successInstance();
	}

	@Override
	public Response deleteTemplateFile(String type, String id) {
		String mappingFolder= getMappingFolder();
		String relativePath = "/cms/" + mappingFolder + "/"
				+ getFileNameById(id);
		if (type.equals("admin") || type.equals("frontEnd")) {
			String path = getTemplateFilePath(type, relativePath);
			File file = new File(path);
			if (file.exists()) {
				file.delete();
			}
			return Response.successInstance();
		}
		return Response.failInstance().responseMsg("type is invalid!");
	}

	public String getFileNameById(String id) {
		Channel channel = channelService.findChannelById(id);
		StringBuffer fileName = new StringBuffer();
		List<Channel> parentChannels = getAllParents(channel);
		if (parentChannels != null && !parentChannels.isEmpty()) {
			for (int i = 0; i < parentChannels.size(); i++) {
				Channel pc = parentChannels.get(i);
				fileName.append(pc.getAlias()).append("_");
			}
		}
		return fileName.append(channel.getAlias()).append(".ftl").toString();
	}

	private List<Channel> getAllParents(Channel channel) {		
		if (channel != null && channel.getParent() != null) {
			List<Channel> channels = Lists.newArrayList();
			Channel parentChannel = channel.getParent();
			Map<String, Channel> channelMap = channelService
					.findChannelsForMap(Maps.newHashMap());
			if (channelMap.containsKey(parentChannel.getId())) {
				parentChannel = channelMap.get(parentChannel.getId());
				channels.add(parentChannel);
			}
			while (parentChannel.getParent() != null) {
				parentChannel = channelMap.get(parentChannel.getId());
				channels.add(parentChannel);
			}
			return channels;
		}
		return null;
	}
	
	private String getMappingFolder(){
		return Objects.toString(siteInfoService.findMappingFolderByDomain(ThreadContext.getDomain()),"");
	}

}
