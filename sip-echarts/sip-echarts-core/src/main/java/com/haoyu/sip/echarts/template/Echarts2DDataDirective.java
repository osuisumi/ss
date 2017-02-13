package com.haoyu.sip.echarts.template;

import java.io.IOException;
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
import com.haoyu.sip.core.mapper.JsonMapper;
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
 * 二维统计数据处理程序
 * 参数：echartsId(统计图ID)，requestParam(统计图附带的请求地址参数)
 * @author lianghuahuang
 *
 */
@Component
public class Echarts2DDataDirective implements TemplateDirectiveModel {
	
	@Resource
	private IStatDataValueService statDataValueService;

	public void execute(Environment env, @SuppressWarnings("rawtypes") Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		StatChartsParam scp = new StatChartsParam();	
		
		if(params.containsKey("echartsId")){
			String chartsId = ((SimpleScalar)params.get("echartsId")).getAsString();
			scp.setStatCharts(new StatCharts(chartsId));
		}else{
			return;			
		}
		
		if(params.containsKey("requestParam")){
			String requestParam = ((SimpleScalar)params.get("requestParam")).getAsString();
			scp.setRequestParam(requestParam);
		}else{
			return;
		}
		
		Map<String,Float> statDataValueMap =statDataValueService.findStatDataValueMap(scp);
		if(statDataValueMap!=null&&!statDataValueMap.isEmpty()){
			if(params.containsKey("xAxisData")){
				String xAxisDataStr = ((SimpleScalar)params.get("xAxisData")).getAsString();
				JsonMapper jsonMapper = new JsonMapper();
				String[] xAxisDataArray = jsonMapper.fromJson(xAxisDataStr, String[].class);
				Float[] seriesDataArray  = new Float[xAxisDataArray.length];
				for(int i=0;i<xAxisDataArray.length;i++){
					String xd = xAxisDataArray[i];
					seriesDataArray[i] = statDataValueMap.getOrDefault(xd, 0f);
				}
				env.setVariable("seriesData",  new DefaultObjectWrapper().wrap(jsonMapper.toJson(seriesDataArray)));
			}else{
				Float[] seriesDataArray  = new Float[statDataValueMap.size()];
				String[] xAxisDataArray = new String[statDataValueMap.size()];
				int i=0;
				for (Map.Entry<String, Float> m : statDataValueMap.entrySet()) { 
					xAxisDataArray[i] = m.getKey();
					seriesDataArray[i] = m.getValue();
					i++;
				}		
				JsonMapper jsonMapper = new JsonMapper();
				env.setVariable("seriesData",  new DefaultObjectWrapper().wrap(jsonMapper.toJson(seriesDataArray)));
				env.setVariable("xAxisData",  new DefaultObjectWrapper().wrap(jsonMapper.toJson(xAxisDataArray)));
				env.setVariable("statDataValueMap",new DefaultObjectWrapper().wrap(statDataValueMap));
			}		
			body.render(env.getOut());  
		}
	}
	
}