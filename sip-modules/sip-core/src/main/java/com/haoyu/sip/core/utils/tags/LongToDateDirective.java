package com.haoyu.sip.core.utils.tags;

import java.io.IOException;
import java.io.Writer;
import java.util.Date;
import java.util.Map;

import com.haoyu.sip.utils.TimeUtils;

import freemarker.core.Environment;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.SimpleScalar;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateNumberModel;

/**
 * freemarker .ftl文件中所使用的标签处理程序
 * 用于格式化时间类型为long的时间
 * @author lianghuahuang
 */
public class LongToDateDirective implements TemplateDirectiveModel {


	public void execute(Environment env, @SuppressWarnings("rawtypes") Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		String timeString = "";
		if(params.containsKey("longtime")){
			TemplateModel paramValue = (TemplateModel)params.get("longtime");
			long longtime = Long.parseLong(((SimpleScalar)paramValue).getAsString());
			String pattern = "yyyy-MM-dd";
			if(params.containsKey("pattern")){
				paramValue = (TemplateModel)params.get("pattern");
				pattern = ((SimpleScalar)paramValue).getAsString();
			}
			timeString = TimeUtils.formatDate(longtime, pattern);
		}
		env.setVariable("date",  new DefaultObjectWrapper().wrap(timeString));
		body.render(env.getOut()); 
	}
	
}