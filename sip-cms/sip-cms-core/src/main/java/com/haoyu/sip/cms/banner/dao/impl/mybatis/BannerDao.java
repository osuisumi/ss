/**
 * 
 */
package com.haoyu.sip.cms.banner.dao.impl.mybatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.sip.cms.banner.dao.IBannerDao;
import com.haoyu.sip.cms.banner.entity.Banner;
import com.haoyu.sip.core.jdbc.MybatisDao;

/**
 * @author lianghuahuang
 *
 */
@Repository
public class BannerDao extends MybatisDao implements IBannerDao {

	/* (non-Javadoc)
	 * @see com.haoyu.sip.cms.banner.dao.IBannerDao#selectBannerById(java.lang.String)
	 */
	@Override
	public Banner selectBannerById(String id) {
		return super.selectByPrimaryKey(id);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.cms.banner.dao.IBannerDao#insertBanner(com.haoyu.sip.cms.banner.entity.Banner)
	 */
	@Override
	public int insertBanner(Banner banner) {
		banner.setDefaultValue();
		return super.insert(banner);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.cms.banner.dao.IBannerDao#updateBanner(com.haoyu.sip.cms.banner.entity.Banner)
	 */
	@Override
	public int updateBanner(Banner banner) {
		banner.setUpdateValue();
		return super.update(banner);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.cms.banner.dao.IBannerDao#deleteBannerByLogic(com.haoyu.sip.cms.banner.entity.Banner)
	 */
	@Override
	public int deleteBannerByLogic(Banner banner) {
		banner.setUpdateValue();
		return super.deleteByLogic(banner);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.cms.banner.dao.IBannerDao#deleteBannerByPhysics(java.lang.String)
	 */
	@Override
	public int deleteBannerByPhysics(String id) {
		return super.deleteByPhysics(id);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.cms.banner.dao.IBannerDao#findAll(java.util.Map)
	 */
	@Override
	public List<Banner> findAll(Map<String, Object> parameter) {
		return super.selectList("selectByParameter", parameter);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.cms.banner.dao.IBannerDao#findAll(java.util.Map, com.github.miemiedev.mybatis.paginator.domain.PageBounds)
	 */
	@Override
	public List<Banner> findAll(Map<String, Object> parameter,
			PageBounds pageBounds) {
		return super.selectList("selectByParameter", parameter,pageBounds);
	}

}
