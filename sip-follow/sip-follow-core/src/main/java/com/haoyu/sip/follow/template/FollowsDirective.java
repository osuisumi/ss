package com.haoyu.sip.follow.template;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.haoyu.sip.core.freemarker.AbstractTemplateDirectiveModel;
import com.haoyu.sip.follow.entity.Follow;
import com.haoyu.sip.follow.service.IFollowService;

import freemarker.core.Environment;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

@Component
public class FollowsDirective extends AbstractTemplateDirectiveModel{

	@Resource
	private IFollowService followService;
	
	@Override
	public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
		Map<String,Object> parameter = getSelectParam(params);
		PageBounds pageBounds = getPageBounds(params);
		List<Follow> follows = followService.listFollow(parameter, pageBounds);
		env.setVariable("follows", new DefaultObjectWrapper().wrap(follows));
		if(pageBounds != null && pageBounds.isContainsTotalCount()){
			PageList pageList = (PageList)follows;
			env.setVariable("paginator" , new DefaultObjectWrapper().wrap(pageList.getPaginator()));
		}
		body.render(env.getOut());
	}
	
}
