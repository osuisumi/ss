package com.haoyu.search.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.apache.lucene.document.Document;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class SearchResultBean implements Serializable{
	
	private static final long serialVersionUID = -6920713829447655115L;

	private int totalCount;	//检索总条数
	
	private List<Document> documents = Lists.newArrayList();	//检索记录集合
	
	private Map<String, String> highLightValues = Maps.newHashMap();	//高亮字段值
	
	public SearchResultBean(int totalCount, List<Document> documents, Map<String, String> highLightValues){
		this.totalCount = totalCount;
		this.documents = documents;
		this.highLightValues = highLightValues;
	}

	public Map<String, String> getHighLightValues() {
		return highLightValues;
	}

	public void setHighLightValues(Map<String, String> highLightValues) {
		this.highLightValues = highLightValues;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public List<Document> getDocuments() {
		return documents;
	}

	public void setDocuments(List<Document> documents) {
		this.documents = documents;
	}
	
}
