<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="dict" uri="http://dictionary.haoyi.com"%>
<%@ taglib prefix="file" uri="http://file.haoyu.com" %>
<%@ taglib prefix="sipc" uri="http://sip.haoyu.com/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="tb" uri="http://textBook.haoyu.com" %>

<%@ taglib prefix="tag" tagdir="/WEB-INF/tags" %>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<input id="ctx" type="hidden" value="${pageContext.request.contextPath}">

<fmt:setBundle basename="message" var="message" />
<fmt:message var="locale" key="locale" bundle="${message}" />
