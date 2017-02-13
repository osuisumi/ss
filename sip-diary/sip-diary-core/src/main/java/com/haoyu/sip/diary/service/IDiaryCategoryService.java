package com.haoyu.sip.diary.service;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.diary.entity.DiaryCategory;

public interface IDiaryCategoryService {
	List<DiaryCategory> listDiaryCategory(Map<String, Object> param,PageBounds pageBounds);

	Response createDiaryCategory(DiaryCategory diaryCategory);

	Response updateDiaryCategory(DiaryCategory diaryCategory);
	
	Response deleteDiaryCategory(DiaryCategory diaryCategory);

	DiaryCategory findDiaryCategoryById(String id);
}
