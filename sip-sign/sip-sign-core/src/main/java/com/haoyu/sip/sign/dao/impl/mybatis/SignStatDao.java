package com.haoyu.sip.sign.dao.impl.mybatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.sip.core.jdbc.MybatisDao;
import com.haoyu.sip.sign.dao.ISignStatDao;
import com.haoyu.sip.sign.entity.SignStat;

@Repository
public class SignStatDao extends MybatisDao implements ISignStatDao{

	@Override
	public int update(SignStat signStat) {
		return super.update(signStat);
	}

	@Override
	public int insert(SignStat signStat) {
		return super.insert(signStat);
	}

	@Override
	public SignStat selectById(String id) {
		return super.selectByPrimaryKey(id);
	}

	@Override
	public List<SignStat> selectRank(Map<String, Object> param, PageBounds pageBounds) {
		return selectList("selectRank", param, pageBounds);
	}

	@Override
	public int getCount(Map<String, Object> param) {
		return selectOne("getCount", param);
	}
	
}
