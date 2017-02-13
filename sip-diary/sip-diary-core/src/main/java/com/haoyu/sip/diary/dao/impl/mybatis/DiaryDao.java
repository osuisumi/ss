package com.haoyu.sip.diary.dao.impl.mybatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.sip.core.jdbc.MybatisDao;
import com.haoyu.sip.core.utils.ThreadContext;
import com.haoyu.sip.diary.dao.IDiaryDao;
import com.haoyu.sip.diary.entity.Diary;
import com.haoyu.sip.utils.Collections3;
@Repository
public class DiaryDao extends MybatisDao implements IDiaryDao {

	@Override
	public int insert(Diary diary) {
		diary.setDefaultValue();
		return super.insert(diary);
	}

	@Override
	public int update(Diary diary) {
		diary.setUpdateTime(System.currentTimeMillis());
		diary.setUpdatedby(ThreadContext.getUser());
		return super.update(diary);
	}

	@Override
	public int delete(Diary diary) {
		diary.setUpdateTime(System.currentTimeMillis());
		diary.setUpdatedby(ThreadContext.getUser());
		return super.deleteByLogic(diary);
	}
	
	@Override
	public Diary selectByPrimaryKey(String id) {
		return super.selectByPrimaryKey(id);
	}

	@Override
	public List<Diary> select(Map<String, Object> param, PageBounds pageBounds) {
		return super.selectList("select", param, pageBounds);
	}
	
	@Override
	public Diary selectByOp(Map<String, Object> param, PageBounds pageBounds) {
		List<Diary> diaries = super.selectList("selectByOp", param,pageBounds);
		if (Collections3.isNotEmpty(diaries)) {
			return diaries.get(0);
		}
		return null;
	}

}
