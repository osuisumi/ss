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

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.binding.message.MessageBuilder;
import org.springframework.binding.message.MessageContext;
import org.springframework.util.StringUtils;
import org.springframework.web.util.CookieGenerator;
import org.springframework.webflow.core.collection.LocalAttributeMap;
import org.springframework.webflow.execution.Event;
import org.springframework.webflow.execution.RequestContext;

import com.haoyu.sip.login.CentralAuthenticationService;
import com.haoyu.sip.login.Message;
import com.haoyu.sip.login.auth.AuthenticationException;
import com.haoyu.sip.login.auth.Credential;
import com.haoyu.sip.login.auth.HandlerResult;
import com.haoyu.sip.login.auth.QrCodeCredential;
import com.haoyu.sip.login.auth.principal.Service;
import com.haoyu.sip.login.ticket.QRCodeLoginTicket;
import com.haoyu.sip.login.ticket.TicketCreationException;
import com.haoyu.sip.login.ticket.TicketException;
import com.haoyu.sip.login.ticket.TicketGrantingTicket;
import com.haoyu.sip.login.ticket.registry.TicketRegistry;
import com.haoyu.sip.login.web.bind.CredentialsBinder;
import com.haoyu.sip.login.web.support.WebUtils;

/**
 * Action to authenticate credential and retrieve a TicketGrantingTicket for
 * those credential. If there is a request for renew, then it also generates
 * the Service Ticket required.
 *
 * @author Scott Battaglia
 * @since 3.0.4
 */
public class AuthenticationViaFormAction {

    /** Authentication success result. */
    public static final String SUCCESS = "success";

    /** Authentication succeeded with warnings from authn subsystem that should be displayed to user. */
    public static final String SUCCESS_WITH_WARNINGS = "successWithWarnings";

    /** Authentication success with "warn" enabled. */
    public static final String WARN = "warn";

    /** Authentication failure result. */
    public static final String AUTHENTICATION_FAILURE = "authenticationFailure";

    /** Error result. */
    public static final String ERROR = "error";

    /**
     * Binder that allows additional binding of form object beyond Spring
     * defaults.
     */
    private CredentialsBinder credentialsBinder;

    /** Core we delegate to for handling all ticket related tasks. */
    @NotNull
    private CentralAuthenticationService centralAuthenticationService;

    /** Ticket registry used to retrieve tickets by ID. */
    @NotNull
    private TicketRegistry ticketRegistry;

    @NotNull
    private CookieGenerator warnCookieGenerator;

    /** Flag indicating whether message context contains warning messages. */
    private boolean hasWarningMessages;

