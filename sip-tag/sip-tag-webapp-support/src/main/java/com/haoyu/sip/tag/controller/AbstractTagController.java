/**
 * 
 */
package com.haoyu.sip.tag.controller;

import org.springframework.web.bind.annotation.RequestMapping;

import com.haoyu.sip.core.entity.User;
import com.haoyu.sip.core.web.AbstractBaseController;

/**
 * @author lianghuahuang
 *
 */
@RequestMapping("/tags")
public class AbstractTagController extends AbstractBaseController {
	
	protected String getLogicalViewNamePrefix(){
		return "tag/";
	}

}
