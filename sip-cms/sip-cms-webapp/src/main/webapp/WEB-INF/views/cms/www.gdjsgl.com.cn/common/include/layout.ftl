<#macro layout title="" cssArray=[] jsArray=[]>
<@siteInfoDetailDirective>
	<#assign siteInfo=siteInfo/>
	<#assign mappingFolder="${siteInfo.mappingFolder}"/>
</@siteInfoDetailDirective>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="UTF-8">
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1">
<meta name="author" content="">
<meta name="description" content="">
<meta name="keywords" content="">
<meta http-equiv="Window-target" content="_top">
<title>${siteInfo.name!}</title>
<link rel="Shortcut Icon" href="${ctx}/css/${mappingFolder}/favicon.ico">
<link rel="stylesheet" href="${ctx}/css/reset.css">
<link rel="stylesheet" href="${ctx}/css/cms/${mappingFolder}/p/style.css">
<script type="text/javascript" src="${ctx}/js/jquery.js"></script>
</head>
<body id="index">
    <div id="wrap">
         <!-- start header -->
        <div class="g-header">
            <#include "header.ftl"/>
        </div>
        <!--end header -->
        

        <!--begin body content -->
        <div class="g-main">
             <#nested>            
        </div>
        <!--end body content -->
        <!--begin footer -->
        <div class="g-footer">
        	 <div class="g-auto">
               <#include "footer.ftl"/>
             </div>
        </div>
        <!--end footer -->
    </div>
    <!--end wrap -->
</body>
</html>
</#macro>