    /** Logger instance. **/
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    public final void doBind(final RequestContext context, final Credential credential) throws Exception {
        final HttpServletRequest request = WebUtils.getHttpServletRequest(context);

        if (this.credentialsBinder != null && this.credentialsBinder.supports(credential.getClass())) {
            this.credentialsBinder.bind(request, credential);
        }
    }
    public final Event submit(final RequestContext context, final Credential credential,
            final MessageContext messageContext) throws Exception {
    	final String qrcodeLoginTicketId = WebUtils.getQrcodeLoginTicketIdFromRequest(context);
		final String appToken = WebUtils.getAppTokenFromRequest(context);
		QrCodeCredential qcc = null;
        // Validate login ticket
    	if(StringUtils.isEmpty(qrcodeLoginTicketId)||StringUtils.isEmpty(appToken)){
	        final String authoritativeLoginTicket = WebUtils.getLoginTicketFromFlowScope(context);//获取flowScope存储的loginTicket的值并删除
	        final String providedLoginTicket = WebUtils.getLoginTicketFromRequest(context);//获取请求提交的loginTicket的值
	        if (!authoritativeLoginTicket.equals(providedLoginTicket)) {
	            logger.warn("Invalid login ticket {}", providedLoginTicket);
	            messageContext.addMessage(new MessageBuilder().code("error.invalid.loginticket").build());
	            return newEvent(ERROR);
	        }
    	}else{
    		qcc = new QrCodeCredential(appToken,credential.getId());
    		QRCodeLoginTicket qrcodeLoginTicket = (QRCodeLoginTicket)ticketRegistry.getTicket(qrcodeLoginTicketId);
    		if(qrcodeLoginTicket==null||qrcodeLoginTicket.getAppToken()==null||!qrcodeLoginTicket.getAppToken().equals(appToken)){
    			return newEvent(ERROR);
    		}
    		//清空appToken
    		qrcodeLoginTicket.setAppToken(null);
    		ticketRegistry.deleteTicket(qrcodeLoginTicketId);
    	}

        final String ticketGrantingTicketId = WebUtils.getTicketGrantingTicketId(context);
        final Service service = WebUtils.getService(context);
        if (StringUtils.hasText(context.getRequestParameters().get("renew")) && ticketGrantingTicketId != null
                && service != null) {

            try {
                final String serviceTicketId = this.centralAuthenticationService.grantServiceTicket(
                        ticketGrantingTicketId, service, qcc!=null?qcc:credential);
                WebUtils.putServiceTicketInRequestScope(context, serviceTicketId);
                putWarnCookieIfRequestParameterPresent(context);
                return newEvent(WARN);
            } catch (final AuthenticationException e) {
                return newEvent(AUTHENTICATION_FAILURE, e);
            } catch (final TicketCreationException e) {
                logger.warn(
                        "Invalid attempt to access service using renew=true with different credential. "
                        + "Ending SSO session.");
                this.centralAuthenticationService.destroyTicketGrantingTicket(ticketGrantingTicketId);
            } catch (final TicketException e) {
                return newEvent(ERROR, e);
            }
        }

        try {
            final String tgtId = this.centralAuthenticationService.createTicketGrantingTicket(qcc!=null?qcc:credential);
            WebUtils.putTicketGrantingTicketInFlowScope(context, tgtId);
            putWarnCookieIfRequestParameterPresent(context);
            final TicketGrantingTicket tgt = (TicketGrantingTicket) this.ticketRegistry.getTicket(tgtId);
            for (final Map.Entry<String, HandlerResult> entry : tgt.getAuthentication().getSuccesses().entrySet()) {
                for (final Message message : entry.getValue().getWarnings()) {
                    addWarningToContext(messageContext, message);
                }
            }
            if (this.hasWarningMessages) {
                return newEvent(SUCCESS_WITH_WARNINGS);
            }
            return newEvent(SUCCESS);
        } catch (final AuthenticationException e) {
            return newEvent(AUTHENTICATION_FAILURE, e);
        } catch (final Exception e) {
            return newEvent(ERROR, e);
        }
    }

    private void putWarnCookieIfRequestParameterPresent(final RequestContext context) {
        final HttpServletResponse response = WebUtils.getHttpServletResponse(context);

        if (StringUtils.hasText(context.getExternalContext().getRequestParameterMap().get("warn"))) {
            this.warnCookieGenerator.addCookie(response, "true");
        } else {
            this.warnCookieGenerator.removeCookie(response);
        }
    }

    private AuthenticationException getAuthenticationExceptionAsCause(final TicketException e) {
        return (AuthenticationException) e.getCause();
    }

    private Event newEvent(final String id) {
        return new Event(this, id);
    }

    private Event newEvent(final String id, final Exception error) {
        return new Event(this, id, new LocalAttributeMap("error", error));
    }

    public final void setCentralAuthenticationService(final CentralAuthenticationService centralAuthenticationService) {
        this.centralAuthenticationService = centralAuthenticationService;
    }

    public void setTicketRegistry(final TicketRegistry ticketRegistry) {
        this.ticketRegistry = ticketRegistry;
    }

    /**
     * Set a CredentialsBinder for additional binding of the HttpServletRequest
     * to the Credential instance, beyond our default binding of the
     * Credential as a Form Object in Spring WebMVC parlance. By the time we
     * invoke this CredentialsBinder, we have already engaged in default binding
     * such that for each HttpServletRequest parameter, if there was a JavaBean
     * property of the Credential implementation of the same name, we have set
     * that property to be the value of the corresponding request parameter.
     * This CredentialsBinder plugin point exists to allow consideration of
     * things other than HttpServletRequest parameters in populating the
     * Credential (or more sophisticated consideration of the
     * HttpServletRequest parameters).
     *
     * @param credentialsBinder the credential binder to set.
     */
    public final void setCredentialsBinder(final CredentialsBinder credentialsBinder) {
        this.credentialsBinder = credentialsBinder;
    }

    public final void setWarnCookieGenerator(final CookieGenerator warnCookieGenerator) {
        this.warnCookieGenerator = warnCookieGenerator;
    }

    /**
     * Adds a warning message to the message context.
     *
     * @param context Message context.
     * @param warning Warning message.
     */
    private void addWarningToContext(final MessageContext context, final Message warning) {
        final MessageBuilder builder = new MessageBuilder()
                .warning()
                .code(warning.getCode())
                .defaultText(warning.getDefaultMessage())
                .args(warning.getParams());
        context.addMessage(builder.build());
        this.hasWarningMessages = true;
    }
}
