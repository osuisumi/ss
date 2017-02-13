<#macro layout cssArray=[] jsArray=[]>
<#escape x as x?html>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title></title>
<link rel="stylesheet" href="${ctx}/css/admin/neat.css">
<link rel="stylesheet" href="${ctx}/css/admin/blue/mis-style.css">
<link rel="stylesheet" href="${ctx}/js/mylayer/v1.0/skin/default/mylayer.css">
<#if cssArray??>
	<#list cssArray as css>
<link rel="stylesheet" href="${css}">	
	</#list>
</#if>
<script type="text/javascript" src="${ctx}/js/jquery.js"></script>
<script type="text/javascript" src="${ctx}/js/mis.js"></script>
<script type="text/javascript" src="${ctx }/js/sip-common.js"></script>
<#if jsArray??>
	<#list jsArray as js>
<script type="text/javascript" src="${js}"></script>	
	</#list>
</#if>
</head>
<body>
	<#nested/>
</body>
</html>
</#escape>
</#macro>