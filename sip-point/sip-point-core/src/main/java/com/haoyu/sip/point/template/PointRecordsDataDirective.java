package com.haoyu.sip.point.template;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.haoyu.sip.core.freemarker.AbstractTemplateDirectiveModel;
import com.haoyu.sip.point.entity.PointRecord;
import com.haoyu.sip.point.service.IPointRecordService;

import freemarker.core.Environment;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

@Component
public class PointRecordsDataDirective extends AbstractTemplateDirectiveModel{
	@Resource
	private IPointRecordService pointRecordService;
	
	@Override
	public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
		PageBounds pageBounds = getPageBounds(params);
		Map<String,Object> parameter = getSelectParam(params);
		
		List<PointRecord> pointRecords =  pointRecordService.findPointRecords(parameter, pageBounds);
		env.setVariable("pointRecords", new DefaultObjectWrapper().wrap(pointRecords));
		if(pageBounds!=null && pageBounds.isContainsTotalCount()){
			PageList pageList = (PageList) pointRecords;
			env.setVariable("paginator", new DefaultObjectWrapper().wrap(pageList.getPaginator()));
		}
		
		body.render(env.getOut());
		
	}

}
