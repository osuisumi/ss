package com.haoyu.sip.cms.channel.controller;


import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.haoyu.sip.cms.channel.entity.Channel;
import com.haoyu.sip.cms.channel.entity.ChannelContent;
import com.haoyu.sip.cms.channel.service.IChannelContentService;
import com.haoyu.sip.cms.channel.service.IChannelService;
import com.haoyu.sip.cms.siteinfo.service.ISiteInfoService;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.utils.PropertiesLoader;
import com.haoyu.sip.core.utils.ThreadContext;
import com.haoyu.sip.core.web.AbstractBaseController;


@Controller
@RequestMapping("/cms_channels")
public class ChannelController extends AbstractBaseController{
	@Autowired
	private IChannelService channelService;
	
	@Resource
	private IChannelContentService channelContentService;
	
	@Resource
	private ISiteInfoService siteInfoService;
	
	private static String templateLoaderPath = PropertiesLoader
			.get("cms.template.path");
	
	private String superManagerRoleCode=PropertiesLoader.get("superManager.roleCode");
	
	protected String getLogicalViewNamePrefix(){
		return "/admin/cms/channel/";
	}
	
	protected String getMappingFolder(){
		return Objects.toString(siteInfoService.findMappingFolderByDomain(ThreadContext.getDomain()),"");
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String getCreateChannel(String pid,Model model){
		model.addAttribute("pid", pid);
		return getLogicalViewNamePrefix()+"create_channel";
	}
	
	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public String getEditChannel(@PathVariable String id, Model model){
		model.addAttribute("channel",channelService.findChannelById(id));
		return getLogicalViewNamePrefix()+"edit_channel";
		
	}
	
	@RequestMapping(value = "/{id}/view", method = RequestMethod.GET)
	public String viewChannel(@PathVariable String id, Model model){
		model.addAttribute("channel",channelService.findChannelById(id));
		return getLogicalViewNamePrefix()+"view_channel";		
	}
	
	/**
	 * 保存新创的栏目数据 
	 * @param channel
	 * @return
	 */
	@RequestMapping(method=RequestMethod.POST)
	@ResponseBody
	public Response createChannel(Channel channel){
		return channelService.createChannel(channel);
	}
	
	@RequestMapping(value = "/tree",method=RequestMethod.GET)
	public String getChannels(Channel channel, Model model){
		setParameterToModel(request,model);
		return getLogicalViewNamePrefix()+"tree_channel";
	}
	
	
	@RequestMapping(method=RequestMethod.GET)
	@ResponseBody
	public List<Channel> getChannels(Channel channel){
		return channelService.findChannels(channel, getPageBounds(10, false));
	}
	
	@RequestMapping(value="authorized",method=RequestMethod.GET)
	@ResponseBody
	public List<Channel> getAuthorizedChannels(){		
		Map<String,Object> parameter = Maps.newHashMap();
		//判断用户是否具备超级管理员角色		
		if(!SecurityUtils.getSubject().hasRole(superManagerRoleCode)){
				parameter.put("userId", ThreadContext.getUser().getId());
		}
		return channelService.findChannels(parameter);
	}
	
	@RequestMapping(value = "/{id}",method=RequestMethod.PUT)
	@ResponseBody
	public Response updateChannel(@PathVariable String id,Channel channel){
		channel.setId(id);
		return channelService.updateChannel(channel);
	}
	
	@RequestMapping(value = "/{id}",method=RequestMethod.GET)
	@ResponseBody
	public Channel getChannel(@PathVariable String id){
		return channelService.findChannelById(id);
	}
	@RequestMapping(value = "/{id}",method=RequestMethod.DELETE)
	@ResponseBody
	public Response deleteChannel(@PathVariable String id){
		return channelService.deleteChannel(new Channel(id));
	}
	@RequestMapping(value = "/edit_contents", method = RequestMethod.GET)
	public String getEditChannelContent(){
		return getLogicalViewNamePrefix()+"edit_channel_content";
	}
	
	@RequestMapping(value = "/edit_contents/{id}", method = RequestMethod.GET)
	public String getEditChannelContent(@PathVariable String id, Model model){
		super.setParameterToModel(request, model);
		Channel channel = channelService.findChannelById(id);
		model.addAttribute("channel",channel);
		String fileName = getFileName(id,channel.getAlias());
		String mappingFolder = getMappingFolder();
		String templateFilePath = getTemplateFilePath(id,channel.getAlias(),fileName,mappingFolder);
		File file = new File(templateFilePath);
		if(file.exists()){
			return "/admin/cms/"+Objects.toString(mappingFolder)+"/"+fileName.replace(".ftl", "");
		}
		String displayType = channel.getDisplayType();
		if(StringUtils.isEmpty(displayType)){
			return "/admin/cms/common/display/ArticleList";
		}
		return "/admin/cms/common/display/"+displayType;		
	}
	
	
	@RequestMapping(value = "/channel_contents/{id}",method=RequestMethod.PUT)
	@ResponseBody
	public Response updateChannel(@PathVariable String id,ChannelContent channelContent){
		channelContent.setChannelId(id);
		return channelContentService.updateChannelContent(channelContent,false);
	}
	
	/**
	 * @param type
	 * @param relativePath
	 * @return
	 */
	private String getTemplateFilePath(String id,String alias,String fileName,String mappingFolder) {
		StringBuffer path = new StringBuffer(templateLoaderPath);
		path.append("admin").append("/cms/");
		path.append(Objects.toString(mappingFolder)).append("/");
		path.append(fileName);
		return path.toString();
	}

	
	
	private String getFileName(String id,String alias) {
		StringBuffer fileName = new StringBuffer();
		List<Channel> parentChannels = getAllParents(id);
		if (parentChannels != null && !parentChannels.isEmpty()) {
			for (int i = 0; i < parentChannels.size(); i++) {
				Channel channel = parentChannels.get(i);
				fileName.append(channel.getAlias()).append("_");
			}
		}
		return fileName.append(alias).append(".ftl").toString();
	}

	private List<Channel> getAllParents(String id) {
		Channel channel = channelService.findChannelById(id);
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
	

}
