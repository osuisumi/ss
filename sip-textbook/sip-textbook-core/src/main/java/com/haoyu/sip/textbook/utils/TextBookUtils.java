package com.haoyu.sip.textbook.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.JacksonJsonRedisSerializer;
import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.haoyu.sip.core.utils.PropertiesLoader;
import com.haoyu.sip.textbook.entity.TextBookEntry;
import com.haoyu.sip.textbook.entity.TextBookRelation;
import com.haoyu.sip.textbook.service.ITextBookEntryService;
import com.haoyu.sip.textbook.service.ITextBookRelationService;

/**
 * @author shisibo
 *
 */
@Component
public class TextBookUtils {

	@Resource
	private ITextBookEntryService textBookEntryService;
	@Resource
	private ITextBookRelationService textBookRelationService;
	@Resource
	private RedisTemplate redisTemplate;

	private static String appName;

	private static TextBookUtils textBookUtils;

	@PostConstruct
	public void init() {
		textBookUtils = this;
		textBookUtils.textBookEntryService = this.textBookEntryService;
		textBookUtils.textBookRelationService = this.textBookRelationService;
		textBookUtils.redisTemplate = this.redisTemplate;
		appName = PropertiesLoader.get("redis.app.key");
	}

	public static void initAll(){
		textBookUtils.redisTemplate.setValueSerializer(textBookUtils.redisTemplate.getDefaultSerializer());
		Set<String> keysEntry = textBookUtils.redisTemplate.keys(appName + ":textBook:entry:*");
		Set<String> keysRelation = textBookUtils.redisTemplate.keys(appName + ":textBook:relation:*");
		if(!keysEntry.isEmpty()){
			textBookUtils.redisTemplate.delete(keysEntry);
		}
		if(!keysRelation.isEmpty()){
			textBookUtils.redisTemplate.delete(keysRelation);
		}
		
	}
	
	public static void init(String textBookTypeCode){
		textBookUtils.redisTemplate.setValueSerializer(textBookUtils.redisTemplate.getDefaultSerializer());
		ValueOperations<String, List<TextBookEntry>> valueOper = textBookUtils.redisTemplate.opsForValue();
		String key = appName + ":textBook:entry:" + textBookTypeCode;
		textBookUtils.redisTemplate.delete(key);
	}

	public static Map<String, TextBookEntry> getEntryMap(String textBookTypeCode) {
		Map<String, TextBookEntry> entryMap = Maps.newHashMap();
		textBookUtils.redisTemplate.setHashValueSerializer(new JacksonJsonRedisSerializer(TextBookEntry.class));
		HashOperations<String, String, TextBookEntry> hashOper = textBookUtils.redisTemplate.opsForHash();
		String key = appName + ":textBook:entry:" + textBookTypeCode;
		if (textBookUtils.redisTemplate.hasKey(key)) {
			entryMap = hashOper.entries(key);
		} else {
			TextBookEntry textBookEntry = new TextBookEntry();
			textBookEntry.setTextBookTypeCode(textBookTypeCode);
			entryMap = textBookUtils.textBookEntryService.selectForMap(textBookEntry);
			if (entryMap != null && !entryMap.isEmpty()) {
				hashOper.putAll(key, entryMap);
			}
		}
		return entryMap;
	}


	public static List<TextBookRelation> getRelation(String textBookTypeCode) {
		List<TextBookRelation> list = new ArrayList<TextBookRelation>();
		textBookUtils.redisTemplate.setValueSerializer(textBookUtils.redisTemplate.getDefaultSerializer());
		ValueOperations<String, List<TextBookRelation>> valueOper = textBookUtils.redisTemplate.opsForValue();
		String key = appName + ":textBook:relation:" + textBookTypeCode;
		if (textBookUtils.redisTemplate.hasKey(key)) {
			list = valueOper.get(key);
		} else {
			TextBookRelation textBookRelation = new TextBookRelation();
			textBookRelation.setTextBookTypeCode(textBookTypeCode);
			list = textBookUtils.textBookRelationService.list(textBookRelation, null);
			if (list != null && !list.isEmpty()) {
				valueOper.set(key, list);
			}
		}
		return list;
	}

