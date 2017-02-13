/**
 * 
 */
package com.haoyu.sip.cms.recommend.controller;


import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.google.common.collect.Lists;
import com.haoyu.sip.cms.channel.entity.Channel;
import com.haoyu.sip.cms.recommend.entity.Recommend;
import com.haoyu.sip.cms.recommend.entity.RecommendCourse;
import com.haoyu.sip.cms.recommend.service.IRecommendCourseService;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.web.AbstractBaseController;

/**
 * @author lianghuahuang
 *
 */
@Controller
@RequestMapping("/recommend")
public class RecommendCourseController extends RecommendController {
	@Resource
	private IRecommendCourseService recommendCourseService;
	
	@Value("#{commonConfig['project.name']}")
	private String projectName;
	
	@Value("#{commonConfig['template.rootPath']}")
	private String templatePath;
	
	protected String getLogicalViewNamePrefix() {
		return super.getLogicalViewNamePrefix()+"/course";
	}
	

	@RequestMapping(value="/{channelId}/addCourseToRecommend/{ids}",method=RequestMethod.POST)
	@ResponseBody
	public Response addCourseToRecommend(@PathVariable String channelId,@PathVariable String ids){
	//	RecommendCourse recommendCourse = new RecommendCourse(new Recommend(new Channel(channelId)),courseId);
		return recommendCourseService.addCourseToRecommend(Lists.newArrayList(StringUtils.split(ids, ",")),new Channel(channelId));
	}
	
	@RequestMapping(value="/{channelId}/courses",method=RequestMethod.GET)
	public String getRecommendCourse(@PathVariable String channelId,RecommendCourse recommendCourse,Model model){
		model.addAttribute("channelId", channelId);
		if(recommendCourse==null){
			model.addAttribute("recommendCourse", new RecommendCourse(new Recommend(new Channel(channelId))));
		}
		model.addAttribute("pageBounds", getPageBounds(10, true));
		return getLogicalViewNamePrefix()+"/list_recommendCourse";
	}

	@RequestMapping(value = "/removeCourseToRecommend/{ids}",method=RequestMethod.DELETE)
	@ResponseBody
	public Response deleteRecommend(@PathVariable String ids){
		return recommendService.deleteRecommend(Lists.newArrayList(StringUtils.split(ids, ",")));
	}
}
