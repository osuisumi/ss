package com.haoyu.search.utils;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wltea.analyzer.lucene.IKAnalyzer;

import com.haoyu.sip.core.utils.PropertiesLoader;

public class IndexUtils {
	
	private static Logger logger = LoggerFactory.getLogger(IndexUtils.class);
	
	private static Object lock = new Object();

	/**
	 * 初始化索引或增量修改索引
	 * @param fields 索引的文档集合. 一个map相当于一个document, 可以理解成数据库中的一条记录, map的key为字段名, value为字段值. map的list即是多条记录, 转换成多个document, 并依此加入索引中
	 * @param cover 是否覆盖. 如为true则修改前会删除原索引. 如为false则为增量修改.
	 */
	public static  void updateIndex(List<Map<String, Object>> fields, boolean cover) {
		Analyzer analyzer = new IKAnalyzer();
		IndexWriterConfig iwConfig = new IndexWriterConfig(Version.LATEST, analyzer);
		iwConfig.setOpenMode(IndexWriterConfig.OpenMode.CREATE_OR_APPEND);
		File indexDir = new File(PropertiesLoader.get("lucene.index.dir"));
		IndexWriter indexWriter = null;
		try {
			Directory fsDirectory = FSDirectory.open(indexDir);
			if (IndexWriter.isLocked(fsDirectory)) {
				IndexWriter.unlock(fsDirectory);
			}
			synchronized(lock){
				indexWriter = new IndexWriter(fsDirectory, iwConfig);
				if (cover) {
					indexWriter.deleteAll();
				}
				for (Map<String, Object> map : fields) {
					Document doc = new Document();
					for (String key : map.keySet()) {
						TextField postIdField = new TextField(key, (String) map.get(key), Store.YES);
						doc.add(postIdField);
					}
					indexWriter.addDocument(doc);
				}
				indexWriter.close();
			}
		} catch (IOException e) {
			logger.error(e.getClass() + "---" + e.getMessage());
			e.printStackTrace();
		}finally{
			if(indexWriter != null){
				try {
					indexWriter.close();
				} catch (IOException e) {
					e.printStackTrace();
					logger.error(e.getMessage());
				}
			}
		}
	}
	
	public static void deleteDocuments(List<String> ids){
		Analyzer analyzer = new IKAnalyzer();
		IndexWriterConfig iwConfig = new IndexWriterConfig(Version.LATEST, analyzer);
		iwConfig.setOpenMode(IndexWriterConfig.OpenMode.CREATE_OR_APPEND);
		File indexDir = new File(PropertiesLoader.get("lucene.index.dir"));
		try {
			Directory fsDirectory = FSDirectory.open(indexDir);
			synchronized(lock){
				IndexWriter indexWriter = new IndexWriter(fsDirectory, iwConfig);
				for (String id : ids) {
					BooleanQuery query = new BooleanQuery();
					QueryParser qp = new QueryParser(Version.LATEST, "id", analyzer);
					qp.setDefaultOperator(QueryParser.AND_OPERATOR);
					try {
						query.add(qp.parse(id), BooleanClause.Occur.MUST);
						indexWriter.deleteDocuments(query);
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}
				indexWriter.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
