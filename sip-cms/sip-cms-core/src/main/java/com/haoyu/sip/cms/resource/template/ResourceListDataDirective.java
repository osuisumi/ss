package com.haoyu.sip.cms.resource.template;

import java.io.IOException;
import java.util.List;
import java.util.Map;






import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.google.common.collect.Maps;
import com.haoyu.sip.cms.resource.entity.Resource;
import com.haoyu.sip.cms.resource.service.IResourceService;

import freemarker.core.Environment;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.SimpleScalar;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
/**
 * freemarker .ftl文件中所使用的标签处理程序
 * 用于从后台获取相对应的数据
 * 参数有
 * page 第几页 1 缺省为 1
 * rp 行数  9	缺省为10
 * catalogId 查找所有在该栏目下的文章  不可少
 * @author huangqunyan
 *
 */
@Component
public class ResourceListDataDirective implements TemplateDirectiveModel {
	
	@javax.annotation.Resource
	private IResourceService resourceService;

	public void execute(Environment env, @SuppressWarnings("rawtypes") Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		int page = 1;
		int size = 10;
		PageBounds pageBounds = null;
		StringBuffer cacheKeyBuffer = new StringBuffer();
		if(params.containsKey("page")){
			String pageStr = ((SimpleScalar)params.get("page")).getAsString();
			if(StringUtils.isEmpty(pageStr))
				pageStr = "1";
			page = Integer.parseInt(pageStr);
			pageBounds = new PageBounds(page,size);
		}
		if(params.containsKey("size")){
			size = Integer.parseInt(((SimpleScalar)params.get("size")).getAsString());
			pageBounds = new PageBounds(page,size);
		}	
		Map<String,Object> parameter = Maps.newHashMap();	
		if(params.containsKey("name")&&params.get("name")!=null){
			String name = ((SimpleScalar)params.get("name")).getAsString();
			parameter.put("name", name);
			cacheKeyBuffer.append(name);
		}
		
		if(params.containsKey("type")&&params.get("type")!=null){
			String type = ((SimpleScalar)params.get("type")).getAsString();
			parameter.put("type", type);
			cacheKeyBuffer.append(type);
		}
		
		if(params.containsKey("relationId")&&params.get("relationId")!=null){
			String relationId = ((SimpleScalar)params.get("relationId")).getAsString();
			parameter.put("relationId", relationId);
			cacheKeyBuffer.append(relationId);
		}
		parameter.put("cacheKey", DigestUtils.md5Hex(cacheKeyBuffer.toString()+pageBounds.getLimit()+pageBounds.getPage()));
		List<Resource> resourceList =resourceService.findResources(parameter, pageBounds);
		PageList pageList = (PageList)resourceList;
		env.setVariable("resourceList",  new DefaultObjectWrapper().wrap(resourceList));
		env.setVariable("paginator" , new DefaultObjectWrapper().wrap(pageList.getPaginator()));
		body.render(env.getOut());  
	}
	
}