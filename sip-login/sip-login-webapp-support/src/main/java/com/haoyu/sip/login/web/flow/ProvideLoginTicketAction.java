package com.haoyu.sip.login.web.flow;

import javax.servlet.http.HttpServletRequest;

import org.springframework.webflow.action.AbstractAction;
import org.springframework.webflow.execution.Event;
import org.springframework.webflow.execution.RequestContext;

import com.haoyu.sip.login.web.support.WebUtils;

/**
 * Opens up the CAS web flow to allow external retrieval of a login ticket.
 * 用于ajax登录时，根据参数是否需要获取loginTicket的值
 * @author denger
 */
public class ProvideLoginTicketAction extends AbstractAction{

	@Override
	protected Event doExecute(RequestContext context) throws Exception {
		final HttpServletRequest request = WebUtils.getHttpServletRequest(context);
		// 如果参数中包含 get-lt 参数，则返回 loginTicketRequested 执行流，并跳转至 loginTicket 生成页，否则 则跳过该flow，并按照原始login的流程来执行。
		if (request.getParameter("get-lt") != null && request.getParameter("get-lt").equalsIgnoreCase("true")) {
			return result("loginTicketRequested");
		}
		//如果参数中包含get-lt-qr参数，则返回loginTicketQRCodeRequested执行流，并跳转至loginTicketQRCode生成页
		else if(request.getParameter("get-lt-qr") != null && request.getParameter("get-lt-qr").equalsIgnoreCase("true")){
			return result("loginTicketQRCodeRequested");
		}  
		return result("continue");
	}
	
}
