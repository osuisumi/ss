package com.haoyu.sip.file.template;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.haoyu.sip.core.freemarker.AbstractTemplateDirectiveModel;
import com.haoyu.sip.file.entity.FileInfo;
import com.haoyu.sip.file.service.IFileInfoService;

import freemarker.core.Environment;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

@Component
public class FilesDirective extends AbstractTemplateDirectiveModel{

	@Resource
	private IFileInfoService fileInfoService;
	
	@Override
	public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
		Map<String,Object> parameter = getSelectParam(params);
		PageBounds pageBounds = getPageBounds(params);
		List<FileInfo> fileInfos = fileInfoService.listFileInfo(parameter, pageBounds);
		env.setVariable("fileInfos", new DefaultObjectWrapper().wrap(fileInfos));
		if(pageBounds != null &&pageBounds.isContainsTotalCount()){
			PageList pageList = (PageList)fileInfos;
			env.setVariable("paginator" , new DefaultObjectWrapper().wrap(pageList.getPaginator()));
		}
		body.render(env.getOut());
	}
	
}
