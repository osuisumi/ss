package com.haoyu.sip.diary.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.diary.dao.IDiaryCategoryDao;
import com.haoyu.sip.diary.entity.DiaryCategory;
import com.haoyu.sip.diary.service.IDiaryCategoryService;
import com.haoyu.sip.utils.Identities;
@Service
public class DiaryCategoryServiceImpl implements IDiaryCategoryService{

	@Resource
	private IDiaryCategoryDao diaryCategorydao;
	@Override
	public List<DiaryCategory> listDiaryCategory(Map<String, Object> param, PageBounds pageBounds) {
		return diaryCategorydao.select(param, pageBounds);
	}

	@Override
	public Response createDiaryCategory(DiaryCategory diaryCategory) {
		if (diaryCategory == null){
			return Response.failInstance();
		}
		if (StringUtils.isEmpty(diaryCategory.getId())) {
			diaryCategory.setId(Identities.uuid2());
		}
		int count = diaryCategorydao.insert(diaryCategory);
		return count > 0 ? Response.successInstance():Response.failInstance();
	}

	@Override
	public Response updateDiaryCategory(DiaryCategory diaryCategory) {
		if (diaryCategory == null||StringUtils.isEmpty(diaryCategory.getId())){
			return Response.failInstance();
		}
		int count = diaryCategorydao.update(diaryCategory);
		return count > 0 ? Response.successInstance() : Response.failInstance();
	}

	@Override
	public Response deleteDiaryCategory(DiaryCategory diaryCategory) {
		if (diaryCategory == null||StringUtils.isEmpty(diaryCategory.getId())){
			return Response.failInstance();
		}		
		int count = diaryCategorydao.delete(diaryCategory);
		return count > 0 ? Response.successInstance() : Response.failInstance();
	}

	@Override
	public DiaryCategory findDiaryCategoryById(String id) {
		return diaryCategorydao.selectByPrimaryKey(id);
	}
}