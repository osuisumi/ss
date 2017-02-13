/**
 * 
 */
package com.haoyu.sip.cms.magazine.service;

import com.haoyu.sip.cms.magazine.entity.Magazine;
import com.haoyu.sip.core.service.Response;

/**
 * @author lianghuahuang
 *
 */
public interface IMagazineService {
	
	Response createMagazine(Magazine magazine);
}