	public static Map<String, TextBookEntry> getEntryMap(String textBookTypeCode, String parentValue) {
		Map<String, TextBookEntry> entryMap = getEntryMap(textBookTypeCode);
		Map<String, TextBookEntry> resultMap = Maps.newHashMap();
		for (TextBookEntry textBookEntry : entryMap.values()) {
			if (parentValue.equals(textBookEntry.getParentValue())) {
				resultMap.put(textBookEntry.getTextBookValue(), textBookEntry);
			}
		}
		return resultMap;
	}

	public static List<TextBookEntry> getEntryList(String textBookTypeCode) {
		Map<String, TextBookEntry> entryMap = getEntryMap(textBookTypeCode);
		return getSortEntryList(entryMap);
	}

	public static List<TextBookEntry> getEntryList(TextBookParam textBookParam) {
		List<TextBookEntry> result = Lists.newArrayList();
		List<String> typeCodes = Arrays.asList(textBookParam.getTextBookTypeCode().split(","));
		for(String typeCode:typeCodes){
			textBookParam.setTextBookTypeCode(typeCode);
			List<TextBookEntry> textBookEntrys = getEntryList(textBookParam.getTextBookTypeCode());
			// type的所有relation
			List<TextBookRelation> textBookRelationList = getRelation(textBookParam.getTextBookTypeCode());
			Map<String, String> parentCodeMap = getParentCodeMap(textBookParam, textBookRelationList);
			getResult(textBookParam, textBookEntrys, parentCodeMap);
			Map<String, TextBookEntry> parentTextBookMap = new LinkedHashMap<>();
			for (TextBookEntry textBookEntry : textBookEntrys) {
				if (StringUtils.isEmpty(textBookEntry.getParentValue())) {
					parentTextBookMap.put(textBookEntry.getTextBookTypeCode() + textBookEntry.getTextBookValue(), textBookEntry);
				}
			}

			if (parentTextBookMap != null && parentTextBookMap.size() > 0) {
				for (TextBookEntry textBookEntry : textBookEntrys) {
					if (!StringUtils.isEmpty(textBookEntry.getParentValue())) {
						textBookEntry.getTextBookTypeCode();
						textBookEntry.getParentValue();
						parentTextBookMap.get(textBookEntry.getTextBookTypeCode() + textBookEntry.getParentValue()).getChildTextBookEntrys().add(textBookEntry);
					}
				}
			} else {
				for (TextBookEntry textBookEntry : textBookEntrys) {
					parentTextBookMap.put(textBookEntry.getTextBookTypeCode() + textBookEntry.getTextBookValue(), textBookEntry);
				}

			}
			result.addAll(getSortEntryList(parentTextBookMap));
		}
		return result;

	}

	public static List<TextBookEntry> getEntryList(String textBookTypeCode, String parentValue) {
		Map<String, TextBookEntry> entryMap = getEntryMap(textBookTypeCode, parentValue);
		Map<String, TextBookEntry> parentTextBookMap = new LinkedHashMap<>();
		for (TextBookEntry textBookEntry : entryMap.values()) {
			if (StringUtils.isEmpty(textBookEntry.getParentValue())) {
				parentTextBookMap.put(textBookEntry.getTextBookValue(), textBookEntry);
			}
		}

		if (parentTextBookMap != null && parentTextBookMap.size() > 0) {
			for (TextBookEntry textBookEntry : entryMap.values()) {
				if (!StringUtils.isEmpty(textBookEntry.getParentValue())) {
					parentTextBookMap.get(textBookEntry.getParentValue()).getChildTextBookEntrys().add(textBookEntry);
				}
			}
		} else {
			for (TextBookEntry textBookEntry : entryMap.values()) {
				parentTextBookMap.put(textBookEntry.getTextBookValue(), textBookEntry);
			}

		}
		return getSortEntryList(parentTextBookMap);
	}

	private static List<TextBookEntry> getSortEntryList(Map<String, TextBookEntry> entryMap) {
		List<TextBookEntry> entryList = Lists.newArrayList();
		for (TextBookEntry textBookEntry : entryMap.values()) {
			entryList.add(textBookEntry);
		}
		Collections.sort(entryList, new Comparator<TextBookEntry>() {
			public int compare(TextBookEntry o1, TextBookEntry o2) {
				if (o1.getSortNo() != null && o2.getSortNo() != null) {
					int result = o1.getSortNo().compareTo(o2.getSortNo());
					if (result != 0) {
						return result;
					}
				}
				return o1.getTextBookValue().compareTo(o2.getTextBookValue());
			}
		});
		return entryList;
	}

