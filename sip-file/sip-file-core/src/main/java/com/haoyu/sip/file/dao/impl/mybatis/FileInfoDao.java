package com.haoyu.sip.file.dao.impl.mybatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.sip.core.jdbc.MybatisDao;
import com.haoyu.sip.file.dao.IFileInfoDao;
import com.haoyu.sip.file.entity.FileInfo;

@Repository
public class FileInfoDao extends MybatisDao implements IFileInfoDao {

	@Override
	public int getCount(Map<String, Object> param) {
		return selectOne("getCount", param);
	}

	@Override
	public List<FileInfo> select(Map<String, Object> param, PageBounds pageBounds) {
		return selectList("select", param, pageBounds);
	}

}
