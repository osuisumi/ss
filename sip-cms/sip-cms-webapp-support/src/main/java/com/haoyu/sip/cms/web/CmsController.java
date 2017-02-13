/**
 * 
 */
package com.haoyu.sip.cms.web;

import java.io.File;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mobile.device.Device;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.haoyu.sip.cms.article.entity.Article;
import com.haoyu.sip.cms.article.service.IArticleSearchService;
import com.haoyu.sip.cms.channel.entity.Channel;
import com.haoyu.sip.cms.channel.service.IChannelService;
import com.haoyu.sip.cms.siteinfo.service.ISiteInfoService;
import com.haoyu.sip.cms.template.service.ITemplateFileService;
import com.haoyu.sip.core.entity.Relation;
import com.haoyu.sip.core.utils.PropertiesLoader;
import com.haoyu.sip.core.utils.ThreadContext;
import com.haoyu.sip.core.web.AbstractBaseController;
import com.haoyu.sip.file.entity.FileInfo;
import com.haoyu.sip.file.service.IFileService;

/**
 * @author lianghuahuang 
 *
 */
@Controller
@RequestMapping("**/cms")
public class CmsController extends AbstractBaseController {
	@Resource
	private IChannelService channelService;

	@Value("#{commonConfig['template.rootPath']}")
	private String templatePath;
	
	@Value("#{commonConfig['cms.template.path']}")
	private String templateLoaderPath=PropertiesLoader
			.get("cms.template.path");

	@Resource
	private ISiteInfoService siteInfoService;
	
	@Resource
	private IFileService fileService;
	
	@Resource
	private ITemplateFileService templateFileService;
	
	@Resource
	private IArticleSearchService articleSearchService;
	

	protected String getMappingFolder(){
		return Objects.toString(siteInfoService.findMappingFolderByDomain(ThreadContext.getDomain()),"");
	}
	
	protected String getLogicalViewNamePrefix(String mappingFolder){
		return "/cms/"+mappingFolder+"/";
	}

	protected String getLogicalViewNamePrefix() {
		return "/cms/"+ThreadContext.getDomain()+"/";
	}

/*	@RequestMapping(value = {"/index",""}, method = {RequestMethod.GET,RequestMethod.POST})
	public String index() {
		return getLogicalViewNamePrefix()+"/index/index";
	}*/
	@RequestMapping(value = "/file", method=RequestMethod.GET)
	@ResponseBody
	public List<FileInfo> listFileInfo(String relationId, String relationType){
		Relation relation = new Relation(relationId);
		relation.setType(relationType);
		return fileService.listFileInfoByRelation(relation);
	}
	
	@RequestMapping(value = "/nav", method = {RequestMethod.GET,RequestMethod.POST})
	public String nav() {
		//catalogService.findAll(parameter);
		return getLogicalViewNamePrefix()+"/navigation";
	}
	
	@RequestMapping(value = "/{alias}", method = {RequestMethod.GET,RequestMethod.POST})
	public String channel(@PathVariable String alias,HttpServletRequest request,Model model){
		setParameterToModel(request,model);
		model.addAttribute("alias", alias);
		String mappingFolder = getMappingFolder();
		StringBuffer path = new StringBuffer(getLogicalViewNamePrefix(mappingFolder));
		//首先获取模板文件名，判断是否存在，如果不存在则按照栏目类型进入通用模板页面，如果类型为Mixed则去到404的页面
		Channel channel = channelService.findChannelByAlias(alias, ThreadContext.getDomain());
		
		if(StringUtils.isNotEmpty(mappingFolder)){
			model.addAttribute("mappingFolder", mappingFolder);
		}
		if(channel==null){
			return "/cms/"+mappingFolder+"/common/404";
		}
		model.addAttribute("channel", channel);
		String fileName = templateFileService.getFileNameById(channel.getId());
		
		StringBuffer absoluteFilePath = new StringBuffer(templateLoaderPath);
		absoluteFilePath.append(path);
		
		File file = new File(absoluteFilePath.toString());
		if(!file.exists()){
			absoluteFilePath.delete(absoluteFilePath.indexOf(mappingFolder), absoluteFilePath.length()-1);
			path.delete(path.indexOf(mappingFolder), path.length()-1);
		}
		absoluteFilePath.append(fileName);
		file = new File(absoluteFilePath.toString());
		if(!file.exists()){
			if(channel.getDisplayType().equalsIgnoreCase("Parent")){
				file = new File(absoluteFilePath.toString().replace("_"+channel.getAlias(), ""));
				if(!file.exists()){
					return "/cms/"+mappingFolder+"/common/404";
				}
				return path.append(fileName.replace("_"+channel.getAlias(), "")).toString().replace(".ftl", "");
			}else if(channel.getDisplayType().equalsIgnoreCase("Mixed")){
				return "/cms/"+mappingFolder+"/common/404";
			}
			return path.append("common/display/").append(channel.getDisplayType()).toString().replace(".ftl", "");
		}
		return path.append(fileName).toString().replace(".ftl", "");
	}
	