	public static String getEntryOptions(String textBookTypeCode) {
		List<TextBookEntry> entryList = getEntryList(textBookTypeCode);
		return getOptionsString(entryList);
	}

	public static String getEntryOptions(String textBookTypeCode, String parentValue) {
		List<TextBookEntry> entryList = getEntryList(textBookTypeCode, parentValue);
		return getOptionsString(entryList);
	}

	public static String getOptionsString(List<TextBookEntry> entryList) {
		StringBuffer entryOptions = new StringBuffer();
		for (TextBookEntry entry : entryList) {
			entryOptions.append("<option value='").append(entry.getTextBookValue()).append("'>");
			entryOptions.append(entry.getTextBookName()).append("</option>");
		}
		return entryOptions.toString();
	}

	public static String getEntryOptionsSelected(String textBookTypeCode, String defaultValue) {
		List<TextBookEntry> entryList = getEntryList(textBookTypeCode);
		return getOptionsSelectedString(defaultValue, entryList);
	}

	public static String getEntryOptionsSelected(String textBookTypeCode, String parentValue, String defaultValue) {
		List<TextBookEntry> entryList = getEntryList(textBookTypeCode, parentValue);
		return getOptionsSelectedString(defaultValue, entryList);
	}

	private static String getOptionsSelectedString(String defaultValue, List<TextBookEntry> entryList) {
		StringBuffer entryOptions = new StringBuffer();
		for (TextBookEntry entry : entryList) {
			entryOptions.append("<option value='").append(entry.getTextBookValue()).append("'");
			if (StringUtils.isNotEmpty(defaultValue) && defaultValue.equals(entry.getTextBookValue())) {
				entryOptions.append(" selected='selected' ");
			}
			entryOptions.append(">").append(entry.getTextBookName()).append("</option>");
		}
		return entryOptions.toString();
	}

	public static String getEntryName(String textBookTypeCode, String dictValue) {
		Map<String, TextBookEntry> entryMap = getEntryMap(textBookTypeCode);
		if (entryMap != null && entryMap.containsKey(dictValue)) {
			return entryMap.get(dictValue).getTextBookName();
		}
		return "";
	}

	public static String getEntryValue(String textBookTypeCode, String textBookEntryName) {
		Map<String, TextBookEntry> entryMap = getEntryMap(textBookTypeCode);
		if (entryMap != null) {
			for (TextBookEntry textBookEntry : entryMap.values()) {
				if (textBookEntry.getTextBookName().equals(textBookEntryName)) {
					return textBookEntry.getTextBookValue();
				}
			}
		}
		return "";
	}
	
	public static String getEntryValue(TextBookParam textBookParam) {
		String result = "";
		if (StringUtils.isNotEmpty(textBookParam.getSubject()) && StringUtils.isNotEmpty(textBookParam.getStage()) && StringUtils.isNotEmpty(textBookParam.getGrade()) && StringUtils.isNotEmpty(textBookParam.getVersion()) &&StringUtils.isNotEmpty(textBookParam.getSection())) {
			List<TextBookEntry> textBookEntrys = getEntryList(textBookParam);
			for(TextBookEntry tb:textBookEntrys){
				if(tb.getTextBookName().equals(textBookParam.getTextBookName())){
					result = tb.getTextBookValue(); 
				}
			}
		}else{
			result =  getEntryValue(textBookParam.getTextBookTypeCode(),textBookParam.getTextBookName());
		}
		return result;
	}

	public static TextBookEntry getEntry(String textBookTypeCode, String dictValue) {
		Map<String, TextBookEntry> entryMap = getEntryMap(textBookTypeCode);
		if (entryMap != null && entryMap.containsKey(dictValue)) {
			return entryMap.get(dictValue);
		}
		return null;
	}

