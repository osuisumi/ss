package com.haoyu.sip.diary.dao.impl.mybatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.sip.core.jdbc.MybatisDao;
import com.haoyu.sip.core.utils.ThreadContext;
import com.haoyu.sip.diary.dao.IDiaryCategoryDao;
import com.haoyu.sip.diary.entity.DiaryCategory;
@Repository
public class DiaryCategoryDao extends MybatisDao implements IDiaryCategoryDao {

	@Override
	public int insert(DiaryCategory diaryCategory) {
		diaryCategory.setDefaultValue();
		return super.insert(diaryCategory);
	}

	@Override
	public int update(DiaryCategory diaryCategory) {
		diaryCategory.setUpdateTime(System.currentTimeMillis());
		diaryCategory.setUpdatedby(ThreadContext.getUser());
		return super.update(diaryCategory);
	}

	@Override
	public int delete(DiaryCategory diaryCategory) {
		diaryCategory.setUpdateTime(System.currentTimeMillis());
		diaryCategory.setUpdatedby(ThreadContext.getUser());
		return super.deleteByLogic(diaryCategory);
	}

	@Override
	public DiaryCategory selectByPrimaryKey(String id) {
		return super.selectByPrimaryKey(id);
	}

	@Override
	public List<DiaryCategory> select(Map<String, Object> param, PageBounds pageBounds) {
		return super.selectList("select", param, pageBounds);
	}
}
