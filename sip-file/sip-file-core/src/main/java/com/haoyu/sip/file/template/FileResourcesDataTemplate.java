package com.haoyu.sip.file.template;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.github.miemiedev.mybatis.paginator.domain.Order;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.haoyu.sip.core.web.SearchParam;
import com.haoyu.sip.file.entity.FileResource;
import com.haoyu.sip.file.service.IFileResourceService;

import freemarker.core.Environment;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateScalarModel;
@Component
public class FileResourcesDataTemplate implements TemplateDirectiveModel{
	@Resource
	private IFileResourceService fileResourceService;

	@Override
	public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
		PageBounds pageBounds = null;
		if (params.containsKey("page")  && params.get("page") != null) {
			pageBounds = new PageBounds();
			String page = params.get("page").toString();
			pageBounds.setPage(Integer.valueOf(page));
		}
		if (params.containsKey("limit")  && params.get("limit") != null) {
			String limit = params.get("limit").toString();
			pageBounds.setLimit(Integer.valueOf(limit));
			pageBounds.setContainsTotalCount(true);
		}
		if (params.containsKey("orders")  && params.get("orders") != null) {
			String orders = params.get("orders").toString();
			pageBounds.setOrders(Order.formString(orders));
		}
		SearchParam searchParam = new SearchParam();
		Map<String, Object> param = searchParam.getParamMap();
		if (params.containsKey("relationId") &&StringUtils.isNotEmpty(((TemplateScalarModel) params.get("relationId")).getAsString()) ) {
			param.put("relationId", params.get("relationId").toString());
			if(params.containsKey("parentId") &&StringUtils.isNotEmpty(((TemplateScalarModel) params.get("parentId")).getAsString()) ){
				param.put("parentId",params.get("parentId").toString());
			}
			if(params.containsKey("creator") &&StringUtils.isNotEmpty(((TemplateScalarModel) params.get("creator")).getAsString()) ){
				param.put("creator",params.get("creator").toString());
			}
			if(params.containsKey("isFolder") &&StringUtils.isNotEmpty(((TemplateScalarModel) params.get("isFolder")).getAsString()) ){
				param.put("isFolder",params.get("isFolder").toString());
			}
			if(params.containsKey("type") && StringUtils.isNotEmpty(((TemplateScalarModel) params.get("type")).getAsString())){
				param.put("type",params.get("type").toString());
			}
			List<FileResource> fileResources = fileResourceService.list(searchParam, pageBounds);
			env.setVariable("fileResources", new DefaultObjectWrapper().wrap(fileResources));
			if (pageBounds != null && pageBounds.isContainsTotalCount()) {
				PageList pageList = (PageList)fileResources;
				env.setVariable("paginator" , new DefaultObjectWrapper().wrap(pageList.getPaginator()));
			}
		}
		body.render(env.getOut());
		
	}

}
