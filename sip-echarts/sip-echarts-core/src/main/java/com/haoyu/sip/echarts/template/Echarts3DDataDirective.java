package com.haoyu.sip.echarts.template;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.google.common.collect.Maps;
import com.google.common.collect.Ordering;
import com.google.common.collect.Table;
import com.haoyu.sip.core.mapper.JsonMapper;
import com.haoyu.sip.echarts.entity.EchartsSeries;
import com.haoyu.sip.echarts.entity.StatCharts;
import com.haoyu.sip.echarts.entity.StatChartsParam;
import com.haoyu.sip.echarts.service.IStatDataValueService;

import freemarker.core.Environment;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.SimpleScalar;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

/**
 * 
 * 三维统计数据处理程序 参数：echartsId(统计图ID)，requestParam(统计图附带的请求地址参数),
 * xAxisData(echarts横坐标坐标点
 * ,不传时则通过统计数据计算并按照rowFieldName排序),legendData(echarts图例说明,
 * 不传时则按照统计数据计算并按照columnFieldName排序)
 * 
 * @author lianghuahuang
 *
 */
@Component
public class Echarts3DDataDirective implements TemplateDirectiveModel {

	@Resource
	private IStatDataValueService statDataValueService;

	public void execute(Environment env,
			@SuppressWarnings("rawtypes") Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		StatChartsParam scp = new StatChartsParam();
		if (params.containsKey("echartsId")) {
			String chartsId = ((SimpleScalar) params.get("echartsId"))
					.getAsString();
			scp.setStatCharts(new StatCharts(chartsId));
		} else {
			return;
		}

		if (params.containsKey("requestParam")) {
			String requestParam = ((SimpleScalar) params.get("requestParam"))
					.getAsString();
			scp.setRequestParam(requestParam);
		} else {
			return;
		}

		Table<String, String, Float> statDataValueTable = statDataValueService
				.findStatDataValueTable(scp);

		if (statDataValueTable != null && !statDataValueTable.isEmpty()) {
			String[] xAxisDataArray = null;
			JsonMapper jsonMapper = new JsonMapper();
			// 获取横坐标坐标点名称
			if (params.containsKey("xAxisData")) {
				String xAxisDataStr = ((SimpleScalar) params.get("xAxisData"))
						.getAsString();
				xAxisDataArray = jsonMapper.fromJson(xAxisDataStr,
						String[].class);
			} else {
				// 根据统计数据获取横坐标点坐标名称，通过获取table中的行字段名来设置，并对名称进行排序
				Set<String> xAxisSets = statDataValueTable.rowKeySet();
				Ordering<Object> usingToStringOrdering = Ordering
						.usingToString();
				List<String> sortedXAxisLists = usingToStringOrdering
						.sortedCopy(xAxisSets);
				xAxisDataArray = sortedXAxisLists.toArray(new String[xAxisSets
						.size()]);
				env.setVariable("xAxisData", new DefaultObjectWrapper()
						.wrap(jsonMapper.toJson(xAxisDataArray)));
			}

			String[] legendDataArray = null;
			// 获取图例名称
			if (params.containsKey("legendData")) {
				String legendDataStr = ((SimpleScalar) params.get("legendData"))
						.getAsString();
				legendDataArray = jsonMapper.fromJson(legendDataStr,
						String[].class);
			} else {
				// 根据统计数据获取图例名称，通过获取table中的列字段名来设置，并对名称进行排序
				Set<String> legendSets = statDataValueTable.columnKeySet();
				Ordering<Object> usingToStringOrdering = Ordering
						.usingToString();
				List<String> sortedLegendLists = usingToStringOrdering
						.sortedCopy(legendSets);
				legendDataArray = sortedLegendLists
						.toArray(new String[legendSets.size()]);
				env.setVariable("legendData", new DefaultObjectWrapper()
						.wrap(jsonMapper.toJson(legendDataArray)));
			}

			// 按照图例名称顺序来封装分组数据
			EchartsSeries[] esArray = new EchartsSeries[legendDataArray.length];
			for (int i = 0; i < legendDataArray.length; i++) {
				String ld = legendDataArray[i];
				EchartsSeries es = new EchartsSeries();
				es.setName(ld);
				StringBuffer data = new StringBuffer("[");
				//当查询结果中包含指定图例名称时对该数据值进行封装
				if (statDataValueTable.containsColumn(ld)) {
					Map<String, Float> columnMap = statDataValueTable.column(ld);
					//根据横坐标点名称顺序封装对应的数据值，如果取值为空，则默认为0,封装类型为float类型的数组json字符串
					for (int j = 0; j < xAxisDataArray.length; j++) {
						if (columnMap.containsKey(xAxisDataArray[j])) {
							data.append(columnMap.getOrDefault(xAxisDataArray[j],0f));
						} else {
							data.append(0);
						}
						if (j < xAxisDataArray.length - 1) {
							data.append(",");
						}
					}

				} else {
					//返回结果中不包含图例数据值，将数据封装为0
					for (int j = 0; j < xAxisDataArray.length; j++) {
						data.append(0);
						if (j < xAxisDataArray.length - 1) {
							data.append(",");
						}
					}
				}
				data.append("]");
				es.setData(data.toString());
				esArray[i] = es;
			}
			env.setVariable("series", new DefaultObjectWrapper().wrap(esArray));
			body.render(env.getOut());
		}
	}
}