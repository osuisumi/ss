package com.haoyu.sip.sign.template;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.haoyu.sip.core.freemarker.AbstractTemplateDirectiveModel;
import com.haoyu.sip.sign.entity.SignStat;
import com.haoyu.sip.sign.service.ISignStatService;

import freemarker.core.Environment;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

@Component
public class SignStatRankDirective extends AbstractTemplateDirectiveModel{

	@Resource
	private ISignStatService signStatService;

	public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
		Map<String,Object> parameter = getSelectParam(params);
		PageBounds pageBounds  = getPageBounds(params);
		if(parameter.containsKey("relationId")){
			List<SignStat> signStats = signStatService.listSignStatRank(parameter, pageBounds);
			env.setVariable("signStats", new DefaultObjectWrapper().wrap(signStats));
			if (pageBounds != null && pageBounds.isContainsTotalCount()) {
				PageList pageList = (PageList) signStats;
				env.setVariable("paginator", new DefaultObjectWrapper().wrap(pageList.getPaginator()));
			}
		}
		body.render(env.getOut());
	}

}
