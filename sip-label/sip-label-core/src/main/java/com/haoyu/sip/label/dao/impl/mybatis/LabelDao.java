package com.haoyu.sip.label.dao.impl.mybatis;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.sip.core.jdbc.MybatisDao;
import com.haoyu.sip.label.dao.ILabelDao;
import com.haoyu.sip.label.entity.Label;

@Repository
public class LabelDao extends MybatisDao implements ILabelDao{

	public List<Label> selectByName(Label label, PageBounds pageBounds) {
		return selectList("selectByName", label, pageBounds);
	}
	
	public List<Label> selectByRelationId(String relationId) {
		return selectList("selectByRelationId", relationId);
	}

	@Override
	public int deleteWithoutRelation(Label label) {
		return this.update("deleteWithoutRelation",label);
	}

	@Override
	public Label selectOneByName(String name) {
		return selectOne("selectOneByName",name);
	}
	

}
