package com.haoyu.search.utils;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.TopFieldCollector;
import org.apache.lucene.search.highlight.Formatter;
import org.apache.lucene.search.highlight.Fragmenter;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.InvalidTokenOffsetsException;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.Scorer;
import org.apache.lucene.search.highlight.SimpleFragmenter;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.wltea.analyzer.lucene.IKAnalyzer;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.haoyu.search.entity.QueryField;
import com.haoyu.search.entity.SearchResultBean;
import com.haoyu.sip.core.utils.PropertiesLoader;
import com.haoyu.sip.utils.Collections3;

public class SearchUtils {

	/**
	 * 全文检索
	 * @param queryFields	检索条件, 多个取交集, 即为and
	 * @param page	分页参数, 当前页
	 * @param limit	分页参数, 每页条数
	 * @param highLight	是否设置高亮
	 * @param highLightField	高亮字段, 暂时只支持一个字段	
	 * @param startHtml	高亮样式标签(前)
	 * @param endHtml	高亮样式标签(后)
	 * @return
	 */
	public static SearchResultBean searchDocument(List<QueryField> queryFields, int page, int limit, boolean highLight, String highLightField, String startHtml, String endHtml) {
		try {
			//IKAnalyzer为分词工具
			Analyzer analyzer = new IKAnalyzer();
			//找到索引所在目录, 并打开索引检索工具
			File indexDir = new File(PropertiesLoader.get("lucene.index.dir"));
			Directory fsDirectory = FSDirectory.open(indexDir);
			DirectoryReader ireader = DirectoryReader.open(fsDirectory);
			IndexSearcher isearcher = new IndexSearcher(ireader);
			//设定检索条件
			BooleanQuery booleanQuery = new BooleanQuery();
			if (Collections3.isNotEmpty(queryFields)) {
				for (QueryField queryField : queryFields) {
					if (StringUtils.isNotEmpty(queryField.getValue())) {
						//此处默认使用多字段检索. 参数String数组中若只有一个值,会自动转成单字段检索 
						QueryParser qp = new MultiFieldQueryParser(Version.LATEST, queryField.getFields(), analyzer);
						qp.setDefaultOperator(QueryParser.AND_OPERATOR);
						booleanQuery.add(qp.parse(queryField.getValue()), BooleanClause.Occur.MUST);
					}
				}
			}
			//设定分页参数
			int start = (page - 1) * limit;
			TopFieldCollector c = TopFieldCollector.create(new Sort(), start + limit, false, false, false, false);
			//执行检索
			isearcher.search(booleanQuery, c);
			ScoreDoc[] scoreDocs = c.topDocs(start, limit).scoreDocs;
			
			//设置高亮参数
			Formatter htmlFormatter = null;
			if (highLight){
				htmlFormatter = new SimpleHTMLFormatter(startHtml, endHtml);
			}else{
				htmlFormatter = new SimpleHTMLFormatter("", "");
			}
			Scorer scorer = new QueryScorer(booleanQuery);
			Fragmenter fragmenter = new SimpleFragmenter(10);
			Highlighter highlighter = new Highlighter(htmlFormatter, scorer);
			highlighter.setTextFragmenter(fragmenter);
			
			List<Document> documents = Lists.newArrayList();
			Map<String, String> highLightValues = Maps.newHashMap();
			//遍历检索结果, 组装好返回值
			for (int i = 0; i < scoreDocs.length; i++) {
				Document doc = isearcher.doc(scoreDocs[i].doc);
				//将高亮字段修改成高亮效果, 并放到highLightValues中
				TokenStream tokenStream = analyzer.tokenStream(highLightField, new StringReader(doc.get(highLightField)));  
				String hightLightValue = highlighter.getBestFragment(tokenStream,  doc.get(highLightField));
				if (StringUtils.isNotEmpty(hightLightValue)) {
					highLightValues.put(doc.get("id"), hightLightValue);
				}
				documents.add(doc);
			}
			//将检索总条数, 检索记录集合, 高亮字段值三者封装并返回
			return new SearchResultBean(c.getTotalHits(), documents, highLightValues);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (InvalidTokenOffsetsException e) {
			e.printStackTrace();
		}
		return null;
	}
}