	private static void getResult(TextBookParam textBookParam, List<TextBookEntry> textBookEntrys, Map<String, String> parentCodeMap) {

		for (int i = textBookEntrys.size() - 1; i >= 0; i--) {
			TextBookEntry tmp = textBookEntrys.get(i);
			// stage
			if (!StringUtils.isEmpty(textBookParam.getStage())) {
				if (parentCodeMap.get(tmp.getId()) == null || !parentCodeMap.get(tmp.getId()).contains("{\"stage\":" + textBookParam.getStage() + "}")) {
					textBookEntrys.remove(i);
					continue;
				}
			}
			// subject
			if (!StringUtils.isEmpty(textBookParam.getSubject())) {
				if (parentCodeMap.get(tmp.getId()) == null || !parentCodeMap.get(tmp.getId()).contains("{\"subject\":" + textBookParam.getSubject() + "}")) {
					textBookEntrys.remove(i);
					continue;
				}
			}

			// grade
			if (!StringUtils.isEmpty(textBookParam.getGrade())) {
				if (parentCodeMap.get(tmp.getId()) == null || !parentCodeMap.get(tmp.getId()).contains("{\"grade\":" + textBookParam.getGrade() + "}")) {
					textBookEntrys.remove(i);
					continue;
				}
			}

			// version
			if (!StringUtils.isEmpty(textBookParam.getVersion())) {
				if (parentCodeMap.get(tmp.getId()) == null || !parentCodeMap.get(tmp.getId()).contains("{\"version\":" + textBookParam.getVersion() + "}")) {
					textBookEntrys.remove(i);
					continue;
				}
			}

			// section
			if (!StringUtils.isEmpty(textBookParam.getSection())) {
				if (parentCodeMap.get(tmp.getId()) == null || !parentCodeMap.get(tmp.getId()).contains("{\"section\":" + textBookParam.getSection() + "}")) {
					textBookEntrys.remove(i);
					continue;
				}
			}

		}

	}

	private static Map<String, String> getParentCodeMap(TextBookParam textBookParam, List<TextBookRelation> textBookRelationList) {
		Map<String, String> parentCodeMap = new HashMap<String, String>();

		// 根据条件筛选relation
		for (int i = textBookRelationList.size() - 1; i >= 0; i--) {
			TextBookRelation tmp = textBookRelationList.get(i);
			// Map<String, String> parentCodeMap = TextBookUtils.getParentCode(tmp.getId(),textBookRelationMap);
			// stage
			if (!StringUtils.isEmpty(textBookParam.getStage())) {
				String a = "{\"stage\":" + textBookParam.getStage() + "}";
				String b = tmp.getParentCode();
				if (!tmp.getParentCode().contains("{\"stage\":" + textBookParam.getStage() + "}")) {
					textBookRelationList.remove(i);
					continue;
				}
			}
			// subject
			if (!StringUtils.isEmpty(textBookParam.getSubject())) {
				if (!tmp.getParentCode().contains("{\"subject\":" + textBookParam.getSubject() + "}")) {
					textBookRelationList.remove(i);
					continue;
				}
			}

			// grade
			if (!StringUtils.isEmpty(textBookParam.getGrade())) {
				if (!tmp.getParentCode().contains("{\"grade\":" + textBookParam.getGrade() + "}")) {
					textBookRelationList.remove(i);
					continue;
				}
			}

			// version
			if (!StringUtils.isEmpty(textBookParam.getVersion())) {
				if (!tmp.getParentCode().contains("{\"version\":" + textBookParam.getVersion() + "}")) {
					textBookRelationList.remove(i);
					continue;
				}
			}

			// section
			if (!StringUtils.isEmpty(textBookParam.getSection())) {
				if (!tmp.getParentCode().contains("{\"section\":" + textBookParam.getSection() + "}")) {
					textBookRelationList.remove(i);
					continue;
				}
			}

		}

		// 筛选完后组装成map
		for (TextBookRelation tr : textBookRelationList) {
			String[] parentCodes = tr.getParentCode().split(",");
			for (int i = 0; i < parentCodes.length; i++) {
				if (StringUtils.isEmpty(parentCodeMap.get(tr.getTextBookEntryId()))) {
					parentCodeMap.put(tr.getTextBookEntryId(), parentCodes[i]);
				} else {
					if (!parentCodeMap.get(tr.getTextBookEntryId()).contains(parentCodes[i])) {
						parentCodeMap.put(tr.getTextBookEntryId(), parentCodeMap.get(tr.getTextBookEntryId()) + "," + parentCodes[i]);
					}
				}
			}
		}

		return parentCodeMap;

	}
	
	public static void main(String[] args) {
		initAll();
	}
	
}
