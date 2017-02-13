<%--

    Licensed to Jasig under one or more contributor license
    agreements. See the NOTICE file distributed with this work
    for additional information regarding copyright ownership.
    Jasig licenses this file to you under the Apache License,
    Version 2.0 (the "License"); you may not use this file
    except in compliance with the License.  You may obtain a
    copy of the License at the following location:

      http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing,
    software distributed under the License is distributed on an
    "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
    KIND, either express or implied.  See the License for the
    specific language governing permissions and limitations
    under the License.

--%>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="zh_CN">
<head>
<meta charset="UTF-8">
<title>广州昊誉信息科技有限公司</title>
<meta name="Keywords" content="教育,网络,数字校园,教育信息化,科技,网站开发,软件开发,远程教育">
<meta name="description" content="广州昊誉信息科技有限公司成立于2012年，公司以计算机软件技术为基础，立足于教育信息化行业，为客户提供教育信息化相关软件开发及技术支持的服务 ，旨在成为专业的教育信息化建设解决方案提供商。">
<meta content="广州昊誉信息科技有限公司" name="author"/>
<link rel="stylesheet" href="${ctx}/css/reset.css">
<link rel="stylesheet" href="${ctx}/css/login.css">
<script type="text/javascript">
	var ctx = "${ctx}";
</script>
<script type="text/javascript" src="${ctx}/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.dsTab.js"></script>
<script type="text/javascript" src="${ctx}/js/login.js"></script>
</head>
<body>
	<!--start #g-logWrap -->
	<div id="g-logWrap">