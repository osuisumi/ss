package com.haoyu.sip.diary.service;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.diary.entity.Diary;

public interface IDiaryService {
	
	List<Diary> listDiary(Map<String, Object> param,PageBounds pageBounds);

	Response createDiary(Diary diary);

	Response updateDiary(Diary diary);
	
	Response deleteDiary(Diary diary);

	Diary findDiaryById(String id);
	
	Diary getDiaryByOp(Map<String, Object> param);
}
