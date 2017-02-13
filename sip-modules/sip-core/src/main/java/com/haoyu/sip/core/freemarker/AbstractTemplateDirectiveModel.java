package com.haoyu.sip.core.freemarker;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.github.miemiedev.mybatis.paginator.domain.Order;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import freemarker.template.SimpleScalar;
import freemarker.template.TemplateBooleanModel;
import freemarker.template.TemplateDateModel;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateModelException;
import freemarker.template.TemplateScalarModel;
import freemarker.template.TemplateSequenceModel;

public abstract class AbstractTemplateDirectiveModel implements TemplateDirectiveModel{
	
	protected PageBounds getPageBounds(Map params){
		PageBounds pageBounds = new PageBounds();
		boolean hasPageParam = false;
		if (params.containsKey("page")  && params.get("page") != null) {
			hasPageParam = true;
			String page = params.get("page").toString();
			pageBounds.setPage(Integer.valueOf(page));
		}
		if (params.containsKey("limit")  && params.get("limit") != null) {
			hasPageParam = true;
			String limit = params.get("limit").toString();
			pageBounds.setLimit(Integer.valueOf(limit));
			pageBounds.setContainsTotalCount(true);
		}
		if (params.containsKey("orders")  && params.get("orders") != null) {
			String orders = params.get("orders").toString();
			pageBounds.setOrders(Order.formString(orders));
			if (!hasPageParam) {
				pageBounds.setLimit(Integer.MAX_VALUE);
				pageBounds.setPage(1);
			}
			pageBounds.setContainsTotalCount(true);
			return pageBounds;
		}
		if(hasPageParam){
			pageBounds.setContainsTotalCount(true);
			return pageBounds;
		}
		return null;
	}
	
	protected Map<String,Object> getSelectParam(Map<String,Object> params){
		Map<String,Object> param = Maps.newHashMap();
		if(!params.isEmpty()){
			for(Map.Entry<String, Object> entry:params.entrySet()){
				//只处理字符串类型参数
				if(!isPageParam(entry.getKey())){
					try {
						if(entry.getValue() instanceof SimpleScalar){
							String value = ((SimpleScalar)entry.getValue()).getAsString();
							if (StringUtils.isNotEmpty(value)) {
								param.put(entry.getKey(),((SimpleScalar)entry.getValue()).getAsString());
							}
						}else if (entry.getValue() instanceof TemplateBooleanModel) {
							param.put(entry.getKey(), ((TemplateBooleanModel)entry.getValue()).getAsBoolean());
						}else if(entry.getValue() instanceof TemplateScalarModel){
							param.put(entry.getKey(), ((TemplateScalarModel)entry.getValue()).getAsString());
						}else if(entry.getValue() instanceof TemplateDateModel){
							param.put(entry.getKey(), ((TemplateDateModel)entry.getValue()).getAsDate());
						}else if(entry.getValue() instanceof TemplateSequenceModel){
							TemplateSequenceModel templateSequenceModel = (TemplateSequenceModel)entry.getValue();
							List<String> list = Lists.newArrayList();
							for (int i = 0; i < templateSequenceModel.size(); i++) {
								list.add(templateSequenceModel.get(i).toString());
							}
							param.put(entry.getKey(), list);
						}
					} catch (TemplateModelException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return param;
	}
	
	protected String getId(Map params){
		String id = "";
		if(params.containsKey("id")&&(params.get("id") instanceof SimpleScalar || params.get("id") instanceof TemplateScalarModel ) ){
			if (params.get("id").toString() instanceof String) {
				id = params.get("id").toString();
			}
		}
		return id;
	}
	
	private boolean isPageParam(String key){
		if("page".equals(key)||"limit".equals(key)||"orders".equals(key)){
			return true;
		}else{
			return false;
		}
	}
	

}
