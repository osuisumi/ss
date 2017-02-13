<%@page import="java.io.OutputStream"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.haoyu.sip.barcode.qrcode.QRCodeEncoder"%>
<%
	String contents = (String)request.getAttribute("loginTicketQRCode");
	if(contents!=null){	
		OutputStream outputStream  = response.getOutputStream();
		QRCodeEncoder.encode(contents, 225, 225, outputStream);
		out.clear();
		out = pageContext.pushBody();
	}else{
		System.out.print("error");
	}
%>