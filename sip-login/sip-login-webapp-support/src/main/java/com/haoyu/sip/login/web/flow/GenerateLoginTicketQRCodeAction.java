/*
 * Licensed to Jasig under one or more contributor license
 * agreements. See the NOTICE file distributed with this work
 * for additional information regarding copyright ownership.
 * Jasig licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file
 * except in compliance with the License.  You may obtain a
 * copy of the License at the following location:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package com.haoyu.sip.login.web.flow;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.webflow.execution.RequestContext;

import com.haoyu.sip.login.CentralAuthenticationService;
import com.haoyu.sip.login.ticket.Ticket;
import com.haoyu.sip.login.ticket.registry.TicketRegistry;
import com.haoyu.sip.login.util.UniqueTicketIdGenerator;
import com.haoyu.sip.login.web.support.WebUtils;


/**
 * Generates the login ticket parameter as described in section 3.5 of the
 * <a href="http://www.jasig.org/cas/protocol">CAS protocol</a>.
 * 生成登录票据参数,登录前需要先获取该参数
 * @author Marvin S. Addison
 * @since 3.4.9
 *
 */
public class GenerateLoginTicketQRCodeAction {
    /** 3.5.1 - Login tickets SHOULD begin with characters "LT-". */
    private static final String PREFIX = "LT";

    /** Logger instance. */
    private final Logger logger = LoggerFactory.getLogger(getClass());
    
    @NotNull
    private String qrcodeUri;

    @NotNull
    private UniqueTicketIdGenerator ticketIdGenerator;
    
    @NotNull
    private CentralAuthenticationService centralAuthenticationService;

    
    public GenerateLoginTicketQRCodeAction(final CentralAuthenticationService centralAuthenticationService){
    	this.centralAuthenticationService = centralAuthenticationService;
    }

    public final String generate(final RequestContext context) {
        final String loginTicket = this.ticketIdGenerator.getNewTicketId(PREFIX);
        logger.debug("Generated login ticket {}", loginTicket);
        WebUtils.putLoginTicket(context, loginTicket);
      /*  String qrcodeLoginTicketId = centralAuthenticationService.createQRCodeLoginTicket(loginTicket);
        StringBuffer qrcodeContent = new StringBuffer(qrcodeUri);
        if(qrcodeUri.lastIndexOf("/")==qrcodeUri.length()-1){
        	qrcodeContent.append(qrcodeLoginTicketId);
        }else{
        	qrcodeContent.append("/").append(qrcodeLoginTicketId);
        }
        WebUtils.putLoginTicketQRCode(context, qrcodeContent.toString());*/
        return "generated";
    }

    public void setTicketIdGenerator(final UniqueTicketIdGenerator generator) {
        this.ticketIdGenerator = generator;
    }

	public void setQrcodeUri(String qrcodeUri) {
		this.qrcodeUri = qrcodeUri;
	}

	public void setCentralAuthenticationService(
			CentralAuthenticationService centralAuthenticationService) {
		this.centralAuthenticationService = centralAuthenticationService;
	}
    
}
