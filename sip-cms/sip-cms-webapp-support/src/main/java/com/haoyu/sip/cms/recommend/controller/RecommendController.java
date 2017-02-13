/**
 * 
 */
package com.haoyu.sip.cms.recommend.controller;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Lists;
import com.haoyu.sip.cms.recommend.service.IRecommendService;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.web.AbstractBaseController;

/**
 * @author lianghuahuang
 *
 */
public class RecommendController extends AbstractBaseController {
	
	@Resource
	protected IRecommendService recommendService;
	
	protected String getLogicalViewNamePrefix() {
		return "/cms/backstage/recommend";
	}
	

}
