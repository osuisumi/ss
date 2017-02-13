package com.haoyu.sip.cms.recommend.template;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


















import javax.annotation.Resource;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.github.miemiedev.mybatis.paginator.domain.Order;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.haoyu.sip.cms.channel.entity.Channel;
import com.haoyu.sip.cms.channel.service.IChannelService;
import com.haoyu.sip.cms.recommend.entity.Recommend;
import com.haoyu.sip.cms.recommend.entity.RecommendCourse;
import com.haoyu.sip.cms.recommend.service.IRecommendCourseService;
import com.haoyu.sip.core.utils.ThreadContext;

import freemarker.core.Environment;
import freemarker.ext.beans.BeanModel;
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
public class RecommendCourseListDataDirective implements TemplateDirectiveModel {
	
	@Resource
	private IRecommendCourseService recommendCourseService;
	
	@Resource
	private IChannelService channelService;

	public void execute(Environment env, @SuppressWarnings("rawtypes") Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		PageBounds pageBounds = null;
		Map<String,Object> parameter = Maps.newHashMap();
		StringBuffer cacheKeyBuffer = new StringBuffer();
		if (params.containsKey("recommendCourse")&&params.get("recommendCourse")!=null) {
			BeanModel beanModel = (BeanModel) params.get("recommendCourse");
			RecommendCourse recommendCourse = (RecommendCourse)beanModel.getWrappedObject();
			parameter.put("recommendCourse", recommendCourse);
		}
		if(params.containsKey("courseName")&&params.get("courseName")!=null){
			String courseName = ((SimpleScalar)params.get("courseName")).getAsString();
			parameter.put("courseName",courseName);
			cacheKeyBuffer.append(courseName);
		}
		if(params.containsKey("channelAlias")&&params.get("channelAlias")!=null){
			String channelAlias = ((SimpleScalar)params.get("channelAlias")).getAsString();
			Channel channel = channelService.findChannelByAlias(channelAlias,ThreadContext.getDomain());
			parameter.put("channel",channel);
			env.setVariable("channelId", new DefaultObjectWrapper().wrap(channel.getId()));
			cacheKeyBuffer.append(channelAlias);
		}
		
		if (params.containsKey("pageBounds")&&params.get("pageBounds")!=null) {
			BeanModel beanModel = (BeanModel) params.get("pageBounds");
			pageBounds = (PageBounds)beanModel.getWrappedObject();
			pageBounds.setOrders(Order.formString("ORDER_NO"));
		}else{
			int page = 1;
			int size = 10;
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
			
		}
		parameter.put("cacheKey", DigestUtils.md5Hex(cacheKeyBuffer.toString()+pageBounds.getLimit()+pageBounds.getPage()));
		List<RecommendCourse> recommendCourseList = recommendCourseService.findRecommendCourses(parameter,pageBounds);
		env.setVariable("recommendCourseList", new DefaultObjectWrapper().wrap(recommendCourseList));
		if (pageBounds != null) {
			PageList pageList = (PageList)recommendCourseList;
			env.setVariable("paginator" , new DefaultObjectWrapper().wrap(pageList.getPaginator()));
		}
		body.render(env.getOut());  
	}
	
}