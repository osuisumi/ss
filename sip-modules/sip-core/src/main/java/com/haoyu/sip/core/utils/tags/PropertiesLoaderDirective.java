package com.haoyu.sip.core.utils.tags;

import java.io.IOException;
import java.io.Writer;
import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;

import com.haoyu.sip.core.utils.PropertiesLoader;
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
public class PropertiesLoaderDirective implements TemplateDirectiveModel {

	@Resource
	private PropertiesLoader propertiesLoader;

	public void execute(Environment env, @SuppressWarnings("rawtypes") Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		if(params.containsKey("key")){
			TemplateModel paramValue = (TemplateModel)params.get("key");
			String key = ((SimpleScalar)paramValue).getAsString();
			env.setVariable("propValue",  new DefaultObjectWrapper().wrap(propertiesLoader.getProperty(key)));
			body.render(env.getOut()); 
		}
		
	}
	
}