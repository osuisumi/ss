package com.haoyu.sip.diary.dao;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.sip.diary.entity.Diary;

public interface IDiaryDao {
	
	int insert(Diary diary);

	int update(Diary diary);

	int delete(Diary diary);
	
	Diary selectByPrimaryKey(String id);

	List<Diary> select(Map<String, Object> param,PageBounds pageBounds);

	Diary selectByOp(Map<String, Object> param, PageBounds pageBounds);
}
