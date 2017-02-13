package com.haoyu.sip.core.web;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;

import com.github.miemiedev.mybatis.paginator.domain.Order;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;




public abstract class AbstractBaseController {
	
	protected static final Logger logger = LoggerFactory.getLogger(AbstractBaseController.class);
    @Resource 
    protected  HttpServletRequest request; 
	
	public static final String SESSION_LOGINER = "loginer";
	
	public static final String ERROR_MESSAGES = "errorMessages";
	
	public static final String SUCCESS_MESSAGES = "successMessages";

	protected final HttpSession getSession(){
		if(request!=null){
			return request.getSession();
		}
		return null;
	}
	
	/***
	 * 设置request属性
	 * 等同于request.setAttribute
	 * @param name
	 * @param value
	 * @return this
	 */
	public AbstractBaseController setAttr(String name, Object value) {
		request.setAttribute(name, value);
		return this;
	}
	
	/**
	 * 删除request属性
	 * 等同于request.removeAttribute
	 * @param name
	 * @return this
	 */
	public AbstractBaseController removeAttr(String name) {
		request.removeAttribute(name);
		return this;
	}
	

	public AbstractBaseController setAttrs(Map<String, Object> attrMap) {
		for (Map.Entry<String, Object> entry : attrMap.entrySet())
			request.setAttribute(entry.getKey(), entry.getValue());
		return this;
	}
	

	public String getPara(String name) {
		return request.getParameter(name);
	}
	

	public String getPara(String name, String defaultValue) {
		String result = request.getParameter(name);
		return result != null && !"".equals(result) ? result : defaultValue;
	}
	

	public Map<String, String[]> getParaMap() {
		return request.getParameterMap();
	}
	

	public Enumeration<String> getParaNames() {
		return request.getParameterNames();
	}
	

	public String[] getParaValues(String name) {
		return request.getParameterValues(name);
	}
	

	public Enumeration<String> getAttrNames() {
		return request.getAttributeNames();
	}
	
	@SuppressWarnings("unchecked")
	public <T> T getAttr(String name) {
		return (T)request.getAttribute(name);
	}
	
	/**
	 * 直接输出内容的简便函数.
	 */
	protected String render(String text, String contentType,HttpServletResponse response) {
		try {
			response.setContentType(contentType);
			response.getWriter().write(text);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}
	
	/**
	 * 直接输出字符串.
	 */
	protected String renderText(String text,HttpServletResponse response) {
		return render(text, "text/plain;charset=GBK",response);
	}
	
	/**
	 * 直接输出GBK字符串.
	 */
	protected String renderText(int number,HttpServletResponse response) {
		return render(String.valueOf(number), "text/plain;charset=GBK",response);
	}
	
	/**
	 * 直接输出字符串GBK编码
	 * @param html
	 * @return
	 */
	protected String renderHtml(String html,HttpServletResponse response) {
		return render(html, "text/html;charset=GBK",response);
	}
	/**
	 * 直接输出字符串UTF8编码.
	 */
	protected String renderHtmlUTF8(String html,HttpServletResponse response) {
		return render(html, "text/html;charset=UTF-8",response);
	}
	

	/**
	 * 直接输出XML.
	 */
	protected String renderXML(String xml,HttpServletResponse response) {
		return render(xml, "text/xml;charset=GBK" ,response);
	}
	
	@SuppressWarnings("unchecked")
	public void saveError(String error) {
        List<String> errors = (List<String>) request.getAttribute(ERROR_MESSAGES);
        if (errors == null) {
            errors = new ArrayList<String>();
        }
        errors.add(error);
        request.setAttribute("errors", errors);
    }
	
	@SuppressWarnings("unchecked")
	public void saveSuccess(String msg) {
        List<String> messages = (List<String>) request.getAttribute(SUCCESS_MESSAGES);
        if (messages == null) {
            messages = new ArrayList<String>();
        }
        messages.add(msg);
        request.setAttribute(SUCCESS_MESSAGES, messages);
    }
	
	protected PageBounds getPageBounds(int pageSize,boolean isPage){
		String limit = request.getParameter("limit");
		if (StringUtils.isNotEmpty(limit)) {
			pageSize = Integer.parseInt(limit);
			request.setAttribute("limit", limit);
		}
		if(pageSize == 0){
			pageSize = 10;
		}
		String currentPage = request.getParameter("page");
		if (!isPage && StringUtils.isEmpty(currentPage)) {
			return null;
		}else {
			PageBounds pageBounds = new PageBounds();
			pageBounds.setContainsTotalCount(true);
			if (StringUtils.isNotEmpty(currentPage)) {
				pageBounds.setPage(Integer.parseInt(currentPage));
			}			
			pageBounds.setLimit(pageSize);
			if(request.getParameter("orders")!=null){
				String orders = request.getParameter("orders");
				request.setAttribute("orders", orders);
				pageBounds.setOrders(Order.formString(orders));
			}
			request.setAttribute("pageBounds", pageBounds);
			return pageBounds;
		}
	}
	
	protected void setParameterToModel(HttpServletRequest request,Model model){
		final Set<String> parameterNames = request.getParameterMap().keySet();
		for (final String name : parameterNames) {
				String[] values = request.getParameterValues(name);
				if(values!=null&&values.length==1){
					model.addAttribute("param_"+name,values[0]);
				}else{
					model.addAttribute("param_"+name, values);
				}
		}
	}
	
}
