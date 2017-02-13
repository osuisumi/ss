/**
 * 
 */
package com.haoyu.sip.login.web.websocket;


import java.io.IOException;
import java.io.OutputStream;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.webflow.executor.FlowExecutor;

import com.haoyu.sip.barcode.qrcode.QRCodeEncoder;
import com.haoyu.sip.login.auth.QrCodeCredential;
import com.haoyu.sip.login.ticket.ExpirationPolicy;
import com.haoyu.sip.login.ticket.QRCodeLoginTicket;
import com.haoyu.sip.login.ticket.QRCodeLoginTicketImpl;
import com.haoyu.sip.login.ticket.Ticket;
import com.haoyu.sip.login.ticket.registry.TicketRegistry;
import com.haoyu.sip.login.util.DefaultUniqueTicketIdGenerator;
import com.haoyu.sip.login.util.UniqueTicketIdGenerator;
import com.haoyu.sip.login.web.support.WebUtils;



/**
 * @author Administrator
 *
 */
@Controller
public class QRCodeLoginAction{
		
	 	@Autowired
	    private TicketRegistry ticketRegistry;
	 	
	    @Autowired
	    @Qualifier("qrcodeLoginTicketExpirationPolicy") 
	    private ExpirationPolicy qrcodeLoginTicketExpirationPolicy;
	    
	    private UniqueTicketIdGenerator qrcodeloginTicketUniqueIdGenerator = new DefaultUniqueTicketIdGenerator(40,"test");
	    
	    @Autowired
	    private String qrcodeUri;
	    
	    @Autowired
	    private HttpServletResponse response;
	    
	    @Autowired
	    private HttpServletRequest request;
	    
	    @Autowired
	    private SimpMessagingTemplate simpMessagingTemplate;
	   

	    @MessageMapping("/qrcodeLogin")	
	  //	@SendTo("/topic/login")
	    public void browserLogin(String qrcodeloginTicketId) throws Exception {
		     while(true){
		        	Ticket ticket= ticketRegistry.getTicket(qrcodeloginTicketId);
		        	if(ticket==null||!(ticket instanceof QRCodeLoginTicket))
		        		break;
		        	else if(((QRCodeLoginTicket) ticket).isExpired()){
		        		simpMessagingTemplate.convertAndSend("/queue/login-"+qrcodeloginTicketId, new QrcodeLoginResult("expired"));
		        		return;
		        	}
		        	else if(((QRCodeLoginTicket) ticket).isLogged()){
		        		simpMessagingTemplate.convertAndSend("/queue/login-"+qrcodeloginTicketId,  new QrcodeLoginResult("browserLoginSuccess",new QrCodeCredential(((QRCodeLoginTicket) ticket).getAppToken(),((QRCodeLoginTicket) ticket).getUsername())));
		        		return;
		        	}
		        }
		     simpMessagingTemplate.convertAndSend("/queue/login-"+qrcodeloginTicketId,  new QrcodeLoginResult("browserLoginFail"));
	    }
	  	
	  	@RequestMapping("/appLogin")
	  	@ResponseBody
	  	public String appLogin(String qrcodeloginTicketId){
	  		Ticket ticket= ticketRegistry.getTicket(qrcodeloginTicketId);
	  		if(ticket==null||!(ticket instanceof QRCodeLoginTicket))
	  			return "fail";
	  		((QRCodeLoginTicket) ticket).setLogged(true);
	  		String appToken = request.getSession(false).getId();
	  		((QRCodeLoginTicket) ticket).setAppToken(appToken);
	  		((QRCodeLoginTicket) ticket).setUsername("casuser");
	  		return "appLoginSuccess";
	  	}
	  	
	  
	  	
	  	@RequestMapping("/getQrcodelt")
	  	@ResponseBody
	  	public String getQrcodeLoginTicketId(){
	  		 final QRCodeLoginTicket qrcodeLoginTicket = new QRCodeLoginTicketImpl( this.qrcodeloginTicketUniqueIdGenerator
	                 .getNewTicketId(QRCodeLoginTicket.PREFIX),this.qrcodeLoginTicketExpirationPolicy);
	         this.ticketRegistry.addTicket(qrcodeLoginTicket);
	         return qrcodeLoginTicket.getId();
	  	}
	  	
	  	@RequestMapping("/getUrlQrcode")
	  	@ResponseBody
	  	public void getUrlQrcode(String uuid){
	  		if(ticketRegistry.getTicket(uuid)!=null){
		        StringBuffer qrcodeContent = new StringBuffer(qrcodeUri);
		        if(qrcodeUri.lastIndexOf("/")==qrcodeUri.length()-1){
		        	qrcodeContent.append(uuid);
		        }else{
		        	qrcodeContent.append("/").append(uuid);
		        }
				try {
					 OutputStream outputStream = response.getOutputStream();
					 QRCodeEncoder.encode(qrcodeContent.toString(), 225, 225, outputStream);
					 outputStream.close();
					 outputStream.flush();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
	  		}
	  	}

	  	@MessageExceptionHandler
		@SendToUser("/queue/errors")
		public String handleException(Throwable exception) {
			return exception.getMessage();
		}

}
