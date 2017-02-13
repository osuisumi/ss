package com.haoyu.sip.file.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.commons.lang3.StringUtils;

import com.haoyu.sip.core.mapper.JsonMapper;

public class FileUtils{
	
	/**
	 * 获取一个可用的fastDFS的http地址
	 * @return
	 */
	public static String getHttpHost(){
		boolean isFileFastdfsUse = Boolean.valueOf(CommonConfig.getProperty("file.fdfs.is.user"));
		String hosts = null;
		if (isFileFastdfsUse) {
			hosts = CommonConfig.getProperty("fdfs.http.host");
		}else {
			hosts = CommonConfig.getProperty("file.remote.http.host");
		}
		if(StringUtils.isNotEmpty(hosts)){
			@SuppressWarnings("unchecked")
			List<String> list = new JsonMapper().fromJson(hosts, ArrayList.class);
			Random random = new Random();
			int index = random.nextInt(list.size());
			return list.get(index);
		}
		return null;
	}
	
	public static String getFileUrl(String url){
		return FileUtils.getHttpHost() + url;
	}
	
	public static String getFileUrl(String ctx, String url){
		return ctx + url;
	}
	
	public static String getPreviewPath(String url){
		url = (getHttpHost()+ url).replace("://", "%3A%2F%2F").replaceAll("/", "%2F");
		return "https://view.officeapps.live.com/op/embed.aspx?src="+url+"&wdStartOn=1";
	}
	
	public static String getFileSuffix(String path){
		return path.substring(path.lastIndexOf(".")+1);
	}
	
}
