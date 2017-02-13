package com.haoyu.sip.textbook.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.base.utils.BaseServiceUtils;
import com.haoyu.sip.core.jdbc.MybatisDao;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.utils.PropertiesLoader;
import com.haoyu.sip.excel.ExcelImport;
import com.haoyu.sip.textbook.dao.ITextBookRelationDao;
import com.haoyu.sip.textbook.entity.ExcelTextBookRelation;
import com.haoyu.sip.textbook.entity.TextBookEntry;
import com.haoyu.sip.textbook.entity.TextBookRelation;
import com.haoyu.sip.textbook.service.ITextBookEntryService;
import com.haoyu.sip.textbook.service.ITextBookRelationService;
import com.haoyu.sip.textbook.service.ITextBookTypeService;
import com.haoyu.sip.utils.Collections3;
import com.haoyu.sip.utils.Identities;

@Service
public class TextBookRelationService implements ITextBookRelationService {
	@Resource
	private ITextBookRelationDao textBookRelationDao;
	@Resource
	private ITextBookTypeService textBookTypeService;
	@Resource
	private ITextBookEntryService textBookEntryService;
	@Resource
	private ITextBookRelationService textBookRelationService;
	@Resource
	private PropertiesLoader propertiesLoader;
	
	@Override
	public Response create(TextBookRelation textBookRelation) {
		return BaseServiceUtils.create(textBookRelation, (MybatisDao) textBookRelationDao);
	}

	@Override
	public Response update(TextBookRelation textBookRelation) {
		return BaseServiceUtils.update(textBookRelation, (MybatisDao) textBookRelationDao);
	}

	@Override
	public Response deleteByLogic(TextBookRelation TextBookRelation) {
		return BaseServiceUtils.delete(TextBookRelation.getId(), (MybatisDao) textBookRelationDao);
	}

	@Override
	public TextBookRelation get(TextBookRelation textBookRelation) {
		return (TextBookRelation) BaseServiceUtils.get(textBookRelation.getId(), (MybatisDao) textBookRelationDao);
	}

	@Override
	public List<TextBookRelation> list(TextBookRelation textBookRelation, PageBounds pageBounds) {
		return ((MybatisDao) textBookRelationDao).selectList("select", textBookRelation, pageBounds);
	}
	
	@Override
	public List<TextBookEntry> listEntry(TextBookRelation textBookRelation, PageBounds pageBounds) {
		return ((MybatisDao) textBookRelationDao).selectList("selectEntry", textBookRelation, pageBounds);
	}

	private void saveStage(ExcelTextBookRelation tbr, List<TextBookEntry> existTextBookEntryList) {
		if (StringUtils.isNotEmpty(tbr.getStage())) {
			TextBookEntry textBookEntry_stage = new TextBookEntry("STAGE", tbr.getStage());
			saveEntry(textBookEntry_stage, "STAGE", existTextBookEntryList);
		}
	}

	private void saveSubject(ExcelTextBookRelation tbr, List<TextBookEntry> existTextBookEntryList) {
		if (StringUtils.isNotEmpty(tbr.getSubject())) {
			TextBookEntry textBookEntry_subject = new TextBookEntry("SUBJECT", tbr.getSubject());
			saveEntry(textBookEntry_subject, "SUBJECT", existTextBookEntryList);
		}

	}

	private void saveGrade(ExcelTextBookRelation tbr, List<TextBookEntry> existTextBookEntryList) {
		List<String> gradeStrs = Arrays.asList(tbr.getGrade().split("，"));
		for (String gradeStr : gradeStrs) {
			if (StringUtils.isNotEmpty(gradeStr)) {
				TextBookEntry textBookEntry_grade = new TextBookEntry("GRADE", gradeStr);
				saveEntry(textBookEntry_grade, "GRADE", existTextBookEntryList);
			}
		}
	}

	private void saveEntry(TextBookEntry textBookEntry, String type, List<TextBookEntry> existTextBookEntryList) {
		if (!existTextBookEntryList.contains(textBookEntry)) {
			Integer max = textBookEntryService.getMaxValue(new TextBookEntry(type));
			if (max == null) {
				max = 0;
			}
			textBookEntry.setTextBookValue(max + 1 + "");
			textBookEntry.setSortNo(max + 1);
			Response response = textBookEntryService.create(textBookEntry);
			existTextBookEntryList.add((TextBookEntry)response.getResponseData());
		}
	}

	@Override
	public Response deleteByPhysics(TextBookRelation textBookRelation) {
		return  ((MybatisDao)textBookRelationDao).deleteByPhysics(textBookRelation)>0?Response.successInstance():Response.failInstance();
	}

	@Override
	public Map<String, TextBookRelation> selectForMap(TextBookRelation textBookRelation) {
		return ((MybatisDao)textBookRelationDao).selectMap("select", textBookRelation, "id", null);
	}