	@RequestMapping(value = "/{channelId}/article/{articleId}", method = {RequestMethod.GET,RequestMethod.POST})
	public String article(@PathVariable String channelId,@PathVariable String articleId,Model model){
		setParameterToModel(request,model);
		model.addAttribute("channelId", channelId);
		model.addAttribute("articleId", articleId);
		String mappingFolder = getMappingFolder();
		if(StringUtils.isNotEmpty(mappingFolder)){
			model.addAttribute("mappingFolder", mappingFolder);
		}
		StringBuffer path = new StringBuffer(getLogicalViewNamePrefix(mappingFolder));
		StringBuffer absoluteFilePath = new StringBuffer(templateLoaderPath);
		absoluteFilePath.append(path).append(mappingFolder).append("/article/view_article");
		File file = new File(absoluteFilePath.toString());
		if(!file.exists()){
			return path.append("article/view_article").toString();
		}
		
		return path.append(mappingFolder).append("/article/view_article").toString();
	}
	
	@RequestMapping(value = "/{alias}/content/{id}", method = {RequestMethod.GET,RequestMethod.POST})
	public String content(@PathVariable String alias,@PathVariable String id,Model model){
		setParameterToModel(request,model);
		model.addAttribute("id", id);
		String mappingFolder = getMappingFolder();
		if(StringUtils.isNotEmpty(mappingFolder)){
			model.addAttribute("mappingFolder", mappingFolder);
		}
		Channel channel = channelService.findChannelByAlias(alias, ThreadContext.getDomain());
		model.addAttribute("channel",channel);
		StringBuffer path = new StringBuffer(getLogicalViewNamePrefix(mappingFolder));
		return path.append(channel.getAlias()).append("_content").toString();
	}
	
	@RequestMapping(value = "/{id}/loadMore", method = {RequestMethod.GET,RequestMethod.POST})
	public String loadMoreArticle(@PathVariable String id,Model model){
		setParameterToModel(request,model);
		model.addAttribute("id", id);
		Channel channel = channelService.findChannelById(id);
		model.addAttribute("channel", channel);
		String mappingFolder = getMappingFolder();
		return getLogicalViewNamePrefix(mappingFolder)+"/common/display/More_"+channel.getDisplayType();
	}
	
	@RequestMapping(value = "/gallery/{id}/preview", method = {RequestMethod.GET,RequestMethod.POST})
	public String previewGallery(@PathVariable String id,Model model){
		model.addAttribute("id", id);
		String mappingFolder = getMappingFolder();
		StringBuffer path = new StringBuffer(getLogicalViewNamePrefix(mappingFolder));
		StringBuffer absoluteFilePath = new StringBuffer(templateLoaderPath);
		absoluteFilePath.append(path).append(mappingFolder).append("/").append("common/gallery/preview_gallery.ftl");
		File file = new File(absoluteFilePath.toString());
		if(!file.exists()){
			return path.append("common/gallery/preview_gallery").toString();
		}
		return path.append(mappingFolder).append("/common/gallery/preview_gallery").toString();
	}
	
	@RequestMapping(value = "/search",method=RequestMethod.GET)
	@ResponseBody
	public List<Article> searchArticle(String keywords){
		if(articleSearchService!=null){
			return articleSearchService.searchArticles(keywords, this.getPageBounds(10, true));
		}
		return  null;
	}
	
	@RequestMapping(value = "/search/list",method=RequestMethod.GET)
	public String searchArticleList(String keyword,Model model){
		super.setParameterToModel(request, model);
		model.addAttribute("keyword", keyword);
		String mappingFolder = getMappingFolder();
		return getLogicalViewNamePrefix(mappingFolder)+"article/search_result";
	}
	
	
}
