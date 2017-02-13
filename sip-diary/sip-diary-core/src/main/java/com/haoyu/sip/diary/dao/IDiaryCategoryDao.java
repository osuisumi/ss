package com.haoyu.sip.diary.dao;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.sip.diary.entity.DiaryCategory;

public interface IDiaryCategoryDao {
	int insert(DiaryCategory diaryCategory);

	int update(DiaryCategory diaryCategory);

	int delete(DiaryCategory diaryCategory);
	
	DiaryCategory selectByPrimaryKey(String id);

	List<DiaryCategory> select(Map<String, Object> param,PageBounds pageBounds);
}
