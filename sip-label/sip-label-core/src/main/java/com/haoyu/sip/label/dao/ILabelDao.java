package com.haoyu.sip.label.dao;

import java.util.List;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.sip.label.entity.Label;

public interface ILabelDao {

	List<Label> selectByName(Label label, PageBounds pageBounds);

	int deleteWithoutRelation(Label label);

	List<Label> selectByRelationId(String relationId);

	Label selectOneByName(String name);

}
