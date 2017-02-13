/**
 * 
 */
package com.haoyu.sip.auth.realm;

import java.util.List;

import com.haoyu.sip.auth.entity.AuthUser;

/**
 * @author lianghuahuang
 *
 */
public interface CacheCleaner {
	
	void clearUserCache();
	
	void clearUserCacheById(String id) ;
	
	void clearUserCacheByIds(List<String> ids) ;
	
	void clearUserCacheByUsername(String username);
	
	List<AuthUser> findAuthUserByIds(List<String> ids);
	
	boolean hasCache();
}
