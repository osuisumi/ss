package com.haoyu.sip.point.template;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.haoyu.sip.core.freemarker.AbstractTemplateDirectiveModel;
import com.haoyu.sip.point.entity.PointStrategy;
import com.haoyu.sip.point.service.IPointStrategyService;

import freemarker.core.Environment;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

@Component
public class PointStrategysDirective extends AbstractTemplateDirectiveModel {
	@Resource
	private IPointStrategyService pointStrategyService;

	@Override
	public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
		Map<String, Object> parameter = getSelectParam(params);
		PageBounds pageBounds = getPageBounds(params);
		List<PointStrategy> pointStrategys = pointStrategyService.findPointStrategys(parameter, pageBounds);
		env.setVariable("pointStrategys", new DefaultObjectWrapper().wrap(pointStrategys));
		if (pageBounds != null && pageBounds.isContainsTotalCount()) {
			PageList pageList = (PageList) pointStrategys;
			env.setVariable("paginator", new DefaultObjectWrapper().wrap(pageList.getPaginator()));
		}
		body.render(env.getOut());

	}

}
