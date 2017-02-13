/**
 * 
 */
package com.haoyu.sip.cms.siteinfo.dao.impl.mybatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.sip.cms.siteinfo.dao.ISiteInfoDao;
import com.haoyu.sip.cms.siteinfo.entity.SiteInfo;
import com.haoyu.sip.core.jdbc.MybatisDao;

/**
 * @author lianghuahuang
 *
 */
@Repository
public class SiteInfoDao extends MybatisDao implements ISiteInfoDao {

	/* (non-Javadoc)
	 * @see com.haoyu.sip.cms.siteInfo.dao.ISiteInfoDao#selectSiteInfoById(java.lang.String)
	 */
	@Override
	public SiteInfo selectSiteInfoById(String id) {
		return super.selectByPrimaryKey(id);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.cms.siteInfo.dao.ISiteInfoDao#insertSiteInfo(com.haoyu.sip.cms.siteInfo.entity.SiteInfo)
	 */
	@Override
	public int insertSiteInfo(SiteInfo siteInfo) {
		siteInfo.setDefaultValue();
		return super.insert(siteInfo);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.cms.siteInfo.dao.ISiteInfoDao#updateSiteInfo(com.haoyu.sip.cms.siteInfo.entity.SiteInfo)
	 */
	@Override
	public int updateSiteInfo(SiteInfo siteInfo) {
		siteInfo.setUpdateValue();
		return super.update(siteInfo);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.cms.siteInfo.dao.ISiteInfoDao#deleteSiteInfoByLogic(com.haoyu.sip.cms.siteInfo.entity.SiteInfo)
	 */
	@Override
	public int deleteSiteInfoByLogic(SiteInfo siteInfo) {
		siteInfo.setUpdateValue();
		return super.deleteByLogic(siteInfo);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.cms.siteInfo.dao.ISiteInfoDao#deleteSiteInfoByPhysics(java.lang.String)
	 */
	@Override
	public int deleteSiteInfoByPhysics(String id) {
		return super.deleteByPhysics(id);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.cms.siteInfo.dao.ISiteInfoDao#findAll(java.util.Map)
	 */
	@Override
	public List<SiteInfo> findAll(Map<String, Object> parameter) {
		return super.selectList("selectByParameter", parameter);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.cms.siteInfo.dao.ISiteInfoDao#findAll(java.util.Map, com.github.miemiedev.mybatis.paginator.domain.PageBounds)
	 */
	@Override
	public List<SiteInfo> findAll(Map<String, Object> parameter,
			PageBounds pageBounds) {
		return super.selectList("selectByParameter", parameter,pageBounds);
	}

	@Override
	public SiteInfo selectSiteInfoByDomain(String domain) {
		return super.selectOne("selectByDomain", domain);
	}

}
