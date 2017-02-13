/**
 * 
 */
package com.haoyu.sip.cms.template.service;

import com.haoyu.sip.cms.template.entity.TemplateFile;
import com.haoyu.sip.core.service.Response;

/**
 * @author lianghuahuang
 *
 */
public interface ITemplateFileService {
	
	Response createTemplateFile(String type,String alias,TemplateFile templateFile);
	
	Response updateTemplateFile(String type,String alias,TemplateFile templateFile);
	
	TemplateFile findTemplateFile(String type,String id);
	
	Response deleteTemplateFile(String alias);
	
	Response deleteTemplateFile(String type,String alias);
	
	String getFileNameById(String id);
}
