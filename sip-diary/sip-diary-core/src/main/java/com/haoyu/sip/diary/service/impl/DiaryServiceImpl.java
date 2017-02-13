package com.haoyu.sip.diary.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.diary.dao.IDiaryDao;
import com.haoyu.sip.diary.entity.Diary;
import com.haoyu.sip.diary.event.CreateDiaryEvent;
import com.haoyu.sip.diary.service.IDiaryService;
import com.haoyu.sip.file.entity.FileInfo;
import com.haoyu.sip.file.service.IFileService;
import com.haoyu.sip.utils.Identities;
@Service
public class DiaryServiceImpl implements IDiaryService {

	@Resource
	private IDiaryDao diaryDao;
	@Resource
	private ApplicationContext applicationContext;
	@Resource
	private IFileService fileService;
	
	@Override
	public List<Diary> listDiary(Map<String, Object> param, PageBounds pageBounds) {
		return diaryDao.select(param, pageBounds);
	}

	@Override
	public Response createDiary(Diary diary) {
		if (diary == null){
			return Response.failInstance();
		}
		if (StringUtils.isEmpty(diary.getId())) {
			diary.setId(Identities.uuid2());
		}
		diary.setBrowseNum(0);
		diary.setCommentNum(0);
		diary.setSupportNum(0);
		diary.setIsEssence("N");
		diary.setIsHot("N");
		diary.setIsTop("N");
		int count = diaryDao.insert(diary);
		if(count>0){
			fileService.createFileList(diary.getFileInfos(), diary.getId(), "diary");
			applicationContext.publishEvent(new CreateDiaryEvent(diary));
		}
		return count > 0 ? Response.successInstance():Response.failInstance();
	}

	@Override
	public Response updateDiary(Diary diary) {
		if (diary == null||StringUtils.isEmpty(diary.getId())){
			return Response.failInstance();
		}
		int count = diaryDao.update(diary);
		if(count > 0){
			List<FileInfo> oldFileInfos = fileService.listFileInfoByRelationId(diary.getId());
			fileService.updateFileList(diary.getFileInfos(), oldFileInfos, diary.getId(), "diary");
		}
		return count > 0 ? Response.successInstance() : Response.failInstance();
	}

	@Override
	public Response deleteDiary(Diary diary) {
		if (diary == null||StringUtils.isEmpty(diary.getId())){
			return Response.failInstance();
		}		
		int count = diaryDao.delete(diary);
		return count > 0 ? Response.successInstance() : Response.failInstance();
	}

	@Override
	public Diary findDiaryById(String id) {
		return diaryDao.selectByPrimaryKey(id);
	}

	@Override
	public Diary getDiaryByOp(Map<String, Object> param) {
		PageBounds pageBounds = new PageBounds(1);
		return diaryDao.selectByOp(param, pageBounds);
	}

}