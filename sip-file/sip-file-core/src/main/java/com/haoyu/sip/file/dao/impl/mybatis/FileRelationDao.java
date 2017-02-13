package com.haoyu.sip.file.dao.impl.mybatis;

import org.springframework.stereotype.Repository;

import com.haoyu.sip.core.jdbc.MybatisDao;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.file.dao.IFileRelationDao;
import com.haoyu.sip.file.entity.FileRelation;

@Repository
public class FileRelationDao extends MybatisDao implements IFileRelationDao{

	@Override
	public int deleteFileRelation(FileRelation fileRelation) {
		return update("deleteFileRelation",fileRelation);
	}

}
