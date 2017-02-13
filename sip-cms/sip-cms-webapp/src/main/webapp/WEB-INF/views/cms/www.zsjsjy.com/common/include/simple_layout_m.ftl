<#macro layout title="中山教师教育" cssArray=[] jsArray=[] showScroll=true pullUpEvent="">
<@siteInfoDetailDirective>
	<#assign siteInfo=siteInfo/>
	<#assign mappingFolder="${siteInfo.mappingFolder}"/>
</@siteInfoDetailDirective>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8" />
<meta name="keywords" content="${title}" />
<meta name="description" content="${title}" />
<meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no" />
<meta name="format-detection" content="telephone=no" />
<title>${title}</title>
<link rel="Shortcut Icon" href="${ctx}/css/cms/${mappingFolder}/favicon.ico">
<link rel="stylesheet" href="${ctx}/css/cms/${mappingFolder}/m/normalize.css">
<link rel="stylesheet" href="${ctx}/css/cms/${mappingFolder}/m/style.css">
<#if cssArray??>
	<#list cssArray as css>
<link rel="stylesheet" href="${css}">	
	</#list>
</#if>
<script src="${ctx}/js/jquery.js"></script>
<script src="${ctx}/js/iscroll/iscroll-probe.js"></script>
<script src="${ctx}/js/scroll-common.js"></script>
<#if jsArray??>
	<#list jsArray as js>
<script type="text/javascript" src="${js}"></script>	
	</#list>
</#if>
<#if showScroll>
<script>
$(function(){
    
   <#if (pullUpEvent??) && pullUpEvent!="">
    	<#assign pullUp="true"/>
    <#else>
    	<#assign pullUp="false"/>
    </#if>
      //开启模拟滚动条
	$("body").myScrollFn({
		//允许到底部时上拉加载更多
		pullUp: ${pullUp},
		//执行底部时上拉加载更多函数
		pullUpFn: function(){
			  <#if (pullUpEvent??) && pullUpEvent!="">
			  		${pullUpEvent};
			  </#if>         
		}
	});

})
</script>
</#if>
</head>
<body>
<!-- begin wrapper -->
<div id="wrapper">	
    <#nested>
</div> 
<!-- end wrapper -->
</body>
</html>
</#macro>