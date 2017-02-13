/**
 * 
 */
package com.haoyu.sip.cms.recommend.dao.impl.mybatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.sip.cms.recommend.dao.IRecommendWorkshopDao;
import com.haoyu.sip.cms.recommend.entity.RecommendWorkshop;
import com.haoyu.sip.core.jdbc.MybatisDao;

/**
 * @author lianghuahuang
 *
 */
@Repository
public class RecommendWorkshopDao extends MybatisDao implements
IRecommendWorkshopDao {

	/* (non-Javadoc)
	 * @see com.haoyu.sip.cms.recommend.dao.IRecommendWorkshopDao#selectRecommendWorkshops(java.util.Map)
	 */
	@Override
	public List<RecommendWorkshop> selectRecommendWorkshops(
			Map<String, Object> parameter) {
		return super.selectList("selectByParameter", parameter);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.cms.recommend.dao.IRecommendWorkshopDao#selectRecommendWorkshops(java.util.Map, com.github.miemiedev.mybatis.paginator.domain.PageBounds)
	 */
	@Override
	public List<RecommendWorkshop> selectRecommendWorkshops(
			Map<String, Object> parameter, PageBounds pageBounds) {
		return super.selectList("selectByParameter", parameter,pageBounds);
	}

}