	@Override
	public Map<String, List<String>> importUtil(String url) {
		String tempFileDir = propertiesLoader.getProperty("file.temp.dir");
		File f = new File(tempFileDir + url);
		//导出信息
		Map<String,List<String>> resultMap = createResultMap();
		
		ExcelImport<ExcelTextBookRelation> excelImport = new ExcelImport<ExcelTextBookRelation>(ExcelTextBookRelation.class);
		Collection<ExcelTextBookRelation> list = excelImport.importExcel(f, 0, 1);
		List<TextBookEntry> existTextBookEntryList = textBookEntryService.list(null, null);
		List<String> stageResult = new ArrayList<String>();
		List<String> subjectResult = new ArrayList<String>();
		List<String> gradeResult = new ArrayList<String>();
		
		Map<String,List<Map<String,List<String>>>> relationResult = new HashMap<String, List<Map<String,List<String>>>>();
		//已存在的
		Map<String,String> stageMap = Collections3.extractToMap(textBookEntryService.list(new TextBookEntry("STAGE"),null), "textBookValue", "id");
		Map<String,String> subjectMap = Collections3.extractToMap(textBookEntryService.list(new TextBookEntry("SUBJECT"), null), "textBookValue", "id");
		Map<String,String> gradeMap = Collections3.extractToMap(textBookEntryService.list(new TextBookEntry("GRADE"), null), "textBookValue", "id");
		Map<String,String> versionMap = Collections3.extractToMap(textBookEntryService.list(new TextBookEntry("VERSION"), null), "textBookValue", "id");
		Map<String,String> sectionMap = Collections3.extractToMap(textBookEntryService.list(new TextBookEntry("SECTION"), null), "textBookValue", "id");
		Map<String,Integer> maxValueMap = new HashMap<String,Integer>();
		//已存在的的relation
		List<TextBookRelation> existRelation = textBookRelationService.list(null, null);
		//学段 学科  年级 版本 章 节
		for(ExcelTextBookRelation etbr:list){
			//学段
			TextBookEntry stage = new TextBookEntry("STAGE",etbr.getStage().replaceAll(" ", " ").trim());
			TextBookEntry subject = new TextBookEntry("SUBJECT",etbr.getSubject().replaceAll(" ", " ").trim());
			TextBookEntry grade = new TextBookEntry("GRADE",etbr.getGrade().replaceAll(" ", " ").trim());
			TextBookEntry version = new TextBookEntry("VERSION",etbr.getVersion().replaceAll(" ", " ").trim());
			TextBookEntry chapter = new TextBookEntry("SECTION",etbr.getChapter().replaceAll(" ", " ").trim());
			TextBookEntry section = new TextBookEntry("SECTION",etbr.getSection().replaceAll(" ", " ").trim());
			section.setParentName(chapter.getTextBookName());
			chapter.getChildTextBookEntrys().add(section);
			
			List<TextBookEntry> prepareAdd = new ArrayList<TextBookEntry>();
			prepareAdd.add(stage);
			prepareAdd.add(subject);
			prepareAdd.add(grade);
			prepareAdd.add(version);
			prepareAdd.add(chapter);
			prepareAdd.add(section);
			for(TextBookEntry textBookEntry:prepareAdd){
				if(StringUtils.isEmpty(textBookEntry.getTextBookName())){
					continue;
				}
				if(!existTextBookEntryList.contains(textBookEntry)){
					Integer max;
					if(StringUtils.isEmpty(textBookEntry.getParentName())){
						if(maxValueMap.get(textBookEntry.getTextBookTypeCode())==null){
							max  = textBookEntryService.getMaxValue(textBookEntry);
							textBookEntry.setSortNo(max+1);
							textBookEntry.setTextBookValue(max+1+"");
							maxValueMap.put(textBookEntry.getTextBookTypeCode(), max +1);
						}else{
							max = maxValueMap.get(textBookEntry.getTextBookTypeCode());
							textBookEntry.setSortNo(max+1);
							textBookEntry.setTextBookValue(max+1+"");
							maxValueMap.put(textBookEntry.getTextBookTypeCode(), max +1);
						}
			
					}else{
						String parentValue = "";
						for(TextBookEntry t:textBookEntryService.list(new TextBookEntry(textBookEntry.getTextBookTypeCode(),textBookEntry.getParentName()), null)){
							if(StringUtils.isEmpty(t.getParentValue())){
								parentValue = t.getTextBookValue();
								break;
							}
						}
						textBookEntry.setParentValue(parentValue);
						String key = textBookEntry.getTextBookTypeCode() + "parentValue" + parentValue;
						if(maxValueMap.get(key) == null){
							max = textBookEntryService.getMaxValue(textBookEntry); 
							textBookEntry.setTextBookValue(parentValue+"-"+(max+1));
							textBookEntry.setSortNo(max+1);
							maxValueMap.put(key, max +1);
						}else{
							max = maxValueMap.get(key);
							textBookEntry.setTextBookValue(parentValue+"-"+(max+1));
							textBookEntry.setSortNo(max+1);
							maxValueMap.put(key, max +1);
						}
					}
					String id = Identities.uuid2();
					textBookEntry.setId(id);
					textBookEntryService.create(textBookEntry);
					existTextBookEntryList.add(textBookEntry);
					if(textBookEntry.getTextBookTypeCode() .equals("STAGE") ){
						stageMap.put(textBookEntry.getTextBookValue(),id);
						resultMap.get("stageResult").add(textBookEntry.getTextBookName());
					}else if(textBookEntry.getTextBookTypeCode() .equals("SUBJECT")){
						subjectMap.put(textBookEntry.getTextBookValue(),id);
						resultMap.get("subjectResult").add(textBookEntry.getTextBookName());
					}else if(textBookEntry.getTextBookTypeCode() .equals("GRADE")){
						gradeMap.put(textBookEntry.getTextBookValue(),id );
						resultMap.get("gradeResult").add(textBookEntry.getTextBookName());
					}else if(textBookEntry.getTextBookTypeCode() .equals("VERSION")){
						versionMap.put(textBookEntry.getTextBookValue(),id);
						resultMap.get("versionResult").add(textBookEntry.getTextBookName());
					}else if(textBookEntry.getTextBookTypeCode() .equals("SECTION")){
						sectionMap.put(textBookEntry.getTextBookValue(),id);
						if(StringUtils.isEmpty(textBookEntry.getParentName())){
							resultMap.get("chapterResult").add(textBookEntry.getTextBookName());
						}else{
							resultMap.get("sectionResult").add(textBookEntry.getTextBookName());
						}
					}
				}else{
					for(TextBookEntry t:existTextBookEntryList){
						if(t.equals(textBookEntry)){
							textBookEntry.setTextBookValue(t.getTextBookValue());
						}
					}
				}
			}
			
			List<TextBookRelation> textBookRelations = new ArrayList<TextBookRelation>();
				//stage-subject
			TextBookRelation textBookRelation1 = new TextBookRelation(subjectMap.get(subject.getTextBookValue()), "{\"stage\":"+stage.getTextBookValue()+"}");
				//stage-subject-grade
			TextBookRelation textBookRelation2 = new TextBookRelation(gradeMap.get(grade.getTextBookValue()),"{\"stage\":"+stage.getTextBookValue()+"},{\"subject\":"+subject.getTextBookValue()+"}");
				//stage-subject-grade-version
			TextBookRelation textBookRelation3 = new TextBookRelation(versionMap.get(version.getTextBookValue()),"{\"stage\":"+stage.getTextBookValue()+"},{\"subject\":"+subject.getTextBookValue()+"},{\"grade\":"+grade.getTextBookValue()+"}");
				//stage-subject-grade-version-chapter
			TextBookRelation textBookRelation4 = new TextBookRelation(sectionMap.get(chapter.getTextBookValue()),"{\"stage\":"+stage.getTextBookValue()+"},{\"subject\":"+subject.getTextBookValue()+"},{\"grade\":"+grade.getTextBookValue()+"},"+"{\"version\":"+version.getTextBookValue()+"}");
				//stage-subject-grade-version-chapter-section
			if(!StringUtils.isEmpty(section.getTextBookName())){
				TextBookRelation textBookRelation5 = new TextBookRelation(sectionMap.get(section.getTextBookValue()),"{\"stage\":"+stage.getTextBookValue()+"},{\"subject\":"+subject.getTextBookValue()+"},{\"grade\":"+grade.getTextBookValue()+"},"+"{\"version\":"+version.getTextBookValue()+"},"+"{\"section\":"+chapter.getTextBookValue()+"}");
				textBookRelations.add(textBookRelation5);
			}
			
			
			textBookRelations.add(textBookRelation1);
			textBookRelations.add(textBookRelation2);
			textBookRelations.add(textBookRelation3);
			textBookRelations.add(textBookRelation4);
			
			
			for(TextBookRelation tbr:textBookRelations){
				if(!existRelation.contains(tbr)){
					textBookRelationService.create(tbr);
					existRelation.add(tbr);
				}
			}
		}
		
		//清除缓存
//		TextBookUtils.initAll();
		return resultMap;
	}
	
	
	private  Map<String,List<String>> createResultMap(){
		Map<String,List<String>> resultMap = new HashMap<String,List<String>>();
		List<String> stageResult = new ArrayList<String>();
		List<String> subjectResult = new ArrayList<String>();
		List<String> gradeResult = new ArrayList<String>();
		List<String> versionResult = new ArrayList<String>();
		List<String> chapterResult = new ArrayList<String>();
		List<String> sectionResult = new ArrayList<String>();
		resultMap.put("stageResult", stageResult);
		resultMap.put("subjectResult", subjectResult);
		resultMap.put("gradeResult", gradeResult);
		resultMap.put("versionResult", versionResult);
		resultMap.put("chapterResult", chapterResult);
		resultMap.put("sectionResult", sectionResult);
		return resultMap;
	}


}
