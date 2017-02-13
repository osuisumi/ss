/**
 * 
 */
package com.haoyu.sip.cms.recommend.dao.impl.mybatis;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.haoyu.sip.cms.recommend.dao.IRecommendDao;
import com.haoyu.sip.cms.recommend.entity.Recommend;
import com.haoyu.sip.core.jdbc.MybatisDao;

/**
 * @author lianghuahuang
 *
 */
@Repository
public class RecommendDao extends MybatisDao implements IRecommendDao {

	/* (non-Javadoc)
	 * @see com.haoyu.sip.cms.recommend.dao.IRecommendDao#insertRecommend(com.haoyu.sip.cms.recommend.entity.Recommend)
	 */
	@Override
	public int insertRecommend(Recommend recommend) {
		recommend.setDefaultValue();
		return super.insert(recommend);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.cms.recommend.dao.IRecommendDao#updateRecommend(com.haoyu.sip.cms.recommend.entity.Recommend)
	 */
	@Override
	public int updateRecommend(Recommend recommend) {
		recommend.setUpdateValue();
		return super.update(recommend);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.cms.recommend.dao.IRecommendDao#deleteRecommendByPhysics(com.haoyu.sip.cms.recommend.entity.Recommend)
	 */
	@Override
	public int deleteRecommendByPhysics(Recommend recommend) {
		return super.deleteByPhysics(recommend);
	}

	@Override
	public int deleteRecommendByPhysics(List<String> ids) {
		return super.delete("deleteByPhysicsFromIds", ids);
	}

}
